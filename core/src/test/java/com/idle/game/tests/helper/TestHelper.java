package com.idle.game.tests.helper;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.battle.Battle;
import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.buff.BuffEffect;
import com.idle.game.core.buff.type.BuffEffectType;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.formation.type.FormationPositionType;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.core.hero.type.HeroSize;
import com.idle.game.core.type.BattleHeroType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DefenseType;
import com.idle.game.core.type.TargetType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.idle.game.core.formation.type.FormationPosition.F_0;
import static com.idle.game.core.formation.type.FormationPosition.F_1;
import static com.idle.game.core.formation.type.FormationPositionType.*;

/**
 * @author rafael
 */
public class TestHelper {

    private static BattleHeroType createHeroTypeSpecialFront() {
        BattleHeroType ret = new BattleHeroType();
        ret.getSpecialActions().add(createBasicDmgFireAction(F));
        return ret;
    }

    private static BattleHeroType createHeroTypeSpecialBack() {
        BattleHeroType ret = new BattleHeroType();
        ret.getSpecialActions().add(createBasicDmgFireAction(B));
        return ret;
    }

    private static BattleHeroType createHeroTypeSpecialMiddle() {
        BattleHeroType ret = new BattleHeroType();
        ret.getSpecialActions().add(createBasicDmgFireAction(M));
        return ret;
    }

    private static BattleHeroType createHeroTypeSpecialBackAndAll() {
        BattleHeroType ret = new BattleHeroType();
        ret.getSpecialActions().add(createBasicDmgFireAction(B));
        ret.getSpecialActions().add(createBasicDmgHolyAction(ALL_LINES));
        return ret;
    }

