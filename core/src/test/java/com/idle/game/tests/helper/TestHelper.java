package com.idle.game.tests.helper;

import com.idle.game.core.type.AtittudeType;
import com.idle.game.core.type.BattlePositionType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.Formation;
import com.idle.game.core.Hero;
import com.idle.game.core.type.HeroType;
import com.idle.game.core.item.Item;
import com.idle.game.core.item.ItemQuality;
import com.idle.game.core.type.ItemType;
import com.idle.game.core.PositionedHero;
import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.type.ActionType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.type.TargetType;

/**
 *
 * @author rafael
 */
public class TestHelper {

    private static HeroType createHeroTypePhysicalMelee(AtittudeType a) {
        HeroType ret = new HeroType(a);
        ret.setDamageType(DamageType.PHYSICAL);
        ret.setDistanceType(DistanceType.MELEE);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }

    private static HeroType createHeroTypePhysicalRanged(AtittudeType a) {
        HeroType ret = new HeroType(a);
        ret.setDamageType(DamageType.PHYSICAL);
        ret.setDistanceType(DistanceType.RANGED);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }

    private static HeroType createHeroTypeMagicMelee(AtittudeType a) {
        HeroType ret = new HeroType(a);
        ret.setDamageType(DamageType.MAGIC);
        ret.setDistanceType(DistanceType.MELEE);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }

    private static HeroType createHeroTypeMagicRanged(AtittudeType a) {
        HeroType ret = new HeroType(a);
        ret.setDamageType(DamageType.MAGIC);
        ret.setDistanceType(DistanceType.RANGED);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock10000HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 10000);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock50HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 50);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero10XFaster(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 10, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 10000, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicRangedPhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicRangedPhysicalNoCritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicMeleeMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicMeleeMagicNoCritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicRangedMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicRangedMagicNoCritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicMeleePhysical100CritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicMeleePhysical100CritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicRangedPhysical100CritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalRanged(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicRangedPhysical100CritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalRanged(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicMeleeMagic100CritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicMelee(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicMeleeMagic100CritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicMelee(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicRangedMagic100CritNoDodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicRanged(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicRangedMagic100CritNoDodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicRanged(a), 1, 100, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicMeleePhysicalNoCrit100DodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicMeleePhysicalNoCrit100DodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static Hero createBasicRangedPhysicalNoCrit100DodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicRangedPhysicalNoCrit100DodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypePhysicalRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static Hero createBasicMeleeMagicNoCrit100DodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicMeleeMagicNoCrit100DodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicMelee(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static Hero createBasicRangedMagicNoCrit100DodgeNoBlock100HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicRangedMagicNoCrit100DodgeNoBlock200HpHero(AtittudeType a) {
        return new Hero(createHeroTypeMagicRanged(a), 1, 100, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static PositionedHero createBasicPositionedHero(BattlePositionType battlePositionType, Hero hero) {
        return new PositionedHero(battlePositionType, hero);
    }

    public static Formation createBasicFormation(PositionedHero... heroes) {
        return new Formation(heroes);
    }

    public static Item createBasicChest() {
        return new Item("", ItemQuality.NORMAL, ItemType.CHEST, 0, 10, 10, 0, 0, 0, 0, 0, 0, 50);
    }

    public static Item createBasicBoot() {
        return new Item("", ItemQuality.NORMAL, ItemType.BOOT, 0, 10, 10, 0, 0, 0, 0, 0, 0, 50);
    }

    public static Action createBasicDmgAction() {
        Action healSkill = new Action();
        healSkill.setMainActionEffect(new ActionEffect(ActionType.DMG, TargetType.LESS_LIFE, 100, 0, 0, DamageType.PHYSICAL, Boolean.FALSE));

        return healSkill;
    }

    public static Action createBasicHealSpell() {
        Action healSkill = new Action();
        healSkill.setMainActionEffect(new ActionEffect(ActionType.HEAL, TargetType.LESS_LIFE, 100, 0, 0, DamageType.MAGIC, Boolean.TRUE));

        return healSkill;
    }

    public static Action createHealSpellWithBuff() {
        Action healSkill = new Action();
        healSkill.setMainActionEffect(new ActionEffect(ActionType.HEAL, TargetType.LESS_LIFE, 100, 0, 0, DamageType.MAGIC, Boolean.TRUE));
        healSkill.addSecundaryActionsEffects(new ActionEffect(ActionType.HEAL, TargetType.LESS_LIFE, 25, 0, 3, DamageType.MAGIC, Boolean.TRUE));

        return healSkill;
    }

}
