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
import com.idle.game.core.hero.Unit;
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
import static com.idle.game.core.action.type.SubActionType.*;
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

    private final List<Log> log;
    private Integer turn = 1;
    private final BattleFormation attackFormation;
    private final BattleFormation defenseFormation;
    private final List<BattleUnit> battleUnits;
    private BattleFormation winner;

    public Battle() {
        this.log = null;
        this.attackFormation = null;
        this.defenseFormation = null;
        this.battleUnits = null;
    }

    public Battle(BattleFormation attackForm, BattleFormation defenseForm) {
        assert attackForm != null : "Attack formation can not be null, BUG";
        assert defenseForm != null : "Defense formation can not be null, BUG";

        this.attackFormation = attackForm;
        this.attackFormation.setFormationType(FormationType.ATTACK);
        this.defenseFormation = defenseForm;
        this.defenseFormation.setFormationType(FormationType.DEFENSE);

        this.battleUnits = new ArrayList<>();

        this.attackFormation.getHeroes().forEach(t -> {
            t.setBattleTeamType(ATTACK_TEAM);
            this.battleUnits.add(t.duplicate(Boolean.FALSE));
        });
        this.defenseFormation.getHeroes().forEach(t -> {
            t.setBattleTeamType(DEFENSE_TEAM);
            this.battleUnits.add(t.duplicate(Boolean.FALSE));
        });

        this.log = new ArrayList<>();
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

    private void addLog(Log bl) {

        switch (bl.getEvent().getActionType()) {
            case DMG:
            case HEAL:
                if (bl.getEvent().getSubType() != null) {
                    LOG.log(Level.INFO, "[{0}][{1}] value={2}, target{3}",
                            new Object[]{bl.getEvent().getActionType(), bl.getEvent().getSubType(),
                                    bl.getEvent().getValue(), bl.getHeroesTarget()});
                } else {
                    LOG.log(Level.INFO, "[{0}] value={1}, target{2}",
                            new Object[]{bl.getEvent().getActionType(), bl.getEvent().getValue(), bl.getHeroesTarget()});
                }
                break;
            case BUFF_START:
                LOG.log(Level.INFO, "[{0}]", bl.getEvent().getActionType().toString());
                break;
            default:
                if (bl.getEvent().getActionType().toString().contains("START")) {
                    LOG.log(Level.INFO, "->[{0}]", bl.getEvent().getActionType().toString());
                } else {
                    LOG.log(Level.INFO, "<-[{0}]", bl.getEvent().getActionType().toString());
                }
                break;
        }
        computePassives(bl.getEvent().getActionType());

        this.log.add(bl);
    }

    public List<Log> getLog() {
        return log;
    }

    public BattleFormation doBattle() {

        LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);

        addLog(new Log(this.turn, new Event(BATTLE_START)));

        addLog(new Log(this.turn, new Event(PREPARE_TO_BATTLE_START)));
        prepareToBattle();
        addLog(new Log(this.turn, new Event(PREPARE_TO_BATTLE_END)));

        doTurn();

        LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);
        LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER_BATTLE);

        LOG.log(Level.INFO, "[battleLog] {0}", this);

        for (int i = 0; i < log.size(); i++) {
            LOG.log(Level.INFO, "[battleLog][index={0}] {1}", new Object[]{i, log.get(i)});
        }

        return winner;

    }

    private void doTurn() {

        Boolean lastTurn = Boolean.FALSE;

        addLog(new Log(this.turn, new Event(TURN_START)));
        LOG.log(Level.INFO, "[TURN] {0}", turn);

        List<BattleUnit> turnActionRated = getTurnActionRated();
        LOG.log(Level.INFO, "[TURN_ACTION_RATED] {0}", turnActionRated);

        addLog(new Log(this.turn, new Event(PREPARE_TO_TURN_START)));
        prepareToTurn(turnActionRated);
        addLog(new Log(this.turn, new Event(PREPARE_TO_TURN_END)));

        addLog(new Log(this.turn, new Event(COMPUTE_BUFF_START)));
        computeBuffs(turnActionRated);
        addLog(new Log(this.turn, new Event(COMPUTE_BUFF_END)));

        if (this.winner == null) {
            for (int i = 0; i < turnActionRated.size(); i++) {
                BattleUnit bph = turnActionRated.get(i);
                addLog(new Log(this.turn, bph.duplicate(), new Event(ACTION_START)));
                LOG.log(Level.INFO, "[BPH] {0}", turnActionRated.get(i));
                doAction(bph);
                addLog(new Log(this.turn, bph.duplicate(), new Event(ACTION_END)));
            }
        }
        addLog(new Log(this.turn, new Event(TURN_END)));

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
            this.addLog(new Log(this.turn, new Event(BATTLE_END)));
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

    private void doAction(BattleUnit attacker) {
        doAction(attacker, null);
    }

    private void doAction(BattleUnit attacker, Action customAction) {

        if (attacker.getCurrHp() <= 0) {
            LOG.log(Level.INFO, "[HERO_HEAD] HERO DEAD! SKIPPING");
            return;
        }

        if (!attacker.getCanDoAction()) {
            LOG.log(Level.INFO, "[HERO_CAN_NOT_DO_ACTION] SKIPPING");
            return;
        }

        Action action = customAction != null ? customAction : attacker.nextAction();

        LOG.log(Level.INFO, "[ACTION] {0}", action);

        calculateAction(attacker, action);

        calculateSecondaryActions(attacker, action);

    }

    public void doDamage(Event be, Double critDmgModifier,
                         ActionEffect ae, BattleUnit target) {

        assert ae.getDamageType() != null : "DamageType of action effect can not be null, BUG";
        assert ae.getValue() != null : "Value of action effect can not be null, BUG";
        assert ae.getAccuracy() != null : "Accuracy of action effect can not be null, BUG";


        if (!ae.getCanBeDodge()
                || target.getCurrDodgeChance() == 0
                || DiceUtil.random(100) > target.getCurrDodgeChance()) {
            be.setSubType(SubActionType.DODGE);
            return;
        }

        if (DiceUtil.random(100d) > ae.getAccuracy()) {
            be.setSubType(SubActionType.MISS);
            return;
        }

        Double dmgReduction = target.getDmgDefenseReduction(ae.getDamageType());

        if (critDmgModifier >= 1.0) {
            be.setSubType(SubActionType.CRITICAL);
        }

        be.setValue(-(int) (critDmgModifier * ae.getValue() * dmgReduction / 100));
        be.setDamageType(ae.getDamageType());

        if (target.damage(be.getValue())) {
            be.setSubType(DEATH);
        }


    }

    public void doHeal(Event be, ActionEffect ae, BattleUnit target) {

        be.setValue(ae.getValue());
        be.setDamageType(ae.getDamageType());

        target.heal(be.getValue());
        LOG.log(Level.INFO, "[HEAL, value={0}] HEAL {0} TO HERO {1}", new Object[]{be.getValue(), target.getUnit()});

    }

    private List<BattleUnit> getTargets(BattleUnit attacker, ActionEffect ae) {
        assert ae.getTargetType() != null : "Target of action can not be null, BUG";

        BattleTeamType battleTeamTypeTarget = ae.getOverSameTeam() ? attacker.getBattleTeamType() : attacker.getBattleTeamType().getOpposite();

        return ae.getTargetType().getTarget().get(battleTeamTypeTarget, battleUnits, attacker);
    }

    private void calculateActionEffect(BattleUnit attacker, ActionEffect ae) {

        List<BattleUnit> targets = getTargets(attacker, ae);

        if (targets != null && !targets.isEmpty()) {

            for (BattleUnit target : targets) {

                Event be = new Event(ae.getType());
                switch (ae.getType()) {
                    case DMG:
                        Double critDmgModifier = attacker.rollCriticalHit();
                        doDamage(be, critDmgModifier, ae, target);
                        break;
                    case HEAL:
                        doHeal(be, ae, target);
                        break;
                }

                if (!be.getSubType().equals(MISS) && be.getSubType().equals(DODGE)) {
                    calculateBuffs(attacker, ae, target);
                }

                addLog(new Log(this.turn, attacker.duplicate(), be, target.duplicate()));
            }

        }
    }

    private void calculateAction(BattleUnit attacker, Action a) {
        final ActionEffect actionMainEffect = a.getMainActionEffect();
        LOG.log(Level.INFO, "[ACTION_EFFECT] {0}", actionMainEffect);

        calculateActionEffect(attacker, actionMainEffect);

    }

    private void calculateSecondaryActions(BattleUnit attacker, Action a) {
        final List<ActionEffect> secondaryActions = a.getSecondaryActionsEffects();

        if (secondaryActions != null) {
            for (ActionEffect secondaryAction : secondaryActions) {
                LOG.log(Level.INFO, "[SECONDARY_ACTION_EFFECT] {0}", secondaryAction);

                calculateActionEffect(attacker, secondaryAction);
            }
        }

    }

    private void calculateBuffs(BattleUnit attacker, ActionEffect ae, BattleUnit target) {

        if (ae.getBuffEffects() != null) {
            ae.getBuffEffects().forEach((be) -> {

                if (be.getChance() == null
                        || be.getChance() == 0
                        || DiceUtil.random(100) <= be.getChance()) {

                    Buff buff = null;
                    switch (be.getEffectType()) {
                        case DMG:
                            buff = new Buff(be.getDuration(), (be.getValue() * -1), be.getEffectType(), ae.getDamageType());
                            break;
                        case HEAL:
                            buff = new Buff(be.getDuration(), be.getValue(), be.getEffectType());
                            break;
                        case FROZEN:
                        case STUN:
                        case SILENCE:
                            buff = new Buff(be.getDuration(), be.getEffectType());
                            break;
                        case DECREASE_ATTRIBUTE:
                        case INCREASE_ATTRIBUTE:
                            buff = new Buff(be.getDuration(), be.getValue(), be.getEffectType(), be.getAttributeType());
                            break;
                        default:
                            break;
                    }

                    if (buff != null) {
                        Integer index = target.getCurrBuffs().indexOf(buff);
                        Event event;
                        if (index > -1) {
                            Buff currBuff = target.getCurrBuffs().get(index);
                            currBuff.setCurrTurn(0);
                            currBuff.setTurnDuration(buff.getTurnDuration());
                            currBuff.setValue(currBuff.getValue() + buff.getValue());

                            event = new Event(BUFF_REFRESH);
                            event.setBuff(currBuff.duplicate());

                        } else {

                            target.addCurrBuff(buff);
                            event = new Event(BUFF_START);
                            event.setBuff(buff);

                        }
                        addLog(new Log(this.turn, attacker.duplicate(), event, target.duplicate()));

                    }
                }
            });

        }

    }


    private List<BattleUnit> getTurnActionRated() {

        return this.battleUnits.stream()
                .filter(t -> !t.getClone() && t.getCurrHp() > 0)
                .sorted((t, t1) ->
                        (t1.getCurrSpeed() - t.getCurrSpeed() != 0) ?
                                t1.getCurrSpeed() - t.getCurrSpeed()
                                : t1.getBattleTeamType().equals(BattleTeamType.ATTACK_TEAM) ? 1 : -1)
                .collect(Collectors.toList());

    }

    public void prepareToBattle() {
        this.battleUnits.forEach(battleUnit -> battleUnit.prepareToBattle());
    }

    private void prepareToTurn(List<BattleUnit> battleUnits) {
        battleUnits.forEach(battleUnit -> battleUnit.prepareToTurn());
    }

    private void computeBuffs(List<BattleUnit> battleUnits) {

        for (int i = battleUnits.size() - 1; i >= 0; i--) {

            if ((this.winner = this.verifyWinner()) != null) {
                return;
            }

            BattleUnit battleUnit = battleUnits.get(i);

            if (battleUnit.getCurrHp() <= 0) {
                LOG.log(Level.INFO, "[HERO_HEAD] HERO DEAD! SKIPPING");
                battleUnit.setCurrBuffs(new ArrayList<>());
                return;
            }

            BattleUnit battleUnitBackUp = battleUnit.duplicate();

            Iterator<Buff> it = battleUnit.getCurrBuffs().iterator();

            while (it.hasNext()) {
                Buff buff = it.next();
                Event ret = new Event(BUFF_COMPUTE, buff.getEffectType());
                switch (buff.getEffectType()) {
                    case HEAL:
                        battleUnit.setCurrHp(battleUnit.getCurrHp() + buff.getValue());
                        ret.setValue(buff.getValue());
                        break;
                    case DMG:
                        battleUnit.setCurrHp(battleUnit.getCurrHp() + buff.getValue());
                        ret.setDamageType(buff.getDamageType());
                        ret.setValue(buff.getValue());
                        break;
                    case FROZEN:
                    case STUN:
                    case SILENCE:
                        battleUnit.setCanDoAction(Boolean.FALSE);
                        break;
                    case INCREASE_ATTRIBUTE:
                        battleUnit.recalcAttribute(buff.getAttributeType(), buff.getValue(), 1);
                        ret.setValue(buff.getValue());
                        ret.setAttributeType(buff.getAttributeType());
                        break;
                    case DECREASE_ATTRIBUTE:
                        battleUnit.recalcAttribute(buff.getAttributeType(), buff.getValue(), -1);
                        ret.setValue(buff.getValue());
                        ret.setAttributeType(buff.getAttributeType());
                        break;

                }
                buff.setCurrTurn(buff.getCurrTurn() + 1);
                addLog(new Log(this.turn, battleUnitBackUp, ret, battleUnit.duplicate()));

                //Remove expired buffs
                if (buff.getCurrTurn() >= buff.getTurnDuration()) {
                    it.remove();

                    Event event = new Event(BUFF_DONE);
                    event.setBuff(buff);
                    addLog(new Log(this.turn, battleUnit.duplicate(), event, battleUnit.duplicate()));
                }

            }

        }

    }

    private void computePassives(ActionType actionType) {
        LOG.log(Level.INFO, "->[COMPUTE_PASSIVES_START]");
        this.battleUnits.forEach((battleUnit) -> {

            Unit hero = battleUnit.getUnit();

            if (!battleUnit.getClone() && hero.getUnitType().getPassives() != null) {
                hero.getUnitType().getPassives().stream()
                        .filter(p -> p.getCondiction().getActionType().equals(actionType))
                        .forEach(passive -> {
                            LOG.log(Level.INFO, "[COMPUTE_PASSIVES][type] {0}", passive.getPassiveType());

                            switch (passive.getPassiveType()) {
                                case INCREASE_ATTRIBUTE:
                                    battleUnit.recalcAttribute(passive.getResult().getToAttribute(), passive.getResult().getPercentage(), 1);
                                    break;
                                case DECREASE_ATTRIBUTE:
                                    battleUnit.recalcAttribute(passive.getResult().getToAttribute(), passive.getResult().getPercentage(), -1);
                                    break;
                                case TRADE_ATTRIBUTE:
                                    battleUnit.calcTradeAttribute(passive.getResult().getFromAttribute(),
                                            passive.getResult().getToAttribute(), passive.getResult().getPercentage());
                                    break;
                                case UNIQUE:
                                    Integer perc = passive.getCondiction().getPercentage();
                                    if (perc != null) {
                                        if (battleUnit.getMissingAttributePercentage(AttributeType.HP) >= perc) {
                                            addLog(new Log(this.turn, battleUnit.duplicate(), new Event(ACTION_START)));
                                            this.doAction(battleUnit, passive.getAction());
                                            addLog(new Log(this.turn, battleUnit.duplicate(), new Event(ACTION_END)));
                                        }
                                    } else {
                                        addLog(new Log(this.turn, battleUnit.duplicate(), new Event(ACTION_START)));
                                        this.doAction(battleUnit, passive.getAction());
                                        addLog(new Log(this.turn, battleUnit.duplicate(), new Event(ACTION_END)));
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
