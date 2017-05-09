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
        this.addBattleLog(new BattleLog(this.turn, new BattleEvent(BattleEventType.START)));
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
            this.addBattleLog(new BattleLog(this.turn, new BattleEvent(BattleEventType.END)));
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

        if (aPositionedHero.getHero().getSkillPercentage() >= 100) {
            doSpecialAction(aPositionedHero);
        } else {
            LOG.log(Level.FINEST, "[event=NORMAL_ATTACK]");
            doNormalAttack(aPositionedHero);
        }

    }

    private void doSpecialAction(BattlePositionedHero aPositionedHero) {

        BattleEvent ret = new BattleEvent(BattleEventType.SPECIAL);

        Hero aHero = aPositionedHero.getHero();

        Skill ss = aHero.getHeroType().getSpecialSkill();

        List<BattlePositionedHero> targets = getSkillEffectTarget(ss.getMainEffect().getTargetType(),
                aPositionedHero.getBattleTeamType().equals(BattleTeamType.ATTACK) ? BattleTeamType.DEFENSE : BattleTeamType.ATTACK);

        LOG.log(Level.FINEST, "[event=SS] {0}", ss);

        targets.forEach((tPositionedHero) -> {

            Hero tHero = tPositionedHero.getHero();

            if (!ss.getMainEffect().getCanBeDodge() || DiceUtil.random(100) > tHero.getCurrentDodgeChance()) {

                DamageType skillDamageType = ss.getMainEffect().getDamageType();
                Integer skillDamagePercentage = ss.getMainEffect().getEffectDamagePercentage();
                Integer currentDmg = aHero.getCurrentMeleeDmg() > 0 ? aHero.getCurrentMeleeDmg() : aHero.getCurrentRangedDmg();

                Double dmgModifier = 1.0d;

                if (DiceUtil.random(100) <= aHero.getCurrentCritChance()) {
                    dmgModifier += aHero.getCurrentCritDamage() / 100d;
                    ret.setType(BattleEventType.CRITICAL);
                    LOG.log(Level.FINEST, "[event=CRITICAL, value={0}] CRITICAL, MODIFIER {0}", dmgModifier);
                }

                aHero.setSkillPercentage(0);
                //Ganha 25% da Skill Percentage por ataque sofrido
                tHero.setSkillPercentage((int) (tHero.getSkillPercentage() + (getLoadedSkillPercentage(tHero) * 0.25)));

                ret.setValue(-(int) ((currentDmg * dmgModifier * skillDamagePercentage / 100f)
                        * (skillDamageType.equals(DamageType.PHYSICAL) ? getDmgAmorReduction(tHero) : getDmgMagicResistReduction(tHero))));
                ret.setDamageType(skillDamageType);

                Integer newHp = tHero.getCurrentHp() + ret.getValue();
                if (newHp < 0) {
                    tHero.setCurrentHp(0);
                    LOG.log(Level.FINEST, "[event=DMG_TO_DIE, value={0}] DMG {0} TO HERO, HE DIE {1}", new Object[]{ret.getValue(), tHero});
                } else {
                    tHero.setCurrentHp(newHp);
                    LOG.log(Level.FINEST, "[event=DMG, value={0}] DMG {0} TO HERO {1}", new Object[]{ret.getValue(), tHero});
                }

            } else {
                aHero.setSkillPercentage(0);
                ret.setType(BattleEventType.DODGE);
            }

            this.addBattleLog(new BattleLog(this.turn, aPositionedHero.duplicate(), ret, tPositionedHero.duplicate()));
        });

        doSpecialActionSecundary(ret.getValue(), aPositionedHero, targets);

    }

    private BattleEvent doDamage(BattleEvent ret, Integer currentDmg, Integer dmgModifier, DamageType damageType, Hero tHero) {

        ret.setValue(-(int) ((currentDmg * dmgModifier / 100d)
                * (damageType.equals(DamageType.PHYSICAL) ? getDmgAmorReduction(tHero) : getDmgMagicResistReduction(tHero))));
        ret.setDamageType(damageType);

        Integer newHp = tHero.getCurrentHp() + ret.getValue();
        if (newHp < 0) {
            tHero.setCurrentHp(0);
            LOG.log(Level.FINEST, "[event=DMG_TO_DIE, value={0}] DMG {0} TO HERO, HE DIE {1}", new Object[]{ret.getValue(), tHero});
        } else {
            tHero.setCurrentHp(newHp);
            LOG.log(Level.FINEST, "[event=DMG, value={0}] DMG {0} TO HERO {1}", new Object[]{ret.getValue(), tHero});
        }

        return ret;

    }

    private void doSpecialActionSecundary(Integer dmg, BattlePositionedHero aPositionedHero, List<BattlePositionedHero> lastTargetPositionedHero) {

        LOG.log(Level.FINEST, "[event=SECUNDARY_EFFECTS]");

        Hero aHero = aPositionedHero.getHero();

        Skill ss = aHero.getHeroType().getSpecialSkill();

        if (!ss.getSecundaryEffects().isEmpty()) {
            ss.getSecundaryEffects().forEach((sse) -> {
                switch (sse.getSkillEffectType()) {
                    case HEAL:
                        doSpecialActionHeal(dmg, sse, aPositionedHero);
                        break;
                    case DMG:
                        doSpecialActionDmg(dmg, sse, aPositionedHero, lastTargetPositionedHero);
                        break;
                }

            });
        }
    }

    private void doSpecialActionHeal(Integer dmg, SkillEffect sse, BattlePositionedHero aPositionedHero) {

        BattleEvent ret = new BattleEvent(BattleEventType.HEAL);

        ret.setValue(-(int) ((dmg * sse.getEffectDamagePercentage() / 100f * sse.getEffectChance() / 100f)));
        ret.setDamageType(DamageType.MAGIC);

        final List<BattlePositionedHero> targets = getSkillEffectTarget(sse.getTargetType(), aPositionedHero.getBattleTeamType());

        List<BattlePositionedHero> targetsLog = new ArrayList<>();
        targets.forEach((t) -> {
            t.getHero().setCurrentHp(t.getHero().getCurrentHp() + ret.getValue());
            LOG.log(Level.FINEST, "[event=HEAL, value={0}] HEAL {0} TO HERO {1}", new Object[]{ret.getValue(), t});
            if (sse.getEffectTurnDuration() > 0) {
                t.getHero().addBuff(new Buff(sse.getEffectTurnDuration(), ret.getValue(), EffectType.HEAL));
            }
            targetsLog.add(t.duplicate());
        });

        this.addBattleLog(new BattleLog(this.turn, aPositionedHero.duplicate(), ret, targetsLog));

    }

    private void doSpecialActionDmg(Integer dmg, SkillEffect sse, BattlePositionedHero aPositionedHero, List<BattlePositionedHero> lastTargetPositionedHero) {

        BattleEvent ret = new BattleEvent(BattleEventType.DMG);

        ret.setValue(-(int) ((dmg * sse.getEffectDamagePercentage() / 100f * sse.getEffectChance() / 100f)));
        ret.setDamageType(sse.getDamageType());

        final List<BattlePositionedHero> targets;
        if (sse.getTargetType().equals(TargetType.LAST_ONE)) {
            targets = lastTargetPositionedHero;
        } else {
            targets = getSkillEffectTarget(sse.getTargetType(),
                    aPositionedHero.getBattleTeamType().equals(BattleTeamType.ATTACK) ? BattleTeamType.DEFENSE : BattleTeamType.ATTACK);
        }

        targets.forEach((t) -> {
            doDamage(ret, ret.getValue(), 100, DamageType.MAGIC, t.getHero());
            this.addBattleLog(new BattleLog(this.turn, aPositionedHero.duplicate(), ret, t.duplicate()));
        });

    }

    private List<BattlePositionedHero> getSkillEffectTarget(TargetType targetType, BattleTeamType battleTeamTypeTarget) {

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

    private void doNormalAttack(BattlePositionedHero aPositionedHero) {

        BattleEvent ret = new BattleEvent(BattleEventType.DMG);

        BattlePositionedHero tPositionedHero = getNormalAttackTarget(aPositionedHero);

        if (tPositionedHero != null) {
            Hero aHero = aPositionedHero.getHero();
            Hero tHero = tPositionedHero.getHero();

            if (tHero.getCurrentDodgeChance() == 0 || DiceUtil.random(100) > tHero.getCurrentDodgeChance()) {

                DamageType damageType = aHero.getCurrentMeleeDmg() > 0 ? aHero.getDmgTypeMelee() : aHero.getDmgTypeRanged();
                Integer currentDmg = aHero.getCurrentMeleeDmg() > 0 ? aHero.getCurrentMeleeDmg() : aHero.getCurrentRangedDmg();

                Integer dmgModifier = 100;

                if (aHero.getCurrentCritChance() > 0 && DiceUtil.random(100) <= aHero.getCurrentCritChance()) {
                    dmgModifier += aHero.getCurrentCritDamage();
                    ret.setType(BattleEventType.CRITICAL);
                    LOG.log(Level.FINEST, "[event=CRITICAL, value={0}] CRITICAL, MODIFIER {0}", dmgModifier);
                }

                aHero.setSkillPercentage((int) (aHero.getSkillPercentage() + getLoadedSkillPercentage(aHero)));
                //Ganha 25% da Skill Percentage por ataque sofrido
                tHero.setSkillPercentage((int) (tHero.getSkillPercentage() + (getLoadedSkillPercentage(tHero) * 0.25)));

                doDamage(ret, currentDmg, dmgModifier, damageType, tHero);

            } else {
                ret.setType(BattleEventType.DODGE);
            }

            this.addBattleLog(new BattleLog(this.turn, aPositionedHero.duplicate(), ret, tPositionedHero.duplicate()));
        }

    }

    private BattlePositionedHero getNormalAttackTarget(BattlePositionedHero pAction) {

        BattleTeamType battleTeamTypeTarget = pAction.getBattleTeamType().equals(BattleTeamType.ATTACK) ? BattleTeamType.DEFENSE : BattleTeamType.ATTACK;

        BattlePositionedHero pTarget = null;
        switch (pAction.getHero().getHeroType().getAtittudeType()) {
            case AGGRESSIVE:
                pTarget = getHeroLessLifePerc(battleTeamTypeTarget);
                break;
            case HUNTER:
                pTarget = getHeroLessLife(battleTeamTypeTarget);
                break;
            case SUPPORT:
                pTarget = getRandomHero(battleTeamTypeTarget);
                break;
            case CONTROL:
                pTarget = getRandomHero(battleTeamTypeTarget);
                break;
            case HEALER:
                pTarget = getRandomHero(battleTeamTypeTarget);
                break;
            case DEFENSIVE:
                pTarget = getRandomHero(battleTeamTypeTarget);
                break;
            case STRATEGIST:
                pTarget = getRandomHero(battleTeamTypeTarget);
                break;
            case SPLITTER:
                pTarget = getRandomHero(battleTeamTypeTarget);
                break;
            default:
                pTarget = getRandomHero(battleTeamTypeTarget);
                break;
        }

        LOG.log(Level.FINEST, "[target] {0}", pTarget);

        return pTarget;
    }

    private Double getLoadedSkillPercentage(Hero h) {
        Double ret = (1d - (100d / (100d + h.getCurrentSpeed()))) * 100;

        if (ret > 100d) {
            return 100d;
        } else {
            return ret;
        }
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
            Iterator<Buff> it = battlePositionedHero.getHero().getCurrentBuffs().iterator();

            while (it.hasNext()) {
                Buff buff = it.next();
                BattleEvent ret = new BattleEvent();
                switch (buff.getEffectType()) {
                    case HEAL:
                        battlePositionedHero.getHero().setCurrentHp(battlePositionedHero.getHero().getCurrentHp() + buff.getValue());
                        ret.setType(BattleEventType.HEAL);
                        ret.setValue(buff.getValue());
                        ret.setDamageType(DamageType.MAGIC);
                        ret.setBuffType(BuffType.BUFF);
                        break;
                }
                buff.setCurrentTurn(buff.getCurrentTurn() + 1);
                this.addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), ret, battlePositionedHero.duplicate()));
                if (buff.getCurrentTurn() >= buff.getTurnDuration()) {
                    it.remove();
                    BattleEvent beBuffDone = new BattleEvent();
                    beBuffDone.setType(BattleEventType.BUFF_DONE);
                    this.addBattleLog(new BattleLog(this.turn, battlePositionedHero.duplicate(), beBuffDone, buff));
                }

            }

        });

    }

}
