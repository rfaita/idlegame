package com.idle.game.core;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.type.BattleTeamType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.FormationType;
import com.idle.game.core.type.TargetType;
import com.idle.game.core.type.BattlePositionType;
import com.idle.game.core.type.SubActionType;
import com.idle.game.core.type.BuffType;
import com.idle.game.core.buff.Buff;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.Action;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.type.ActionType;
import com.idle.game.core.exception.ValidationException;
import com.idle.game.core.util.DiceUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Battle(Formation a, Formation d) throws Exception {
        if (a == null) {
            throw new ValidationException("not.have.attack.formation");
        }
        if (d == null) {
            throw new ValidationException("not.have.defense.formation");
        }
        this.attackFormation = a;
        this.attackFormation.setFormationType(FormationType.ATTACK);
        this.defenseFormation = d;
        this.defenseFormation.setFormationType(FormationType.DEFENSE);

        this.battlePositionedHeroes = new ArrayList<>();

        this.attackFormation.getPositionedHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.ATTACK, t.getBattlePosition(), t.getHero()));
        });
        this.defenseFormation.getPositionedHeroes().forEach((t) -> {
            this.battlePositionedHeroes.add(new BattlePositionedHero(BattleTeamType.DEFENSE, t.getBattlePosition(), t.getHero()));
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
                if (i < turnActionRated.size() - 1) {
                    if (calculateDoubleActionChance(turnActionRated.get(i), turnActionRated.get(i + 1))) {
                        LOG.log(Level.FINEST, "[event=DOUBLE_ACTION]");
                        doAction(turnActionRated.get(i));
                    }
                }
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
            this.addBattleLog(new BattleLog(this.turn, new BattleEvent(ActionType.BATTLE_END)));
        }

    }

    private Formation verifyWinner() {
        if (getHeroesByBattleTeamTypeCanDoAction(BattleTeamType.ATTACK).count() <= 0) {
            return defenseFormation;
        } else if (getHeroesByBattleTeamTypeCanDoAction(BattleTeamType.DEFENSE).count() <= 0) {
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

        Hero aHero = aPositionedHero.getHero();

        Action action = aHero.getHeroType().getDefaultAction();

        final ActionEffect actionMainEffect = action.getMainActionEffect();

        List<BattlePositionedHero> targets = getTargets(
                actionMainEffect.getTargetType() == null ? aHero.getHeroType().getAtittudeType().getTargetType() : actionMainEffect.getTargetType(),
                actionMainEffect.getActionOverSameTeam() ? aPositionedHero.getBattleTeamType() : aPositionedHero.getBattleTeamType().getOpposite());

        LOG.log(Level.FINEST, "[event=ACTION] {0}", action);

        if (targets != null) {
            targets.forEach((tPositionedHero) -> {

                Hero tHero = tPositionedHero.getHero();

                BattleEvent ret = new BattleEvent(actionMainEffect.getActionType());

                if (!actionMainEffect.getCanBeDodge()
                        || tHero.getCurrDodgeChance() == 0 || DiceUtil.random(100) > tHero.getCurrDodgeChance()) {

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

    }

    private Double criticalDamage(BattleEvent ret, Hero aHero) {
        Double dmgModifier = 1.0d;

        if (aHero.getCurrCritChance() > 0
                && DiceUtil.random(100) <= aHero.getCurrCritChance()) {
            dmgModifier += aHero.getCurrCritDamage() / 100d;
            ret.setSubType(SubActionType.CRITICAL);
            LOG.log(Level.FINEST, "[event=CRITICAL, value={0}] CRITICAL, MODIFIER {0}", dmgModifier);
        }

        return dmgModifier;
    }

    private void doDamage(BattleEvent ret, Hero aHero, ActionEffect ae, Hero tHero) {

        DamageType dt = ae.getDamageType() != null ? ae.getDamageType() : aHero.getHeroType().getDamageType();

        Double dmgReduction = (dt.equals(DamageType.PHYSICAL) ? getDmgAmorReduction(tHero) : getDmgMagicResistReduction(tHero));

        ret.setValue(-(int) ((aHero.getCurrDmg() * criticalDamage(ret, aHero) * ae.getActionPercentage() / 100f) * dmgReduction));
        ret.setDamageType(ae.getDamageType());

        Integer newHp = tHero.getCurrHp() + ret.getValue();
        if (newHp < 0) {
            tHero.setCurrHp(0);
            ret.setSubType(SubActionType.DEATH);
            LOG.log(Level.FINEST, "[event=DMG_TO_DIE, value={0}] DMG {0} TO HERO, HE DIE {1}", new Object[]{ret.getValue(), tHero});
        } else {
            tHero.setCurrHp(newHp);
            LOG.log(Level.FINEST, "[event=DMG, value={0}] DMG {0} TO HERO {1}", new Object[]{ret.getValue(), tHero});
        }

    }

    private void doHeal(BattleEvent ret, Hero aHero, ActionEffect ae, Hero tHero) {

        ret.setValue((int) ((aHero.getCurrDmg() * ae.getActionPercentage() / 100f)));
        ret.setDamageType(ae.getDamageType());

        tHero.setCurrHp(tHero.getCurrHp() + ret.getValue());
        LOG.log(Level.FINEST, "[event=HEAL, value={0}] HEAL {0} TO HERO {1}", new Object[]{ret.getValue(), tHero});

    }

    private void calculateBuff(Hero aHero, Action a, Hero tHero) {

        if (a.getSecundaryActionsEffects() != null) {
            a.getSecundaryActionsEffects().forEach((sae) -> {

                if (sae.getActionChance() == null
                        || sae.getActionChance() == 0
                        || DiceUtil.random(100) <= sae.getActionChance()) {

                    tHero.addBuff(new Buff(sae.getActionDuration(),
                            (int) (aHero.getCurrDmg() * sae.getActionPercentage() / 100d),
                            sae.getActionType(), sae.getDamageType()));
                }

            });
        }

    }

    private List<BattlePositionedHero> getTargets(TargetType targetType, BattleTeamType battleTeamTypeTarget) {

        List<BattlePositionedHero> targets = new ArrayList<>();
        BattlePositionedHero t = null;
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

    private Double getDmgMagicResistReduction(Hero h) {
        return 100d / (100d + h.getCurrMagicResist());
    }

    private Double getDmgAmorReduction(Hero h) {
        return 100d / (100d + h.getCurrArmor());
    }

    private Boolean calculateDoubleActionChance(BattlePositionedHero heroFaster, BattlePositionedHero nextHeroFaster) {
        return (DiceUtil.random(100) < ((heroFaster.getHero().getCurrSpeed() * 1d / nextHeroFaster.getHero().getCurrSpeed()) - 1) * 25);
    }

    private List<BattlePositionedHero> getAttackHeroes() {
        return getHeroesByBattleTeamType(BattleTeamType.ATTACK).collect(Collectors.toList());
    }

    private List<BattlePositionedHero> getDefenseHeroes() {
        return getHeroesByBattleTeamType(BattleTeamType.DEFENSE).collect(Collectors.toList());
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

    private List<BattlePositionedHero> getBackLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return ph.getBattlePosition().equals(BattlePositionType.FRONT_BOTTOM)
                    || ph.getBattlePosition().equals(BattlePositionType.FRONT_MIDDLE)
                    || ph.getBattlePosition().equals(BattlePositionType.FRONT_TOP);
        }).collect(Collectors.toList());

    }

    private List<BattlePositionedHero> getFrontLinePositionedHeroes(BattleTeamType battleTeamType) {
        return this.getHeroesByBattleTeamTypeCanDoAction(battleTeamType).filter((ph) -> {
            return (ph.getBattlePosition().equals(BattlePositionType.BACK_BOTTOM)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_MIDDLE)
                    || ph.getBattlePosition().equals(BattlePositionType.BACK_TOP));
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
                return t1.getBattleTeamType().equals(BattleTeamType.ATTACK) ? 1 : -1;
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
        for (BattlePositionedHero battlePositionedHero : bphs) {
            battlePositionedHero.getHero().calcPassives();
        }

    }

    private void computeBuffs(List<BattlePositionedHero> bphs) {

        for (int i = bphs.size() - 1; i >= 0; i--) {

            BattlePositionedHero battlePositionedHero = this.battlePositionedHeroes.get(i);

            if ((this.winner = this.verifyWinner()) != null) {
                return;
            }

            Iterator<Buff> it = battlePositionedHero.getHero().getCurrBuffs().iterator();

            while (it.hasNext()) {
                Buff buff = it.next();
                BattleEvent ret = new BattleEvent();
                ret.setType(buff.getEffectType());
                ret.setValue(buff.getValue());
                ret.setDamageType(buff.getDamageType());
                switch (buff.getEffectType()) {
                    case HEAL:
                        battlePositionedHero.getHero().setCurrHp(battlePositionedHero.getHero().getCurrHp() + buff.getValue());
                        ret.setBuffType(BuffType.BUFF);
                        break;
                    case DMG:
                        battlePositionedHero.getHero().setCurrHp(battlePositionedHero.getHero().getCurrHp() - buff.getValue());
                        ret.setBuffType(BuffType.DEBUFF);
                        break;
                    case FROZEN:
                    case STUN:
                    case SILENCE:
                        battlePositionedHero.getHero().setCanDoAction(Boolean.FALSE);
                        break;

                }
                buff.setCurrTurn(buff.getCurrTurn() + 1);
                this.addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), ret, battlePositionedHero.duplicate()));

                //Remove expired buffs
                if (buff.getCurrTurn() >= buff.getTurnDuration()) {
                    it.remove();
                    BattleEvent beBuffDone = new BattleEvent();
                    beBuffDone.setType(ActionType.BUFF_DONE);
                    this.addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), beBuffDone, buff));
                }

            }

        }

    }

}
