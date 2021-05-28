package com.idle.game.core.battle;

import com.idle.game.core.BaseObject;
import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.action.type.SubActionType;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.buff.Buff;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.formation.type.FormationType;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.util.DiceUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;

import static com.idle.game.core.action.type.ActionType.*;
import static com.idle.game.core.action.type.SubActionType.DEATH;
import static com.idle.game.core.battle.type.BattleTeamType.ATTACK_TEAM;
import static com.idle.game.core.battle.type.BattleTeamType.DEFENSE_TEAM;
import static com.idle.game.core.constant.IdleConstants.LOG;
import static com.idle.game.core.formation.type.FormationType.ATTACK;

public class Battle extends BaseObject {

    static {

        LOG.addHandler(new Handler() {

            private StringBuffer append = new StringBuffer("");

            @Override
            public void publish(LogRecord lr) {
                if (lr.getMessage().contains(BATTLE_START.toString())) {
                    append = new StringBuffer("");
                } else if (lr.getMessage().contains("<-")) {
                    append.delete(0, 3);
                }
                lr.setMessage(append.toString() + lr.getMessage());
                if (lr.getMessage().contains("->")) {
                    append.append("   ");
                }
            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        });
    }

    private final List<BattleLog> battleLog;
    private Integer turn = 1;
    private final BattleFormation attackFormation;
    private final BattleFormation defenseFormation;
    private final List<BattlePositionedHero> battlePositionedHeroes;
    private BattleFormation winner;

    public Battle() {
        this.battleLog = null;
        this.attackFormation = null;
        this.defenseFormation = null;
        this.battlePositionedHeroes = null;
    }

    public Battle(BattleFormation attackForm, BattleFormation defenseForm) {
        assert attackForm != null : "Attack formation can not be null, BUG";
        assert defenseForm != null : "Defense formation can not be null, BUG";

        this.attackFormation = attackForm;
        this.attackFormation.setFormationType(FormationType.ATTACK);
        this.defenseFormation = defenseForm;
        this.defenseFormation.setFormationType(FormationType.DEFENSE);

        this.battlePositionedHeroes = new ArrayList<>();

        this.attackFormation.getHeroes().forEach(t -> {
            t.setBattleTeamType(ATTACK_TEAM);
            this.battlePositionedHeroes.add(t.duplicate(Boolean.FALSE));
        });
        this.defenseFormation.getHeroes().forEach(t -> {
            t.setBattleTeamType(DEFENSE_TEAM);
            this.battlePositionedHeroes.add(t.duplicate(Boolean.FALSE));
        });

        this.battleLog = new ArrayList<>();
    }

    public BattleFormation getAttackFormation() {
        return this.attackFormation;
    }

    public BattleFormation getDefenseFormation() {
        return this.defenseFormation;
    }

    public Boolean isFormationAttackWinner() {
        return getWinner().getFormationType().equals(ATTACK);
    }

    public BattleFormation getWinner() {
        return winner;
    }

    public Integer getTurn() {
        return turn;
    }

    private void addBattleLog(BattleLog bl) {

        switch (bl.getBattleEvent().getActionType()) {
            case DMG:
            case HEAL:
                if (bl.getBattleEvent().getSubType() != null) {
                    LOG.log(Level.INFO, "[{0}][{1}] value={2}, target{3}",
                            new Object[]{bl.getBattleEvent().getActionType(), bl.getBattleEvent().getSubType(),
                                    bl.getBattleEvent().getValue(), bl.getHeroesTarget()});
                } else {
                    LOG.log(Level.INFO, "[{0}] value={1}, target{2}",
                            new Object[]{bl.getBattleEvent().getActionType(), bl.getBattleEvent().getValue(), bl.getHeroesTarget()});
                }
                break;
            case BUFF_START:
                LOG.log(Level.INFO, "[{0}]", bl.getBattleEvent().getActionType().toString());
                break;
            default:
                if (bl.getBattleEvent().getActionType().toString().contains("START")) {
                    LOG.log(Level.INFO, "->[{0}]", bl.getBattleEvent().getActionType().toString());
                } else {
                    LOG.log(Level.INFO, "<-[{0}]", bl.getBattleEvent().getActionType().toString());
                }
                break;
        }
        computePassives(bl.getBattleEvent().getActionType());

        this.battleLog.add(bl);
    }

    public List<BattleLog> getBattleLog() {
        return battleLog;
    }

    public BattleFormation doBattle() {

        LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);

        addBattleLog(new BattleLog(this.turn, new BattleEvent(BATTLE_START)));

        addBattleLog(new BattleLog(this.turn, new BattleEvent(PREPARE_TO_BATTLE_START)));
        prepareToBattle();
        addBattleLog(new BattleLog(this.turn, new BattleEvent(PREPARE_TO_BATTLE_END)));

        doTurn();

        LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);
        LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);

        LOG.log(Level.INFO, "[battleLog] {0}", this);

        for (int i = 0; i < battleLog.size(); i++) {
            LOG.log(Level.INFO, "[battleLog][index={0}] {1}", new Object[]{i, battleLog.get(i)});
        }

        return winner;

    }

    private void doTurn() {

        Boolean lastTurn = Boolean.FALSE;

        addBattleLog(new BattleLog(this.turn, new BattleEvent(TURN_START)));
        LOG.log(Level.INFO, "[TURN] {0}", turn);

        List<BattlePositionedHero> turnActionRated = getTurnActionRated();
        LOG.log(Level.INFO, "[TURN_ACTION_RATED] {0}", turnActionRated);

        addBattleLog(new BattleLog(this.turn, new BattleEvent(PREPARE_TO_TURN_START)));
        prepareToTurn(turnActionRated);
        addBattleLog(new BattleLog(this.turn, new BattleEvent(PREPARE_TO_TURN_END)));

        addBattleLog(new BattleLog(this.turn, new BattleEvent(COMPUTE_BUFF_START)));
        computeBuffs(turnActionRated);
        addBattleLog(new BattleLog(this.turn, new BattleEvent(COMPUTE_BUFF_END)));

        if (this.winner == null) {
            for (int i = 0; i < turnActionRated.size(); i++) {
                BattlePositionedHero bph = turnActionRated.get(i);
                addBattleLog(new BattleLog(this.turn, bph.duplicate(), new BattleEvent(ACTION_START)));
                LOG.log(Level.INFO, "[BPH] {0}", turnActionRated.get(i));
                doAction(bph);
                addBattleLog(new BattleLog(this.turn, bph.duplicate(), new BattleEvent(ACTION_END)));
            }
        }
        addBattleLog(new BattleLog(this.turn, new BattleEvent(TURN_END)));

        LOG.log(Level.INFO, "->[VERIFY_WINNER_START]");
        this.winner = verifyWinner();
        LOG.log(Level.INFO, "[VERIFY_WINNER] {0}", this.winner);
        LOG.log(Level.INFO, "<-[VERIFY_WINNER_END]");

        if (this.winner == null) {
            if (this.turn < IdleConstants.TURN_LIMIT) {
                this.turn++;
                doTurn();
            } else {
                this.winner = this.defenseFormation;
                lastTurn = Boolean.TRUE;
            }
        } else {
            lastTurn = Boolean.TRUE;
        }

        if (lastTurn) {
            LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);
            LOG.log(Level.INFO, "[WINNER] {0} on turn: {1}", new Object[]{this.winner, this.turn});
            LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);
            this.addBattleLog(new BattleLog(this.turn, new BattleEvent(BATTLE_END)));
        }

    }

    private BattleFormation verifyWinner() {
//        if (getHeroesByTeamTypeAndAlive(ATTACK_TEAM).count() <= 0) {
//            return defenseFormation;
//        } else if (getHeroesByTeamTypeAndAlive(DEFENSE_TEAM).count() <= 0) {
//            return attackFormation;
//        }
        return null;
    }

    private void doAction(BattlePositionedHero aPositionedHero) {
        doAction(aPositionedHero, null);
    }

    private void doAction(BattlePositionedHero aPositionedHero, Action customAction) {

        if (aPositionedHero.getHero().getCurrHp() <= 0) {
            LOG.log(Level.INFO, "[HERO_HEAD] HERO DEAD! SKIPPING");
            return;
        }

        if (!aPositionedHero.getHero().getCanDoAction()) {
            LOG.log(Level.INFO, "[HERO_CAN_NOT_DO_ACTION] SKIPPING");
            return;
        }

        Action action = customAction != null ? customAction : aPositionedHero.nextAction();

        LOG.log(Level.INFO, "[ACTION] {0}", action);

        calculateAction(aPositionedHero, action);

        calculateSecondaryActions(aPositionedHero, action);

    }

    public void doDamage(BattleEvent be, BattlePositionedHero aPositionedHero,
                         ActionEffect ae, BattlePositionedHero tPositionedHero, Boolean special) {

        assert ae.getDamageType() != null : "DamageType can not be null, BUG";

        Double dmgReduction = tPositionedHero.getHero().getDmgDefenseReduction(ae.getDamageType());

        Double critDmgModifier = aPositionedHero.getHero().getCriticalHit();
        if (critDmgModifier >= 1.0) {
            be.setSubType(SubActionType.CRITICAL);
        }

        Integer dmgModifier = special ? aPositionedHero.getHero().getCurrAp() : aPositionedHero.getHero().getCurrDmg();

        assert dmgModifier != null : "DmgModifier is null. BUG";

        be.setValue(-(int) ((dmgModifier * critDmgModifier * ae.getPercentage() / 100f) * dmgReduction));
        be.setDamageType(ae.getDamageType());

        if (tPositionedHero.dmg(be.getValue())) {
            be.setSubType(DEATH);
        }


    }

    public void doHeal(BattleEvent be, BattlePositionedHero aPositionedHero,
                       ActionEffect ae, BattlePositionedHero tPositionedHero, Boolean special) {

        Integer dmgModifier = special ? aPositionedHero.getHero().getCurrAp() : aPositionedHero.getHero().getCurrDmg();

        be.setValue((int) ((dmgModifier * ae.getPercentage() / 100f)));
        be.setDamageType(ae.getDamageType());

        tPositionedHero.heal(be.getValue());
        LOG.log(Level.INFO, "[HEAL, value={0}] HEAL {0} TO HERO {1}", new Object[]{be.getValue(), tPositionedHero.getHero()});

    }

    private List<BattlePositionedHero> getTargets(BattlePositionedHero aPositionedHero, ActionEffect ae) {
        assert ae.getTargetType() != null : "Target of action can not be null, BUG";

        BattleTeamType battleTeamTypeTarget = ae.getOverSameTeam() ? aPositionedHero.getBattleTeamType() : aPositionedHero.getBattleTeamType().getOpposite();

        return  ae.getTargetType().getTarget().get(battleTeamTypeTarget, battlePositionedHeroes, aPositionedHero);
    }

    private void calculateActionEffect(BattlePositionedHero aPositionedHero, ActionEffect ae, Boolean special) {

        List<BattlePositionedHero> targets = getTargets(aPositionedHero, ae);

        if (targets != null && !targets.isEmpty()) {

            final Integer energyGain = IdleConstants.ENERGY_GAIN_DOING_ATTACK / targets.size();

            targets.forEach(tPositionedHero -> {

                BattleEvent be = new BattleEvent(ae.getType());

                if (!ae.getCanBeDodge()
                        || tPositionedHero.getHero().getCurrDodgeChance() == 0
                        || DiceUtil.random(100) > tPositionedHero.getHero().getCurrDodgeChance()) {

                    switch (ae.getType()) {
                        case DMG:
                            doDamage(be, aPositionedHero, ae, tPositionedHero, special);
                            break;
                        case HEAL:
                            doHeal(be, aPositionedHero, ae, tPositionedHero, special);
                            break;
                    }

                    if (!special && aPositionedHero.getEnergy() < IdleConstants.MAX_ENERGY) {
                        aPositionedHero.setEnergy(aPositionedHero.getEnergy() + energyGain);
                    }

                    if (special) {
                        aPositionedHero.setEnergy(IdleConstants.MIN_ENERGY);
                    }

                    calculateBuffs(aPositionedHero, ae, tPositionedHero);

                } else {
                    be.setSubType(SubActionType.DODGE);
                }

                addBattleLog(new BattleLog(this.turn, aPositionedHero.duplicate(), be, tPositionedHero.duplicate()));
            });

        }
    }

    private void calculateAction(BattlePositionedHero aPositionedHero, Action a) {
        final ActionEffect actionMainEffect = a.getMainActionEffect();
        LOG.log(Level.INFO, "[ACTION_EFFECT] {0}", actionMainEffect);

        calculateActionEffect(aPositionedHero, actionMainEffect, a.getSpecial());

    }

    private void calculateSecondaryActions(BattlePositionedHero aPositionedHero, Action a) {
        final List<ActionEffect> secondaryActions = a.getSecondaryActionsEffects();

        if (secondaryActions != null) {
            secondaryActions.forEach(sa -> {
                LOG.log(Level.INFO, "[SECONDARY_ACTION_EFFECT] {0}", sa);

                calculateActionEffect(aPositionedHero, sa, a.getSpecial());
            });
        }

    }

    private void calculateBuffs(BattlePositionedHero aPositionedHero, ActionEffect ae, BattlePositionedHero tPositionedHero) {

        if (ae.getBuffEffects() != null) {
            ae.getBuffEffects().forEach((be) -> {

                if (be.getChance() == null
                        || be.getChance() == 0
                        || DiceUtil.random(100) <= be.getChance()) {

                    Buff buff = null;
                    switch (be.getEffectType()) {
                        case DMG:
                            buff = new Buff(
                                    be.getDuration(),
                                    (int) (aPositionedHero.getHero().getCurrDmg() * be.getPercentage() / -100d),
                                    be.getEffectType(), ae.getDamageType()
                            );
                            break;
                        case HEAL:
                            buff = new Buff(
                                    be.getDuration(),
                                    (int) (aPositionedHero.getHero().getCurrDmg() * be.getPercentage() / 100d),
                                    be.getEffectType()
                            );
                            break;
                        case FROZEN:
                        case STUN:
                        case SILENCE:
                            buff = new Buff(
                                    be.getDuration(), be.getEffectType()
                            );
                            break;
                        case DECREASE_ATTRIBUTE:
                        case INCREASE_ATTRIBUTE:
                            buff = new Buff(
                                    be.getDuration(), be.getPercentage(),
                                    be.getEffectType(), be.getAttributeType()
                            );
                            break;
                        default:
                            break;
                    }

                    if (buff != null) {
                        Integer index = tPositionedHero.getHero().getCurrBuffs().indexOf(buff);
                        if (index > -1) {
                            Buff currBuff = tPositionedHero.getHero().getCurrBuffs().get(index);
                            currBuff.setCurrTurn(0);
                            currBuff.setTurnDuration(buff.getTurnDuration());
                            currBuff.setValue(currBuff.getValue() + buff.getValue());

                            BattleEvent battleEvent = new BattleEvent(BUFF_REFRESH);
                            battleEvent.setBuff(currBuff.duplicate());

                            addBattleLog(new BattleLog(this.turn,
                                    aPositionedHero.duplicate(),
                                    battleEvent, tPositionedHero.duplicate()));
                        } else {

                            tPositionedHero.getHero().addCurrBuff(buff);
                            BattleEvent battleEvent = new BattleEvent(BUFF_START);
                            battleEvent.setBuff(buff);

                            addBattleLog(new BattleLog(this.turn,
                                    aPositionedHero.duplicate(),
                                    battleEvent, tPositionedHero.duplicate()));
                        }

                    }
                }
            });

        }

    }


    private List<BattlePositionedHero> getTurnActionRated() {

        return this.battlePositionedHeroes.stream()
                .filter(t -> !t.getClone() && t.getHero().getCurrHp() > 0)
                .sorted((t, t1) ->
                        (t1.getHero().getCurrSpeed() - t.getHero().getCurrSpeed() != 0) ?
                                t1.getHero().getCurrSpeed() - t.getHero().getCurrSpeed()
                                : t1.getBattleTeamType().equals(BattleTeamType.ATTACK_TEAM) ? 1 : -1)
                .collect(Collectors.toList());

    }

    public void prepareToBattle() {
        this.battlePositionedHeroes.forEach(h -> {
            h.getHero().prepareToBattle();
            h.setEnergy(IdleConstants.ENERGY_GAIN_ON_BATTLE_START);
        });
    }

    private void prepareToTurn(List<BattlePositionedHero> bphs) {
        bphs.forEach(h -> h.getHero().prepareToTurn());
    }

    private void computeBuffs(List<BattlePositionedHero> bphs) {

        for (int i = bphs.size() - 1; i >= 0; i--) {

            if ((this.winner = this.verifyWinner()) != null) {
                return;
            }

            BattlePositionedHero battlePositionedHero = bphs.get(i);

            BattleHero hero = battlePositionedHero.getHero();

            if (hero.getCurrHp() <= 0) {
                LOG.log(Level.INFO, "[HERO_HEAD] HERO DEAD! SKIPPING");
                hero.setCurrBuffs(new ArrayList<>());
                return;
            }

            BattlePositionedHero battlePositionedHeroBackUp = battlePositionedHero.duplicate();

            Iterator<Buff> it = hero.getCurrBuffs().iterator();

            while (it.hasNext()) {
                Buff buff = it.next();
                BattleEvent ret = new BattleEvent(BUFF_COMPUTE, buff.getEffectType());
                switch (buff.getEffectType()) {
                    case HEAL:
                        hero.setCurrHp(hero.getCurrHp() + buff.getValue());
                        ret.setValue(buff.getValue());
                        break;
                    case DMG:
                        hero.setCurrHp(hero.getCurrHp() + buff.getValue());
                        ret.setDamageType(buff.getDamageType());
                        ret.setValue(buff.getValue());
                        break;
                    case FROZEN:
                    case STUN:
                        hero.setCanDoAction(Boolean.FALSE);
                        break;
                    case SILENCE:
                        hero.setCanDoSpecialAction(Boolean.FALSE);
                        break;
                    case INCREASE_ATTRIBUTE:
                        hero.recalcAttribute(buff.getAttributeType(), buff.getValue(), 1);
                        ret.setValue(buff.getValue());
                        ret.setAttributeType(buff.getAttributeType());
                        break;
                    case DECREASE_ATTRIBUTE:
                        hero.recalcAttribute(buff.getAttributeType(), buff.getValue(), -1);
                        ret.setValue(buff.getValue());
                        ret.setAttributeType(buff.getAttributeType());
                        break;

                }
                buff.setCurrTurn(buff.getCurrTurn() + 1);
                addBattleLog(new BattleLog(this.turn, battlePositionedHeroBackUp, ret, battlePositionedHero.duplicate()));

                //Remove expired buffs
                if (buff.getCurrTurn() >= buff.getTurnDuration()) {
                    it.remove();

                    BattleEvent battleEvent = new BattleEvent(BUFF_DONE);
                    battleEvent.setBuff(buff);
                    addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), battleEvent, battlePositionedHero.duplicate()));
                }

            }

        }

    }

    private void computePassives(ActionType actionType) {
        LOG.log(Level.INFO, "->[COMPUTE_PASSIVES_START]");
        this.battlePositionedHeroes.forEach((h) -> {

            BattleHero hero = h.getHero();

            if (!h.getClone() && hero.getHeroType().getPassives() != null) {
                hero.getHeroType().getPassives().stream()
                        .filter(p -> p.getCondiction().getActionType().equals(actionType))
                        .forEach(p -> {
                            LOG.log(Level.INFO, "[COMPUTE_PASSIVES][type] {0}", p.getPassiveType());

                            switch (p.getPassiveType()) {
                                case INCREASE_ATTRIBUTE:
                                    hero.recalcAttribute(p.getResult().getToAttribute(), p.getResult().getPercentage(), 1);
                                    break;
                                case DECREASE_ATTRIBUTE:
                                    hero.recalcAttribute(p.getResult().getToAttribute(), p.getResult().getPercentage(), -1);
                                    break;
                                case TRADE_ATTRIBUTE:
                                    hero.calcTradeAttribute(p.getResult().getFromAttribute(),
                                            p.getResult().getToAttribute(), p.getResult().getPercentage());
                                    break;
                                case UNIQUE:
                                    Integer perc = p.getCondiction().getPercentage();
                                    if (perc != null) {
                                        if (hero.getMissingAttributePercentage(AttributeType.HP) >= perc) {
                                            addBattleLog(new BattleLog(this.turn, h.duplicate(), new BattleEvent(ACTION_START)));
                                            this.doAction(h, p.getAction());
                                            addBattleLog(new BattleLog(this.turn, h.duplicate(), new BattleEvent(ACTION_END)));
                                        }
                                    } else {
                                        addBattleLog(new BattleLog(this.turn, h.duplicate(), new BattleEvent(ACTION_START)));
                                        this.doAction(h, p.getAction());
                                        addBattleLog(new BattleLog(this.turn, h.duplicate(), new BattleEvent(ACTION_END)));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        });
            }
        });
        LOG.log(Level.INFO, "<-[COMPUTE_PASSIVES_END]");
    }

    @Override
    public String toString() {
        return "B{" + "t=" + turn + ", w=" + winner + ", uuid=" + uuid + '}';
    }

}
