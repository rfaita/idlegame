package com.idle.game.tests.helper;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.battle.Battle;
import com.idle.game.core.battle.BattleUnit;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.buff.BuffEffect;
import com.idle.game.core.buff.type.BuffEffectType;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.formation.type.FormationPositionType;
import com.idle.game.core.hero.Unit;
import com.idle.game.core.hero.type.HeroSize;
import com.idle.game.core.type.UnitType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DefenseType;
import com.idle.game.core.type.TargetType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.idle.game.core.formation.type.FormationPositionType.*;

/**
 * @author rafael
 */
public class TestHelper {

    private static UnitType createHeroTypeSpecialFront() {
        UnitType ret = new UnitType();
        ret.getSpecialActions().add(createBasicDmgFireAction(F));
        return ret;
    }

    private static UnitType createHeroTypeSpecialBack() {
        UnitType ret = new UnitType();
        ret.getSpecialActions().add(createBasicDmgFireAction(B));
        return ret;
    }

    private static UnitType createHeroTypeSpecialMiddle() {
        UnitType ret = new UnitType();
        ret.getSpecialActions().add(createBasicDmgFireAction(M));
        return ret;
    }

    private static UnitType createHeroTypeSpecialBackAndAll() {
        UnitType ret = new UnitType();
        ret.getSpecialActions().add(createBasicDmgFireAction(B));
        ret.getSpecialActions().add(createBasicDmgHolyAction(ALL_LINES));
        return ret;
    }

