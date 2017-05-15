package com.idle.game.core;

import com.idle.game.core.exception.ValidationException;
import com.idle.game.core.util.DiceUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author rafael
 */
public class Battle extends BaseObject {

    private static final Logger LOG = Logger.getLogger(Battle.class.getName());

    private final List<BattleLog> battleLog;
    private Integer turn = 1;
    private final Player attackPlayer;
    private final Player defensePlayer;
    private final List<BattlePositionedHero> battlePositionedHeroes;
    private Player winner;

    public Battle(Player attackPlayer, Player defensePlayer) throws Exception {
        if (attackPlayer.getAttackFormation() == null) {
            throw new ValidationException("player.do.not.have.attack.formation");
        }
        if (defensePlayer.getDefenseFormation() == null) {
            throw new ValidationException("player.do.not.have.defense.formation");
        }
        this.attackPlayer = attackPlayer;
        this.defensePlayer = defensePlayer;

        this.battlePositionedHeroes = new ArrayList<>();

        this.attackPlayer.getAttackFormation().getPositionedHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.ATTACK, t.getBattlePosition(), t.getHero()));
        });
        this.defensePlayer.getDefenseFormation().getPositionedHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.DEFENSE, t.getBattlePosition(), t.getHero()));
        });

        this.battleLog = new ArrayList<>();
    }

    public Formation getAttackFormation() {
        return attackPlayer.getAttackFormation();
    }

    public Formation getDefenseFormation() {
        return defensePlayer.getDefenseFormation();
    }

    public Player getWinner() {
        return winner;
    }

    public Integer getTurn() {
        return turn;
    }

    public Player getAttackPlayer() {
        return attackPlayer;
    }

    public Player getDefensePlayer() {
        return defensePlayer;
    }

    private void addBattleLog(BattleLog bl) {
        this.battleLog.add(bl);
    }

    public List<BattleLog> getBattleLog() {
        return battleLog;
    }

    public Player doBattle() {
        LOG.log(Level.FINEST, "[battle] INIT");
        this.addBattleLog(new BattleLog(this.turn, new BattleEvent(ActionType.BATTLE_START)));
        this.prepareToBattle();
        doTurn();

        LOG.log(Level.FINEST, "[battle] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        getBattleLog().forEach((BattleLog t) -> {
            LOG.log(Level.FINEST, "[battleLog] {0}", t);
        });

        return winner;

    }

    private void doTurn() {

        Boolean lastTurn = Boolean.FALSE;

        LOG.log(Level.FINEST, "[turn] INIT {0}", turn);

        List<BattlePositionedHero> turnActionRated = getTurnActionRated();
        LOG.log(Level.FINEST, "[turnActionRated] {0}", turnActionRated);
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[buffs] INIT");
        computeBuffs(turnActionRated);
        LOG.log(Level.FINEST, "[buffs] END");
        LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);

        LOG.log(Level.FINEST, "[actions] INIT");

        for (int i = 0; i < turnActionRated.size(); i++) {
            LOG.log(Level.FINEST, "[action] INIT {0}", turnActionRated.get(i));
            doAction(turnActionRated.get(i));
            if (i < turnActionRated.size() - 1) {
                if (calculateDoubleActionChance(turnActionRated.get(i), turnActionRated.get(i + 1))) {
                    LOG.log(Level.FINEST, "[event=DOUBLE_ACTION]");
                    doAction(turnActionRated.get(i));
                }
            }
            LOG.log(Level.FINEST, "[action] END");
            LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
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
                this.winner = this.defensePlayer;
                lastTurn = Boolean.TRUE;
            }
        } else {
            lastTurn = Boolean.TRUE;
        }

        if (lastTurn) {
            LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
            LOG.log(Level.FINEST, "[event=WINNER] WINNER: {0} on turn: {1}", new Object[]{this.winner, this.turn});
            LOG.log(Level.FINEST, IdleConstants.LOG_DELIMITER);
            this.addBattleLog(new BattleLog(this.turn, new BattleEvent(ActionType.BATTLE_END)));
        }

    }

    private Player verifyWinner() {
        if (getHeroesByBattleTeamTypeCanDoAction(BattleTeamType.ATTACK).count() <= 0) {
            return defensePlayer;
        } else if (getHeroesByBattleTeamTypeCanDoAction(BattleTeamType.DEFENSE).count() <= 0) {
            return attackPlayer;
        }
        return null;
    }

    private void doAction(BattlePositionedHero aPositionedHero) {

        if (aPositionedHero.getHero().getCurrentHp() <= 0) {
            LOG.log(Level.FINEST, "[event=HERO_HEAD] HERO DEAD! SKIPPING");
            return;
        }

        if (!aPositionedHero.getHero().getCanDoAction()) {
            LOG.log(Level.FINEST, "[event=HERO_CAN_NOT_DO_ACTION] SKIPPING");
            return;
        }

        Hero aHero = aPositionedHero.getHero();

        Action action = aHero.getHeroType().getDefaultAction();

        final ActionEffect actionMainEffect = action.getMainActionEffect();

        List<BattlePositionedHero> targets = getTargets(
                actionMainEffect.getTargetType() == null ? aHero.getHeroType().getAtittudeType().getTargetType() : actionMainEffect.getTargetType(),
                actionMainEffect.getActionOverSameTeam() ? aPositionedHero.getBattleTeamType() : aPositionedHero.getBattleTeamType().getOpposite());

        LOG.log(Level.FINEST, "[event=ACTION] {0}", action);

        targets.forEach((tPositionedHero) -> {

            Hero tHero = tPositionedHero.getHero();

            BattleEvent ret = new BattleEvent(actionMainEffect.getActionType());

            if (!actionMainEffect.getCanBeDodge()
                    || tHero.getCurrentDodgeChance() == 0 || DiceUtil.random(100) > tHero.getCurrentDodgeChance()) {

                switch (actionMainEffect.getActionType()) {
                    case DMG:
                        doDamage(ret, aHero, actionMainEffect, tHero);
                        break;
                    case HEAL:
                        doHeal(ret, aHero, actionMainEffect, tHero);
                        break;
                }

                calculateBuff(aHero, action, tHero);

            } else {
                ret.setSubType(SubActionType.DODGE);
                LOG.log(Level.FINEST, "[event=DODGE] DODGE");
            }

            this.addBattleLog(new BattleLog(this.turn, aPositionedHero.duplicate(), ret, tPositionedHero.duplicate()));
        });

    }

    private Double calculateModifier(BattleEvent ret, Hero aHero) {
        Double dmgModifier = 1.0d;

        if (aHero.getCurrentCritChance() > 0
                && DiceUtil.random(100) <= aHero.getCurrentCritChance()) {
            dmgModifier += aHero.getCurrentCritDamage() / 100d;
            ret.setSubType(SubActionType.CRITICAL);
            LOG.log(Level.FINEST, "[event=CRITICAL, value={0}] CRITICAL, MODIFIER {0}", dmgModifier);
        }

        return dmgModifier;
    }

    private void doDamage(BattleEvent ret, Hero aHero, ActionEffect ae, Hero tHero) {

        ret.setValue(-(int) ((aHero.getCurrentDmg() * calculateModifier(ret, aHero) * ae.getActionPercentage() / 100f)
                * (ae.getDamageType().equals(DamageType.PHYSICAL) ? getDmgAmorReduction(tHero) : getDmgMagicResistReduction(tHero))));
        ret.setDamageType(ae.getDamageType());

        Integer newHp = tHero.getCurrentHp() + ret.getValue();
        if (newHp < 0) {
            tHero.setCurrentHp(0);
            ret.setSubType(SubActionType.DEATH);
            LOG.log(Level.FINEST, "[event=DMG_TO_DIE, value={0}] DMG {0} TO HERO, HE DIE {1}", new Object[]{ret.getValue(), tHero});
        } else {
            tHero.setCurrentHp(newHp);
            LOG.log(Level.FINEST, "[event=DMG, value={0}] DMG {0} TO HERO {1}", new Object[]{ret.getValue(), tHero});
        }

    }

    private void doHeal(BattleEvent ret, Hero aHero, ActionEffect ae, Hero tHero) {

        ret.setValue((int) ((aHero.getCurrentDmg() * ae.getActionPercentage() / 100f)));
        ret.setDamageType(ae.getDamageType());

        tHero.setCurrentHp(tHero.getCurrentHp() + ret.getValue());
        LOG.log(Level.FINEST, "[event=HEAL, value={0}] HEAL {0} TO HERO {1}", new Object[]{ret.getValue(), tHero});

    }

    private void calculateBuff(Hero aHero, Action a, Hero tHero) {

        a.getSecundaryActionsEffects().forEach((sae) -> {

            if (sae.getActionChance() == null
                    || sae.getActionChance() == 0
                    || DiceUtil.random(100) <= sae.getActionChance()) {

                tHero.addBuff(new Buff(sae.getActionDuration(),
                        (int) (aHero.getCurrentDmg() * sae.getActionPercentage() / 100d),
                        sae.getActionType(), sae.getDamageType()));
            }

        });

    }

    private List<BattlePositionedHero> getTargets(TargetType targetType, BattleTeamType battleTeamTypeTarget) {

        List<BattlePositionedHero> targets = new ArrayList<>();
        switch (targetType) {
            case BACK_LINE:
                targets.addAll(getBackLinePositionedHeroes(battleTeamTypeTarget));
                break;
            case DEATH_BACK_LINE:
                break;
            case DEATH_FRONT_LINE:
                break;
            case DEATH_RANDOM:
                break;
            case FRONT_LINE:
                targets.addAll(getFrontLinePositionedHeroes(battleTeamTypeTarget));
                break;
            case LESS_LIFE:
                targets.add(getHeroLessLife(battleTeamTypeTarget));
                break;
            case LESS_PERC_LIFE:
                targets.add(getHeroLessLifePerc(battleTeamTypeTarget));
                break;
            case MORE_LIFE:
                targets.add(getHeroMoreLife(battleTeamTypeTarget));
                break;
            case MORE_PERC_LIFE:
                targets.add(getHeroMoreLifePerc(battleTeamTypeTarget));
                break;
            case RANDOM:
            default:
                targets.add(getRandomHero(battleTeamTypeTarget));
                break;
        }

        LOG.log(Level.FINEST, "[target] {0}", targets);

        return targets;
    }

    private Double getDmgMagicResistReduction(Hero h) {
        return 100d / (100d + h.getCurrentMagicResist());
    }

    private Double getDmgAmorReduction(Hero h) {
        return 100d / (100d + h.getCurrentArmor());
    }

    private Boolean calculateDoubleActionChance(BattlePositionedHero heroFaster, BattlePositionedHero nextHeroFaster) {
        return (DiceUtil.random(100) < ((heroFaster.getHero().getCurrentSpeed() / nextHeroFaster.getHero().getCurrentSpeed()) - 1) * 25);
    }

    private List<BattlePositionedHero> getAttackHeroes() {
        return getHeroesByBattleTeamType(BattleTeamType.ATTACK).collect(Collectors.toList());
    }

    private List<BattlePositionedHero> getDefenseHeroes() {
        return getHeroesByBattleTeamType(BattleTeamType.DEFENSE).collect(Collectors.toList());
    }

    private BattlePositionedHero getHeroLessLifePerc(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrentHp() / h.getHero().getHp() < h2.getHero().getCurrentHp() / h.getHero().getHp() ? h : h2;
        });

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    private BattlePositionedHero getHeroMoreLifePerc(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrentHp() / h.getHero().getHp() > h2.getHero().getCurrentHp() / h.getHero().getHp() ? h : h2;
        });

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    private BattlePositionedHero getHeroLessLife(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrentHp() < h2.getHero().getCurrentHp() ? h : h2;
        });

        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private BattlePositionedHero getHeroMoreLife(BattleTeamType battleTeamType) {
        Optional<BattlePositionedHero> ret = getHeroesByBattleTeamTypeCanDoAction(battleTeamType).reduce((h, h2) -> {
            return h.getHero().getCurrentHp() > h2.getHero().getCurrentHp() ? h : h2;
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

    private List<BattlePositionedHero> getBackLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return ph.getBattlePosition().equals(BattlePositionType.FRONT_BOTTOM)
                    || ph.getBattlePosition().equals(BattlePositionType.FRONT_TOP);
        }
        ).collect(Collectors.toList());

    }

    private List<BattlePositionedHero> getFrontLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return (ph.getBattlePosition().equals(BattlePositionType.BACK_0)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_1)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_2)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_3));
        }).collect(Collectors.toList());
    }

    private Stream<BattlePositionedHero> getHeroesByBattleTeamTypeCanDoAction(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamType(battleTeamType).filter((BattlePositionedHero t) -> {
            return t.getHero().getCurrentHp() > 0;
        });
    }

    private Stream<BattlePositionedHero> getHeroesByBattleTeamType(BattleTeamType battleTeamType) {
        return this.battlePositionedHeroes.stream().filter((BattlePositionedHero t) -> {
            return t.getBattleTeamType().equals(battleTeamType);
        });
    }

    private List<BattlePositionedHero> getTurnActionRated() {

        return this.battlePositionedHeroes.stream().filter((BattlePositionedHero t) -> {
            return t.getHero().getCurrentHp() > 0;
        }).sorted((BattlePositionedHero t, BattlePositionedHero t1) -> {
            if (t1.getHero().getCurrentSpeed() - t.getHero().getCurrentSpeed() != 0) {
                return t1.getHero().getCurrentSpeed() - t.getHero().getCurrentSpeed();
            } else {
                //Velocidade empatada attack team tem vantagem
                return t1.getBattleTeamType().equals(BattleTeamType.ATTACK) ? 1 : -1;
            }
        }).collect(Collectors.toList());

    }

    public void prepareToBattle() {
        this.battlePositionedHeroes.forEach((h) -> {
            h.getHero().prepareToBattle();
        });
    }

    private void computeBuffs(List<BattlePositionedHero> battlePositionedHeroes) {

        battlePositionedHeroes.forEach((battlePositionedHero) -> {
            battlePositionedHero.getHero().prepareToComputeBuffs();

            Iterator<Buff> it = battlePositionedHero.getHero().getCurrentBuffs().iterator();

            while (it.hasNext()) {
                Buff buff = it.next();
                BattleEvent ret = new BattleEvent();
                ret.setType(buff.getEffectType());
                ret.setValue(buff.getValue());
                ret.setDamageType(buff.getDamageType());
                switch (buff.getEffectType()) {
                    case HEAL:
                        battlePositionedHero.getHero().setCurrentHp(battlePositionedHero.getHero().getCurrentHp() + buff.getValue());
                        ret.setBuffType(BuffType.BUFF);
                        break;
                    case DMG:
                        battlePositionedHero.getHero().setCurrentHp(battlePositionedHero.getHero().getCurrentHp() - buff.getValue());
                        ret.setBuffType(BuffType.DEBUFF);
                        break;
                    case FROZEN:
                    case STUN:
                    case SILENCE:
                        battlePositionedHero.getHero().setCanDoAction(Boolean.FALSE);
                        break;

                }
                buff.setCurrentTurn(buff.getCurrentTurn() + 1);
                this.addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), ret, battlePositionedHero.duplicate()));

                //Remove expired buffs
                if (buff.getCurrentTurn() >= buff.getTurnDuration()) {
                    it.remove();
                    BattleEvent beBuffDone = new BattleEvent();
                    beBuffDone.setType(ActionType.BUFF_DONE);
                    this.addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), beBuffDone, buff));
                }

            }

        });

    }

}
