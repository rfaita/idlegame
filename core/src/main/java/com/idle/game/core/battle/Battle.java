package com.idle.game.core.battle;

import com.idle.game.core.BaseObject;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.formation.type.FormationType;
import com.idle.game.core.type.TargetType;
import com.idle.game.core.action.type.SubActionType;
import com.idle.game.core.buff.Buff;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.Action;
import com.idle.game.core.action.type.ActionType;
import static com.idle.game.core.constant.IdleConstants.DEFAULT_ACTION;
import static com.idle.game.core.action.type.ActionType.*;
import static com.idle.game.core.formation.type.FormationPositionType.BACK;
import static com.idle.game.core.formation.type.FormationPositionType.FRONT;
import com.idle.game.core.util.DiceUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static com.idle.game.core.battle.type.BattleTeamType.ATTACK_TEAM;
import static com.idle.game.core.battle.type.BattleTeamType.DEFENSE_TEAM;
import static com.idle.game.core.action.type.SubActionType.DEATH;
import static com.idle.game.core.constant.IdleConstants.LOG;
import static com.idle.game.core.formation.type.FormationType.ATTACK;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.AttributeType;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author rafael
 */
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

        this.attackFormation.getHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.ATTACK_TEAM, t.getPosition(), t.getHero()));
        });
        this.defenseFormation.getHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.DEFENSE_TEAM, t.getPosition(), t.getHero()));
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
        addBattleLog(new BattleLog(this.turn, new BattleEvent(ActionType.COMPUTE_BUFF_END)));

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
        if (getHeroesByBattleTeamTypeCanDoAction(ATTACK_TEAM).count() <= 0) {
            return defenseFormation;
        } else if (getHeroesByBattleTeamTypeCanDoAction(DEFENSE_TEAM).count() <= 0) {
            return attackFormation;
        }
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

        BattleHero aHero = aPositionedHero.getHero();

        Action action;
        if (customAction != null) {
            action = customAction;
        } else if (aPositionedHero.getEnergy() >= IdleConstants.MAX_ENERGY
                && aHero.getHeroType().getSpecialAction() != null
                && aHero.getCanDoSpecialAction()) {
            action = aHero.getHeroType().getSpecialAction();
        } else {
            if (aHero.getHeroType().getDefaultAction() == null) {
                action = DEFAULT_ACTION;
            } else {
                action = aHero.getHeroType().getDefaultAction();
            }
        }

        LOG.log(Level.INFO, "[ACTION] {0}", action);

        calculateAction(aPositionedHero, action);

        calculateSecondaryActions(aPositionedHero, action);

    }

    private Double criticalDamage(BattleEvent ret, BattleHero aHero) {
        Double dmgModifier = 1.0d;

        if (aHero.getCurrCritChance() > 0
                && DiceUtil.random(100) <= aHero.getCurrCritChance()) {
            dmgModifier += aHero.getCurrCritDamage() / 100d;
            ret.setSubType(SubActionType.CRITICAL);
        }

        return dmgModifier;
    }

    private void doDamage(BattleEvent be, BattlePositionedHero aPositionedHero, ActionEffect ae, BattlePositionedHero tPositionedHero) {

        DamageType dt = ae.getDamageType() != null ? ae.getDamageType() : aPositionedHero.getHero().getHeroType().getDamageType();

        Double dmgReduction = (dt.equals(DamageType.PHYSICAL)
                ? getDmgAmorReduction(tPositionedHero.getHero()) : getDmgMagicResistReduction(tPositionedHero.getHero()));

        be.setValue(-(int) ((aPositionedHero.getHero().getCurrDmg() * criticalDamage(be, aPositionedHero.getHero()) * ae.getPercentage() / 100f) * dmgReduction));
        be.setDamageType(ae.getDamageType());

        Integer newHp = tPositionedHero.getHero().getCurrHp() + be.getValue();
        if (newHp < 0) {
            tPositionedHero.getHero().setCurrHp(0);
            tPositionedHero.setEnergy(0);
            be.setSubType(DEATH);
        } else {
            tPositionedHero.getHero().setCurrHp(newHp);
            if (tPositionedHero.getEnergy() < IdleConstants.MAX_ENERGY) {
                tPositionedHero.setEnergy(tPositionedHero.getEnergy() + IdleConstants.ENERGY_GAIN_ON_ATTACK);
            }
        }

    }

    private void doHeal(BattleEvent be, BattlePositionedHero aPositionedHero, ActionEffect ae, BattlePositionedHero tPositionedHero) {

        be.setValue((int) ((aPositionedHero.getHero().getCurrDmg() * ae.getPercentage() / 100f)));
        be.setDamageType(ae.getDamageType());

        tPositionedHero.getHero().setCurrHp(tPositionedHero.getHero().getCurrHp() + be.getValue());
        LOG.log(Level.INFO, "[HEAL, value={0}] HEAL {0} TO HERO {1}", new Object[]{be.getValue(), tPositionedHero.getHero()});

    }

    private void calculateActionEffect(BattlePositionedHero aPositionedHero, ActionEffect ae, Boolean special) {
        assert ae.getTargetType() != null : "Target of action can not be null, BUG";

        List<BattlePositionedHero> targets = getTargetsOfActionEffect(ae, aPositionedHero.getBattleTeamType());

        if (targets != null && !targets.isEmpty()) {

            final Integer energyGain = IdleConstants.ENERGY_GAIN_DOING_ATTACK / targets.size();

            targets.forEach((tPositionedHero) -> {

                BattleEvent be = new BattleEvent(ae.getType());

                if (!ae.getCanBeDodge()
                        || tPositionedHero.getHero().getCurrDodgeChance() == 0
                        || DiceUtil.random(100) > tPositionedHero.getHero().getCurrDodgeChance()) {

                    switch (ae.getType()) {
                        case DMG:
                            doDamage(be, aPositionedHero, ae, tPositionedHero);
                            break;
                        case HEAL:
                            doHeal(be, aPositionedHero, ae, tPositionedHero);
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
        final List<ActionEffect> secondaryActions = a.getSecundaryActionsEffects();

        if (secondaryActions != null) {
            secondaryActions.forEach((sa) -> {
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

    private List<BattlePositionedHero> getTargetsOfActionEffect(ActionEffect ae, BattleTeamType battleTeamType) {
        battleTeamType = ae.getOverSameTeam() ? battleTeamType : battleTeamType.getOpposite();

        return getTargets(ae.getTargetType(), battleTeamType);
    }

    private List<BattlePositionedHero> getTargets(TargetType targetType, BattleTeamType battleTeamTypeTarget) {

        List<BattlePositionedHero> targets = new ArrayList<>();
        BattlePositionedHero t;
        switch (targetType) {
            case ALL:
                targets.addAll(getPositionedHeroes(battleTeamTypeTarget));
                break;
            case BACK_LINE:
                targets.addAll(getBackLinePositionedHeroes(battleTeamTypeTarget));
                break;
            case FRONT_LINE:
                targets.addAll(getFrontLinePositionedHeroes(battleTeamTypeTarget));
                break;
            case LESS_LIFE:
                t = getHeroLessLife(battleTeamTypeTarget);
                if (t != null) {
                    targets.add(t);
                }
                break;
            case LESS_PERC_LIFE:
                t = getHeroLessLifePerc(battleTeamTypeTarget);
                if (t != null) {
                    targets.add(t);
                }
                break;
            case MORE_LIFE:
                t = getHeroMoreLife(battleTeamTypeTarget);
                if (t != null) {
                    targets.add(t);
                }
                break;
            case MORE_PERC_LIFE:
                t = getHeroMoreLifePerc(battleTeamTypeTarget);
                if (t != null) {
                    targets.add(t);
                }
                break;
            case DEATH_BACK_LINE:
                break;
            case DEATH_FRONT_LINE:
                break;
            case DEATH_RANDOM:
                break;
            case FIRST_ONE:
                t = getFirstOnePositionedHero(battleTeamTypeTarget);
                if (t != null) {
                    targets.add(t);
                }
                break;
            case RANDOM:
            default:
                t = getRandomHero(battleTeamTypeTarget);
                if (t != null) {
                    targets.add(t);
                }
                break;
        }

        return targets;
    }

    private Double getDmgMagicResistReduction(BattleHero h) {
        return 6000d / (6000d + h.getCurrMagicResist());
    }

    private Double getDmgAmorReduction(BattleHero h) {
        return 6000d / (6000d + h.getCurrArmor());
    }

    private BattlePositionedHero getHeroLessLifePerc(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrHp() / h.getHero().getHp() < h2.getHero().getCurrHp() / h.getHero().getHp() ? h : h2;
        });

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    private BattlePositionedHero getHeroMoreLifePerc(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrHp() / h.getHero().getHp() > h2.getHero().getCurrHp() / h.getHero().getHp() ? h : h2;
        });

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    private BattlePositionedHero getHeroLessLife(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrHp() < h2.getHero().getCurrHp() ? h : h2;
        });

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private BattlePositionedHero getHeroMoreLife(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrHp() > h2.getHero().getCurrHp() ? h : h2;
        });

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private BattlePositionedHero getRandomHero(BattleTeamType battleTeamType) {

        int size = (int) getHeroesByBattleTeamTypeCanDoAction(battleTeamType).count();

        if (size > 0) {
            Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType)
                    .skip(DiceUtil.random(size - 1)).findFirst();
            try {
                return ret.get();
            } catch (NoSuchElementException e) {
                return null;
            }
        }
        return null;
    }

    private BattlePositionedHero getFirstOnePositionedHero(BattleTeamType battleTeamType) {

        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType)
                .sorted((BattlePositionedHero t, BattlePositionedHero t1) -> {
                    return t1.getPosition().getOrder() - t.getPosition().getOrder();
                }).findFirst();

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private List<BattlePositionedHero> getPositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).collect(Collectors.toList());
    }

    private List<BattlePositionedHero> getBackLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return ph.getPosition().getType().equals(BACK);
        }).collect(Collectors.toList());

    }

    private List<BattlePositionedHero> getFrontLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return ph.getPosition().getType().equals(FRONT);
        }).collect(Collectors.toList());
    }

    private Stream<BattlePositionedHero> getHeroesByBattleTeamTypeCanDoAction(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamType(battleTeamType).filter((BattlePositionedHero t) -> {
            return t.getHero().getCurrHp() > 0;
        });
    }

    private Stream<BattlePositionedHero> getHeroesByBattleTeamType(BattleTeamType battleTeamType) {
        return this.battlePositionedHeroes.stream().filter((BattlePositionedHero t) -> {
            return t.getBattleTeamType().equals(battleTeamType);
        });
    }

    private List<BattlePositionedHero> getTurnActionRated() {

        return this.battlePositionedHeroes.stream().filter((BattlePositionedHero t) -> {
            return t.getHero().getCurrHp() > 0;
        }).sorted((BattlePositionedHero t, BattlePositionedHero t1) -> {
            if (t1.getHero().getCurrSpeed() - t.getHero().getCurrSpeed() != 0) {
                return t1.getHero().getCurrSpeed() - t.getHero().getCurrSpeed();
            } else {
                //Velocidade empatada attack team tem vantagem
                return t1.getBattleTeamType().equals(BattleTeamType.ATTACK_TEAM) ? 1 : -1;
            }
        }).collect(Collectors.toList());

    }

    public void prepareToBattle() {
        this.battlePositionedHeroes.forEach((h) -> {
            h.getHero().prepareToBattle();
            h.setEnergy(IdleConstants.ENERGY_GAIN_ON_BATTLE_START);
        });
    }

    private void prepareToTurn(List<BattlePositionedHero> bphs) {
        bphs.forEach((h) -> {
            h.getHero().prepareToTurn();
        });
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

            if (hero.getHeroType().getPassives() != null) {
                hero.getHeroType().getPassives().stream()
                        .filter((Passive p) -> {
                            return (p.getCondiction().getActionType().equals(actionType));
                        })
                        .forEach((Passive p) -> {
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