    public static BattleHero createSpecialFrontHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeSpecialFront(), 1);
    }

    public static BattleHero createSpecialBackHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeSpecialBack(), 1);
    }

    public static BattleHero createSpecialMiddleHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeSpecialMiddle(), 1);
    }

    public static BattleHero createSpecialBackAndAllHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeSpecialBackAndAll(), 1);
    }

    private static BattleHeroType createHeroTypeActionFront() {
        BattleHeroType ret = new BattleHeroType();
        ret.getDefaultActions().add(createBasicDmgFireAction(F));
        return ret;
    }

    private static BattleHeroType createHeroTypeActionBack() {
        BattleHeroType ret = new BattleHeroType();
        ret.getDefaultActions().add(createBasicDmgFireAction(B));
        return ret;
    }

    private static BattleHeroType createHeroTypeActionMiddle() {
        BattleHeroType ret = new BattleHeroType();
        ret.getDefaultActions().add(createBasicDmgFireAction(M));
        return ret;
    }

    private static BattleHeroType createHeroTypeActionBackAndAll() {
        BattleHeroType ret = new BattleHeroType();
        ret.getDefaultActions().add(createBasicDmgFireAction(B));
        ret.getDefaultActions().add(createBasicDmgHolyAction(ALL_LINES));
        return ret;
    }

    public static BattleHero createActionFrontHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeActionFront(), 1);
    }

    public static BattleHero createActionBackHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeActionBack(), 1);
    }

    public static BattleHero createActionMiddleHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeActionMiddle(), 1);
    }

    public static BattleHero createActionBackAndAllHero() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeActionBackAndAll(), 1);
    }

    private static BattleHeroType createHeroTypeSmall() {
        BattleHeroType ret = new BattleHeroType();
        ret.setSize(HeroSize.SMALL);
        return ret;
    }

    public static BattleHero createBasicHeroSmall() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeSmall(), 1);
    }

    private static BattleHeroType createHeroTypeMedium() {
        BattleHeroType ret = new BattleHeroType();
        ret.setSize(HeroSize.MEDIUM);
        return ret;
    }

    public static BattleHero createBasicHeroMedium() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeMedium(), 1);
    }

    private static BattleHeroType createHeroTypeLarge() {
        BattleHeroType ret = new BattleHeroType();
        ret.setSize(HeroSize.LARGE);
        return ret;
    }

    public static BattleHero createBasicHeroLarge() {
        return new BattleHero(UUID.randomUUID().toString(), createHeroTypeLarge(), 1);
    }

    private static BattleHeroType createHeroTypeBlunt() {
        BattleHeroType ret = new BattleHeroType();
        ret.setSize(HeroSize.SMALL);
        ret.getSpecialActions().add(createBasicDmgBluntAction(ALL_LINES));
        return ret;
    }

    private static BattleHeroType createHeroTypeFire() {
        BattleHeroType ret = new BattleHeroType();
        ret.getSpecialActions().add(createBasicDmgFireAction(ALL_LINES));
        return ret;
    }

    public static BattleHero createBasicBluntNoCritNoDodge100HpHero() {
        BattleHero ret = new BattleHero(UUID.randomUUID().toString(), createHeroTypeBlunt(), 1);
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

    public static BattleHero createBasicBluntNoCritNoDodge10000HpHero() {
        BattleHero ret = createBasicFire100CritNoDodge100HpHero();
        ret.setHp(10000);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicBluntNoCritNoDodge50HpHero() {

        BattleHero ret = createBasicFire100CritNoDodge100HpHero();
        ret.setHp(50);

        ret.prepareToBattle();
        return ret;

    }

    public static BattleHero createBasicBluntNoCritNoDodge100HpHero10XFaster() {
        BattleHero ret = createBasicFire100CritNoDodge100HpHero();
        ret.setSpeed(10);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicBluntNoCritNoDodge200Hp10000SpeedHero() {
        BattleHero ret = createBasicBluntNoCritNoDodge200HpHero();
        ret.setSpeed(10000);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicBluntNoCritNoDodge200HpHero() {
        BattleHero ret = createBasicBluntNoCritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicFireNoCritNoDodge100HpHero() {
        BattleHero ret = new BattleHero(UUID.randomUUID().toString(), createHeroTypeFire(), 1);
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

    public static BattleHero createBasicFireNoCritNoDodge200HpHero() {
        BattleHero ret = createBasicFireNoCritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicBlunt100CritNoDodge100HpHero() {
        BattleHero ret = createBasicBluntNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicBlunt100CritNoDodge200HpHero() {
        BattleHero ret = createBasicBlunt100CritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicFire100CritNoDodge100HpHero() {
        BattleHero ret = createBasicFireNoCritNoDodge100HpHero();
        ret.setCritChance(100);
        ret.setCritDamage(100);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicFire100CritNoDodge200HpHero() {
        BattleHero ret = createBasicFire100CritNoDodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicBluntNoCrit100Dodge100HpHero() {
        BattleHero ret = createBasicBluntNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicBluntNoCrit100Dodge200HpHero() {
        BattleHero ret = createBasicBluntNoCrit100Dodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicFireNoCrit100Dodge100HpHero() {
        BattleHero ret = createBasicFireNoCritNoDodge100HpHero();
        ret.setDodgeChance(100);

        ret.prepareToBattle();
        return ret;
    }

    public static BattleHero createBasicFireNoCrit100Dodge200HpHero() {
        BattleHero ret = createBasicFireNoCrit100Dodge100HpHero();
        ret.setHp(200);

        ret.prepareToBattle();
        return ret;
    }

    public static BattlePositionedHero createBasicAttackPositionedHeroMaxEnergy(FormationPosition battlePositionType, BattleHero hero) {
        BattlePositionedHero ret = new BattlePositionedHero(BattleTeamType.ATTACK_TEAM, battlePositionType, hero);
        ret.setEnergy(IdleConstants.MAX_ENERGY);
        ret.getHero().setCanDoSpecialAction(Boolean.TRUE);
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

    public static List<BattlePositionedHero> createBattlePositionedHeroes(FormationPosition[] pos) {
        List<BattlePositionedHero> ret = new ArrayList<>();
        for (FormationPosition fp : pos) {
            BattlePositionedHero bph = createBasicPositionedHero(fp, createBasicBluntNoCritNoDodge100HpHero());
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
        ae.setPercentage(100);
        ae.setDamageType(DamageType.BLUNT);
        ae.setOverSameTeam(Boolean.FALSE);
        ae.setTargetType(tt);

        return ae;
    }

    public static ActionEffect createFireActionEffect(TargetType tt) {
        ActionEffect ae = new ActionEffect();
        ae.setPercentage(100);
        ae.setDamageType(DamageType.FIRE);
        ae.setOverSameTeam(Boolean.FALSE);
        ae.setTargetType(tt);

        return ae;
    }
}
