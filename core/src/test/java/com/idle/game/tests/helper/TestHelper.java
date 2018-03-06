package com.idle.game.tests.helper;

import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.battle.Battle;
import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.buff.BuffEffect;
import com.idle.game.core.buff.type.BuffEffectType;
import static com.idle.game.core.formation.type.FormationPosition.*;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.type.BattleHeroType;
import com.idle.game.core.type.DefenseType;
import com.idle.game.core.type.TargetType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class TestHelper {

    private static BattleHeroType createHeroTypePhysicalMelee() {
        BattleHeroType ret = new BattleHeroType();
        ret.setDistanceType(DistanceType.MELEE);
        ret.setDefaultAction(createBasicDmgBluntAction());
        return ret;
    }

    private static BattleHeroType createHeroTypePhysicalRanged() {
        BattleHeroType ret = new BattleHeroType();
        ret.setDistanceType(DistanceType.RANGED);
        ret.setDefaultAction(createBasicDmgBluntAction());
        return ret;
    }

    private static BattleHeroType createHeroTypeMagicMelee() {
        BattleHeroType ret = new BattleHeroType();
        ret.setDistanceType(DistanceType.MELEE);
        ret.setDefaultAction(createBasicDmgBluntAction());
        return ret;
    }

    private static BattleHeroType createHeroTypeMagicRanged() {
        BattleHeroType ret = new BattleHeroType();
        ret.setDistanceType(DistanceType.RANGED);
        ret.setDefaultAction(createBasicDmgBluntAction());
        return ret;
    }

    public static BattleHero createBasicMeleePhysicalNoCritNoDodge100HpHero() {
        BattleHero ret = new BattleHero(UUID.randomUUID().toString(), createHeroTypePhysicalMelee(), 1);
        ret.setDmg(100);
        ret.setAp(100);
        for (DefenseType dt : DefenseType.values()) {
            ret.getDefenses().put(dt, 6000);
        }
        ret.setSpeed(1);
        ret.setCritChance(0);
        ret.setCritDamage(-1);
        ret.setDodgeChance(0);
        ret.setHp(100);

        return ret;
    }

    public static BattleHero createBasicMeleePhysicalNoCritNoDodge10000HpHero() {
        BattleHero ret = createBasicMeleeMagic100CritNoDodge100HpHero();
        ret.setHp(10000);

        return ret;
    }

    public static BattleHero createBasicMeleePhysicalNoCritNoDodge50HpHero() {

        BattleHero ret = createBasicMeleeMagic100CritNoDodge100HpHero();
        ret.setHp(50);

        return ret;

    }

    public static BattleHero createBasicMeleePhysicalNoCritNoDodge100HpHero10XFaster() {
        BattleHero ret = createBasicMeleeMagic100CritNoDodge100HpHero();
        ret.setSpeed(10);

        return ret;
    }

    public static BattleHero createBasicMeleePhysicalNoCritNoDodge200Hp10000SpeedHero() {
        BattleHero ret = createBasicMeleePhysicalNoCritNoDodge200HpHero();
        ret.setSpeed(10000);
        return ret;
    }

    public static BattleHero createBasicMeleePhysicalNoCritNoDodge200HpHero() {
        BattleHero ret = createBasicMeleeMagicNoCritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicRangedPhysicalNoCritNoDodge100HpHero() {
        BattleHero ret = new BattleHero(UUID.randomUUID().toString(), createHeroTypePhysicalRanged(), 1);
        ret.setDmg(100);
        ret.setAp(100);
        for (DefenseType dt : DefenseType.values()) {
            ret.getDefenses().put(dt, 6000);
        }
        ret.setSpeed(1);
        ret.setCritChance(0);
        ret.setCritDamage(-1);
        ret.setDodgeChance(0);
        ret.setHp(100);

        return ret;
    }

    public static BattleHero createBasicRangedPhysicalNoCritNoDodge200HpHero() {
        BattleHero ret = createBasicRangedPhysicalNoCritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicMeleeMagicNoCritNoDodge100HpHero() {
        BattleHero ret = new BattleHero(UUID.randomUUID().toString(), createHeroTypeMagicMelee(), 1);
        ret.setDmg(100);
        ret.setAp(100);
        for (DefenseType dt : DefenseType.values()) {
            ret.getDefenses().put(dt, 6000);
        }
        ret.setSpeed(1);
        ret.setCritChance(0);
        ret.setCritDamage(-1);
        ret.setDodgeChance(0);
        ret.setHp(100);

        return ret;
    }

    public static BattleHero createBasicMeleeMagicNoCritNoDodge200HpHero() {
        BattleHero ret = createBasicMeleeMagicNoCritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicRangedMagicNoCritNoDodge100HpHero() {
        BattleHero ret = new BattleHero(UUID.randomUUID().toString(), createHeroTypeMagicRanged(), 1);
        ret.setDmg(100);
        ret.setAp(100);
        for (DefenseType dt : DefenseType.values()) {
            ret.getDefenses().put(dt, 6000);
        }
        ret.setSpeed(1);
        ret.setCritChance(0);
        ret.setCritDamage(-1);
        ret.setDodgeChance(0);
        ret.setHp(100);

        return ret;
    }

    public static BattleHero createBasicRangedMagicNoCritNoDodge200HpHero() {
        BattleHero ret = createBasicRangedMagicNoCritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicMeleePhysical100CritNoDodge100HpHero() {
        BattleHero ret = createBasicMeleePhysicalNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        return ret;
    }

    public static BattleHero createBasicMeleePhysical100CritNoDodge200HpHero() {
        BattleHero ret = createBasicMeleePhysical100CritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicRangedPhysical100CritNoDodge100HpHero() {
        BattleHero ret = createBasicRangedPhysicalNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        return ret;
    }

    public static BattleHero createBasicRangedPhysical100CritNoDodge200HpHero() {
        BattleHero ret = createBasicRangedPhysical100CritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicMeleeMagic100CritNoDodge100HpHero() {
        BattleHero ret = createBasicMeleeMagicNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        return ret;
    }

    public static BattleHero createBasicMeleeMagic100CritNoDodge200HpHero() {
        BattleHero ret = createBasicMeleeMagic100CritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicRangedMagic100CritNoDodge100HpHero() {
        BattleHero ret = createBasicRangedMagicNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        return ret;
    }

    public static BattleHero createBasicRangedMagic100CritNoDodge200HpHero() {
        BattleHero ret = createBasicRangedMagic100CritNoDodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicMeleePhysicalNoCrit100Dodge100HpHero() {
        BattleHero ret = createBasicMeleePhysicalNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        return ret;
    }

    public static BattleHero createBasicMeleePhysicalNoCrit100Dodge200HpHero() {
        BattleHero ret = createBasicMeleePhysicalNoCrit100Dodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicRangedPhysicalNoCrit100Dodge100HpHero() {
        BattleHero ret = createBasicRangedPhysicalNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        return ret;
    }

    public static BattleHero createBasicRangedPhysicalNoCrit100Dodge200HpHero() {
        BattleHero ret = createBasicRangedPhysicalNoCrit100Dodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicMeleeMagicNoCrit100Dodge100HpHero() {
        BattleHero ret = createBasicMeleeMagicNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        return ret;
    }

    public static BattleHero createBasicMeleeMagicNoCrit100Dodge200HpHero() {
        BattleHero ret = createBasicMeleeMagicNoCrit100Dodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattleHero createBasicRangedMagicNoCrit100Dodge100HpHero() {
        BattleHero ret = createBasicRangedMagicNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        return ret;
    }

    public static BattleHero createBasicRangedMagicNoCrit100Dodge200HpHero() {
        BattleHero ret = createBasicRangedMagicNoCrit100Dodge100HpHero();
        ret.setHp(200);

        return ret;
    }

    public static BattlePositionedHero createBasicAttackPositionedHero(FormationPosition battlePositionType, BattleHero hero) {
        return new BattlePositionedHero(BattleTeamType.ATTACK_TEAM, battlePositionType, hero);
    }

    public static BattlePositionedHero createBasicDefensePositionedHero(FormationPosition battlePositionType, BattleHero hero) {
        return new BattlePositionedHero(BattleTeamType.DEFENSE_TEAM, battlePositionType, hero);
    }

    public static BattlePositionedHero createBasicPositionedHero(FormationPosition battlePositionType, BattleHero hero) {
        return new BattlePositionedHero(battlePositionType, hero);
    }

    public static BattleFormation createBasicFormation(BattlePositionedHero... heroes) {
        return new BattleFormation(heroes);
    }

    public static Action createBasicDmgBluntAction() {
        Action ret = new Action();
        ret.setMainActionEffect(new ActionEffect(ActionType.DMG, TargetType.RANDOM, 100, DamageType.BLUNT, Boolean.FALSE));

        return ret;
    }

    public static Action createBasicHealSpell() {
        Action ret = new Action();
        ret.setMainActionEffect(new ActionEffect(ActionType.HEAL, TargetType.RANDOM, 100, DamageType.HOLY, Boolean.TRUE));

        return ret;
    }

    public static Action createHealSpellWithBuff() {
        Action healSkill = new Action();

        List<BuffEffect> bes = new ArrayList<>();
        bes.add(new BuffEffect(BuffEffectType.HEAL, 25, 0, 3));

        healSkill.setMainActionEffect(new ActionEffect(ActionType.HEAL, TargetType.RANDOM, 100, DamageType.HOLY, Boolean.TRUE, bes));

        return healSkill;
    }

    private static Battle createBasicBattle(FormationPosition[] attForms, FormationPosition[] defForms) {
        BattleFormation attForm = createBasicFormation();
        for (FormationPosition fp : attForms) {
            attForm.addBattlePositionedHero(createBasicPositionedHero(fp, createBasicMeleePhysicalNoCritNoDodge100HpHero()));
        }

        BattleFormation defForm = createBasicFormation();
        for (FormationPosition fp : defForms) {
            defForm.addBattlePositionedHero(createBasicPositionedHero(fp, createBasicMeleePhysicalNoCritNoDodge100HpHero()));
        }

        return new Battle(attForm, defForm);
    }

    public static Battle createBasicBattleWithPositionsBiggerAndLowerHp(FormationPosition[] attForms, FormationPosition[] defForms) {
        Battle ret = createBasicBattle(attForms, defForms);

        ret.prepareToBattle();

        List<BattlePositionedHero> tAll
                = ret.getTargetsOfActionEffect(
                        createBasicActionEffect(TargetType.ALL),
                        createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero())
                );

        tAll.forEach((t) -> {
            t.getHero().setCurrHp(90);
        });

        List<BattlePositionedHero> tBigger
                = ret.getTargetsOfActionEffect(
                        createBasicActionEffect(TargetType.IN_FRONT),
                        createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero())
                );

        tBigger.get(0).getHero().setCurrHp(100);

        List<BattlePositionedHero> tLower
                = ret.getTargetsOfActionEffect(
                        createBasicActionEffect(TargetType.IN_FRONT),
                        createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero())
                );

        tLower.get(0).getHero().setCurrHp(80);

        return ret;
    }

    public static Battle createBasicBattleWithPositions(FormationPosition[] attForms, FormationPosition[] defForms) {

        Battle ret = createBasicBattle(attForms, defForms);
        ret.prepareToBattle();
        return ret;
    }

    public static ActionEffect createBasicActionEffectOverSameTeam(TargetType tt) {
        ActionEffect ae = new ActionEffect();
        ae.setOverSameTeam(Boolean.TRUE);
        ae.setTargetType(tt);

        return ae;
    }

    public static ActionEffect createBasicActionEffect(TargetType tt) {
        ActionEffect ae = new ActionEffect();
        ae.setOverSameTeam(Boolean.FALSE);
        ae.setTargetType(tt);

        return ae;
    }
}