    public static Unit createSpecialFrontHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeSpecialFront(), 1);
    }

    public static Unit createSpecialBackHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeSpecialBack(), 1);
    }

    public static Unit createSpecialMiddleHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeSpecialMiddle(), 1);
    }

    public static Unit createSpecialBackAndAllHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeSpecialBackAndAll(), 1);
    }

    private static UnitType createHeroTypeActionFront() {
        UnitType ret = new UnitType();
        ret.getDefaultActions().add(createBasicDmgFireAction(F));
        return ret;
    }

    private static UnitType createHeroTypeActionBack() {
        UnitType ret = new UnitType();
        ret.getDefaultActions().add(createBasicDmgFireAction(B));
        return ret;
    }

    private static UnitType createHeroTypeActionMiddle() {
        UnitType ret = new UnitType();
        ret.getDefaultActions().add(createBasicDmgFireAction(M));
        return ret;
    }

    private static UnitType createHeroTypeActionBackAndAll() {
        UnitType ret = new UnitType();
        ret.getDefaultActions().add(createBasicDmgFireAction(B));
        ret.getDefaultActions().add(createBasicDmgHolyAction(ALL_LINES));
        return ret;
    }

    public static Unit createActionFrontHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeActionFront(), 1);
    }

    public static Unit createActionBackHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeActionBack(), 1);
    }

    public static Unit createActionMiddleHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeActionMiddle(), 1);
    }

    public static Unit createActionBackAndAllHero() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeActionBackAndAll(), 1);
    }

    private static UnitType createHeroTypeSmall() {
        UnitType ret = new UnitType();
        ret.setSize(HeroSize.SMALL);
        return ret;
    }

    public static Unit createBasicHeroSmall() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeSmall(), 1);
    }

    private static UnitType createHeroTypeMedium() {
        UnitType ret = new UnitType();
        ret.setSize(HeroSize.MEDIUM);
        return ret;
    }

    public static Unit createBasicHeroMedium() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeMedium(), 1);
    }

    private static UnitType createHeroTypeLarge() {
        UnitType ret = new UnitType();
        ret.setSize(HeroSize.LARGE);
        return ret;
    }

    public static Unit createBasicHeroLarge() {
        return new Unit(UUID.randomUUID().toString(), createHeroTypeLarge(), 1);
    }

    private static UnitType createHeroTypeBlunt() {
        UnitType ret = new UnitType();
        ret.setSize(HeroSize.SMALL);
        ret.getSpecialActions().add(createBasicDmgBluntAction(ALL_LINES));
        return ret;
    }

    private static UnitType createHeroTypeFire() {
        UnitType ret = new UnitType();
        ret.getSpecialActions().add(createBasicDmgFireAction(ALL_LINES));
        return ret;
    }

    public static Unit createBasicBluntNoCritNoDodge100HpHero() {
        Unit ret = new Unit(UUID.randomUUID().toString(), createHeroTypeBlunt(), 1);
        ret.setDmg(100);
        ret.setAp(100);
        for (DefenseType dt : DefenseType.values()) {
            ret.setDefense(dt, 6000);
        }
        ret.setSpeed(1);
        ret.setCritChance(0);
        ret.setCritDamage(-1);
        ret.setDodgeChance(0);
        ret.setHp(100);

        ret.prepareToBattle();

        return ret;
    }

    public static Unit createBasicBluntNoCritNoDodge10000HpHero() {
        Unit ret = createBasicFire100CritNoDodge100HpHero();
        ret.setHp(10000);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicBluntNoCritNoDodge50HpHero() {

        Unit ret = createBasicFire100CritNoDodge100HpHero();
        ret.setHp(50);

        ret.prepareToBattle();
        return ret;

    }

    public static Unit createBasicBluntNoCritNoDodge100HpHero10XFaster() {
        Unit ret = createBasicFire100CritNoDodge100HpHero();
        ret.setSpeed(10);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicBluntNoCritNoDodge200Hp10000SpeedHero() {
        Unit ret = createBasicBluntNoCritNoDodge200HpHero();
        ret.setSpeed(10000);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicBluntNoCritNoDodge200HpHero() {
        Unit ret = createBasicBluntNoCritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicFireNoCritNoDodge100HpHero() {
        Unit ret = new Unit(UUID.randomUUID().toString(), createHeroTypeFire(), 1);
        ret.setDmg(100);
        ret.setAp(100);
        for (DefenseType dt : DefenseType.values()) {
            ret.setDefense(dt, 6000);
        }
        ret.setSpeed(1);
        ret.setCritChance(0);
        ret.setCritDamage(-1);
        ret.setDodgeChance(0);
        ret.setHp(100);

        ret.prepareToBattle();

        return ret;
    }

    public static Unit createBasicFireNoCritNoDodge200HpHero() {
        Unit ret = createBasicFireNoCritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicBlunt100CritNoDodge100HpHero() {
        Unit ret = createBasicBluntNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicBlunt100CritNoDodge200HpHero() {
        Unit ret = createBasicBlunt100CritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicFire100CritNoDodge100HpHero() {
        Unit ret = createBasicFireNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicFire100CritNoDodge200HpHero() {
        Unit ret = createBasicFire100CritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicBluntNoCrit100Dodge100HpHero() {
        Unit ret = createBasicBluntNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicBluntNoCrit100Dodge200HpHero() {
        Unit ret = createBasicBluntNoCrit100Dodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicFireNoCrit100Dodge100HpHero() {
        Unit ret = createBasicFireNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        ret.prepareToBattle();
        return ret;
    }

    public static Unit createBasicFireNoCrit100Dodge200HpHero() {
        Unit ret = createBasicFireNoCrit100Dodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleUnit createBasicAttackPositionedHeroMaxEnergy(FormationPosition battlePositionType, Unit hero) {
        BattleUnit ret = new BattleUnit(BattleTeamType.ATTACK_TEAM, battlePositionType, hero);
        ret.setEnergy(IdleConstants.MAX_ENERGY);
        ret.getUnit().setCanDoSpecialAction(Boolean.TRUE);
        return ret;
    }

    public static BattleUnit createBasicAttackPositionedHero(FormationPosition battlePositionType, Unit hero) {
        return new BattleUnit(BattleTeamType.ATTACK_TEAM, battlePositionType, hero);
    }

    public static BattleUnit createBasicDefensePositionedHero(FormationPosition battlePositionType, Unit hero) {
        return new BattleUnit(BattleTeamType.DEFENSE_TEAM, battlePositionType, hero);
    }

    public static BattleUnit createBasicPositionedHero(FormationPosition battlePositionType, Unit hero) {
        return new BattleUnit(battlePositionType, hero);
    }

    public static BattleFormation createBasicFormation(BattleUnit... heroes) {
        return new BattleFormation(heroes);
    }

    public static Action createBasicDmgBluntAction(FormationPositionType fpt) {
        Action ret = new Action();
        ret.setMainActionEffect(new ActionEffect(ActionType.DMG, TargetType.RANDOM, 100, DamageType.BLUNT, Boolean.FALSE));
        ret.setFormationPositionType(fpt);

        return ret;
    }

    public static Action createBasicDmgFireAction(FormationPositionType fpt) {
        Action ret = new Action();
        ret.setMainActionEffect(new ActionEffect(ActionType.DMG, TargetType.RANDOM, 100, DamageType.FIRE, Boolean.FALSE));
        ret.setFormationPositionType(fpt);

        return ret;
    }

    public static Action createBasicDmgHolyAction(FormationPositionType fpt) {
        Action ret = new Action();
        ret.setMainActionEffect(new ActionEffect(ActionType.DMG, TargetType.RANDOM, 100, DamageType.HOLY, Boolean.FALSE));
        ret.setFormationPositionType(fpt);

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
            attForm.addBattlePositionedHero(createBasicPositionedHero(fp, createBasicBluntNoCritNoDodge100HpHero()));
        }

        BattleFormation defForm = createBasicFormation();
        for (FormationPosition fp : defForms) {
            defForm.addBattlePositionedHero(createBasicPositionedHero(fp, createBasicBluntNoCritNoDodge100HpHero()));
        }

        return new Battle(attForm, defForm);
    }

    public static List<BattleUnit> createBattlePositionedHeroes(FormationPosition[] pos) {
        List<BattleUnit> ret = new ArrayList<>();
        for (FormationPosition fp : pos) {
            BattleUnit bph = createBasicPositionedHero(fp, createBasicBluntNoCritNoDodge100HpHero());
            bph.setBattleTeamType(BattleTeamType.DEFENSE_TEAM);
            ret.add(bph);
        }

        return ret;
    }

    public static Battle createBasicBattleWithPositionsBiggerAndLowerHp(FormationPosition[] attForms, FormationPosition[] defForms) {
        Battle ret = createBasicBattle(attForms, defForms);

        ret.prepareToBattle();

//        List<BattlePositionedHero> tAll
//                = ret.getTargetsOfActionEffect(
//                createBasicActionEffect(TargetType.ALL),
//                createBasicAttackPositionedHero(F_0, createBasicFireNoCritNoDodge100HpHero())
//        );

//        tAll.forEach((t) -> {
//            t.getHero().setCurrHp(90);
//        });

//        List<BattlePositionedHero> tBigger
//                = ret.getTargetsOfActionEffect(
//                createBasicActionEffect(TargetType.IN_FRONT),
//                createBasicAttackPositionedHero(F_0, createBasicFireNoCritNoDodge100HpHero())
//        );

//        tBigger.get(0).getHero().setCurrHp(100);

//        List<BattlePositionedHero> tLower
//                = ret.getTargetsOfActionEffect(
//                createBasicActionEffect(TargetType.IN_FRONT),
//                createBasicAttackPositionedHero(F_1, createBasicFireNoCritNoDodge100HpHero())
//        );

//        tLower.get(0).getHero().setCurrHp(80);

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
        ae.setValue(100);
        ae.setDamageType(DamageType.BLUNT);
        ae.setOverSameTeam(Boolean.FALSE);
        ae.setTargetType(tt);

        return ae;
    }

    public static ActionEffect createFireActionEffect(TargetType tt) {
        ActionEffect ae = new ActionEffect();
        ae.setValue(100);
        ae.setDamageType(DamageType.FIRE);
        ae.setOverSameTeam(Boolean.FALSE);
        ae.setTargetType(tt);

        return ae;
    }
}
