package com.idle.game.core.battle;

import com.idle.game.core.BaseObject;
import com.idle.game.core.formation.Formation;
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
import static com.idle.game.core.constant.IdleConstants.DEFAULT_ACTION;
import static com.idle.game.core.constant.IdleConstants.LOG;
import static com.idle.game.core.action.type.ActionType.BATTLE_END;
import static com.idle.game.core.action.type.ActionType.BATTLE_START;
import static com.idle.game.core.action.type.ActionType.BUFF_DONE;
import static com.idle.game.core.formation.type.FormationPositionType.BACK;
import static com.idle.game.core.formation.type.FormationPositionType.FRONT;
import static com.idle.game.core.buff.type.BuffType.BUFF;
import static com.idle.game.core.buff.type.BuffType.DEBUFF;
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

/**
 *
 * @author rafael
 */
public class Battle extends BaseObject {

    private final List<BattleLog> battleLog;
    private Integer turn = 1;
    private final Formation attackFormation;
    private final Formation defenseFormation;
    private final List<BattlePositionedHero> battlePositionedHeroes;
    private Formation winner;

    public Battle(Formation attackForm, Formation defenseForm) throws Exception {
        assert attackForm != null : "Attack formation can not be null, BUG";
        assert defenseForm != null : "Defense formation can not be null, BUG";

        this.attackFormation = attackForm;
        this.attackFormation.setFormationType(FormationType.ATTACK);
        this.defenseFormation = defenseForm;
        this.defenseFormation.setFormationType(FormationType.DEFENSE);

        this.battlePositionedHeroes = new ArrayList<>();

        this.attackFormation.getHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.ATTACK_TEAM, t.getBattlePosition(), t.getHero()));
        });
        this.defenseFormation.getHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.DEFENSE_TEAM, t.getBattlePosition(), t.getHero()));
        });

        this.battleLog = new ArrayList<>();
    }

    public Formation getAttackFormation() {
        return this.attackFormation;
    }

    public Formation getDefenseFormation() {
        return this.defenseFormation;
    }

    public Formation getWinner() {
        return winner;
    }

    public Integer getTurn() {
        return turn;
    }

    private void addBattleLog(BattleLog bl) {
        this.battleLog.add(bl);
    }

    public List<BattleLog> getBattleLog() {
        return battleLog;
    }

    public Formation doBattle() throws Exception {
        LOG.log(Level.FINEST, "[battle] INIT");
        addBattleLog(new BattleLog(this.turn, new BattleEvent(BATTLE_START)));
        prepareToBattle();
        doTurn();

        LOG.log(Level.FINEST, "[battle] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[battleLog] {0}", this);
        getBattleLog().forEach((BattleLog t) -> {
            LOG.log(Level.FINEST, "[battleLog] {0}", t);
        });

        return winner;

    }

    private void doTurn() throws Exception {

        Boolean lastTurn = Boolean.FALSE;

        LOG.log(Level.FINEST, "[turn] INIT {0}", turn);

        List<BattlePositionedHero> turnActionRated = getTurnActionRated();
        LOG.log(Level.FINEST, "[turnActionRated] {0}", turnActionRated);
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[prepareToTurn] INIT");
        prepareToTurn(turnActionRated);
        LOG.log(Level.FINEST, "[prepareToTurn] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[passives] INIT");
        computePassives(turnActionRated);
        LOG.log(Level.FINEST, "[passives] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[buffs] INIT");
        computeBuffs(turnActionRated);
        LOG.log(Level.FINEST, "[buffs] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[actions] INIT");

        if (this.winner == null) {
            for (int i = 0; i < turnActionRated.size(); i++) {
                LOG.log(Level.FINEST, "[action] INIT {0}", turnActionRated.get(i));
                doAction(turnActionRated.get(i));
                LOG.log(Level.FINEST, "[action] END");
                LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
            }
        }
        LOG.log(Level.FINEST, "[actions] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[turn] {0} END", turn);
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[event=VERIFY_WINNER] INIT");
        this.winner = verifyWinner();
        LOG.log(Level.FINEST, "[event=VERIFY_WINNER] {0}", this.winner);
        LOG.log(Level.FINEST, "[event=VERIFY_WINNER] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
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
            LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
            LOG.log(Level.FINEST, "[event=WINNER] WINNER: {0} on turn: {1}", new Object[]{this.winner, this.turn});
            LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
            this.addBattleLog(new BattleLog(this.turn, new BattleEvent(BATTLE_END)));
        }

    }

    private Formation verifyWinner() {
        if (getHeroesByBattleTeamTypeCanDoAction(ATTACK_TEAM).count() <= 0) {
            return defenseFormation;
        } else if (getHeroesByBattleTeamTypeCanDoAction(DEFENSE_TEAM).count() <= 0) {
            return attackFormation;
        }
        return null;
    }

    private void doAction(BattlePositionedHero aPositionedHero) {

        if (aPositionedHero.getHero().getCurrHp() <= 0) {
            LOG.log(Level.FINEST, "[event=HERO_HEAD] HERO DEAD! SKIPPING");
            return;
        }

        if (!aPositionedHero.getHero().getCanDoAction()) {
            LOG.log(Level.FINEST, "[event=HERO_CAN_NOT_DO_ACTION] SKIPPING");
            return;
        }

        BattleHero aHero = aPositionedHero.getHero();

        Action action;
        if (aPositionedHero.getEnergy() >= IdleConstants.MAX_ENERGY
                && aHero.getHeroType().getSpecialAction() != null) {
            action = aHero.getHeroType().getSpecialAction();
        } else {
            if (aHero.getHeroType().getDefaultAction() == null) {
                action = DEFAULT_ACTION;
            } else {
                action = aHero.getHeroType().getDefaultAction();
            }
        }

        LOG.log(Level.FINEST, "[event=ACTION] {0}", action);

        calculateAction(aPositionedHero, action);

        calculateSecondaryActions(aPositionedHero, action);

    }

    private Double criticalDamage(BattleEvent ret, BattleHero aHero) {
        Double dmgModifier = 1.0d;

        if (aHero.getCurrCritChance() > 0
                && DiceUtil.random(100) <= aHero.getCurrCritChance()) {
            dmgModifier += aHero.getCurrCritDamage() / 100d;
            ret.setSubType(SubActionType.CRITICAL);
            LOG.log(Level.FINEST, "[event=CRITICAL, value={0}] CRITICAL, MODIFIER {0}", dmgModifier);
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
            LOG.log(Level.FINEST, "[event=DMG_TO_DIE, value={0}] DMG {0} TO HERO, HE DIE {1}", new Object[]{be.getValue(), tPositionedHero});
        } else {
            tPositionedHero.getHero().setCurrHp(newHp);
            if (tPositionedHero.getEnergy() < IdleConstants.MAX_ENERGY) {
                tPositionedHero.setEnergy(tPositionedHero.getEnergy() + IdleConstants.ENERGY_GAIN_ON_ATTACK);
            }
            LOG.log(Level.FINEST, "[event=DMG, value={0}] DMG {0} TO HERO {1}", new Object[]{be.getValue(), tPositionedHero});
        }

    }

    private void doHeal(BattleEvent be, BattlePositionedHero aPositionedHero, ActionEffect ae, BattlePositionedHero tPositionedHero) {

        be.setValue((int) ((aPositionedHero.getHero().getCurrDmg() * ae.getPercentage() / 100f)));
        be.setDamageType(ae.getDamageType());

        aPositionedHero.getHero().setCurrHp(aPositionedHero.getHero().getCurrHp() + be.getValue());
        LOG.log(Level.FINEST, "[event=HEAL, value={0}] HEAL {0} TO HERO {1}", new Object[]{be.getValue(), aPositionedHero.getHero()});

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

                    calculateBuffs(aPositionedHero, ae, tPositionedHero);

                } else {
                    be.setSubType(SubActionType.DODGE);
                    LOG.log(Level.FINEST, "[event=DODGE] DODGE");
                }

                addBattleLog(new BattleLog(this.turn, aPositionedHero.duplicate(), be, tPositionedHero.duplicate()));
            });

        }
    }

    private void calculateAction(BattlePositionedHero aPositionedHero, Action a) {
        final ActionEffect actionMainEffect = a.getMainActionEffect();
        LOG.log(Level.FINEST, "[event=ACTION_EFFECT] {0}", actionMainEffect);

        calculateActionEffect(aPositionedHero, actionMainEffect, a.getSpecial());

    }

    private void calculateSecondaryActions(BattlePositionedHero aPositionedHero, Action a) {
        final List<ActionEffect> secondaryActions = a.getSecundaryActionsEffects();

        if (secondaryActions != null) {
            secondaryActions.forEach((sa) -> {
                LOG.log(Level.FINEST, "[event=SECONDARY_ACTION_EFFECT] {0}", sa);

                calculateActionEffect(aPositionedHero, sa, a.getSpecial());
            });
        }

    }

    private void calculateBuffs(BattlePositionedHero aPositionedHero, ActionEffect ae, BattlePositionedHero tPositionedHero) {

        if (ae.getBuffEffects() != null) {
            ae.getBuffEffects().forEach((buff) -> {

                if (buff.getChance() == null
                        || buff.getChance() == 0
                        || DiceUtil.random(100) <= buff.getChance()) {

                    tPositionedHero.getHero().addBuff(new Buff(buff.getDuration(),
                            (int) (aPositionedHero.getHero().getCurrDmg() * buff.getPercentage() / 100d),
                            buff.getActionType(), ae.getDamageType(), buff.getBuffType()));
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

        LOG.log(Level.FINEST, "[target] {0}", targets);

        return targets;
    }

    private Double getDmgMagicResistReduction(BattleHero h) {
        return 100d / (100d + h.getCurrMagicResist());
    }

    private Double getDmgAmorReduction(BattleHero h) {
        return 100d / (100d + h.getCurrArmor());
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
                    .skip(DiceUtil.random(size)).findFirst();
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
                    return t1.getBattlePosition().getOrder() - t.getBattlePosition().getOrder();
                }).findFirst();

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private List<BattlePositionedHero> getBackLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return ph.getBattlePosition().getType().equals(BACK);
        }).collect(Collectors.toList());

    }

    private List<BattlePositionedHero> getFrontLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return ph.getBattlePosition().getType().equals(FRONT);
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
        });
    }

    private void prepareToTurn(List<BattlePositionedHero> bphs) {
        bphs.forEach((h) -> {
            h.getHero().prepareToTurn();
        });
    }

    private void computePassives(List<BattlePositionedHero> bphs) throws Exception {
        bphs.forEach((h) -> {
            h.getHero().calcPassives();
        });

    }

    private void computeBuffs(List<BattlePositionedHero> bphs) {

        for (int i = bphs.size() - 1; i >= 0; i--) {

            BattlePositionedHero battlePositionedHero = bphs.get(i);
            BattleHero hero = battlePositionedHero.getHero();

            if ((this.winner = this.verifyWinner()) != null) {
                return;
            }

            Iterator<Buff> it = hero.getCurrBuffs().iterator();

            while (it.hasNext()) {
                Buff buff = it.next();
                BattleEvent ret = new BattleEvent(buff.getEffectType(), buff.getValue(), buff.getDamageType());
                switch (buff.getEffectType()) {
                    case HEAL:
                        hero.setCurrHp(hero.getCurrHp() + buff.getValue());
                        ret.setBuffType(BUFF);
                        break;
                    case DMG:
                        hero.setCurrHp(hero.getCurrHp() - buff.getValue());
                        ret.setBuffType(DEBUFF);
                        break;
                    case FROZEN:
                    case STUN:
                    case SILENCE:
                        hero.setCanDoAction(Boolean.FALSE);
                        break;

                }
                buff.setCurrTurn(buff.getCurrTurn() + 1);
                addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), ret, battlePositionedHero.duplicate()));

                //Remove expired buffs
                if (buff.getCurrTurn() >= buff.getTurnDuration()) {
                    it.remove();
                    addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), new BattleEvent(BUFF_DONE), buff));
                }

            }

        }

    }

    @Override
    public String toString() {
        return "B{" + "t=" + turn + ", w=" + winner + ", uuid=" + uuid + '}';
    }

}
