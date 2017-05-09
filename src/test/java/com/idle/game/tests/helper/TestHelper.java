package com.idle.game.tests.helper;

import com.idle.game.core.AtittudeType;
import com.idle.game.core.BattlePositionType;
import com.idle.game.core.DamageType;
import com.idle.game.core.Formation;
import com.idle.game.core.Hero;
import com.idle.game.core.HeroType;
import com.idle.game.core.Item;
import com.idle.game.core.ItemQuality;
import com.idle.game.core.ItemType;
import com.idle.game.core.Player;
import com.idle.game.core.PositionedHero;
import com.idle.game.core.Skill;
import com.idle.game.core.SkillEffect;
import com.idle.game.core.EffectType;
import com.idle.game.core.TargetType;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static HeroType createBasicHeroType(AtittudeType a) {
        return new HeroType(a);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock10000HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 0, -1, 0, 0, 10000);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock50HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 0, -1, 0, 0, 50);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero10XFaster(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 10, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 10000, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicMeleePhysicalNoCritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicRangedPhysicalNoCritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.PHYSICAL, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicRangedPhysicalNoCritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.PHYSICAL, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicMeleeMagicNoCritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.MAGIC, -1, null, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicMeleeMagicNoCritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.MAGIC, -1, null, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicRangedMagicNoCritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.MAGIC, 100, 100, 1, 1, 0, -1, 0, 0, 100);
    }

    public static Hero createBasicRangedMagicNoCritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.MAGIC, 100, 100, 1, 1, 0, -1, 0, 0, 200);
    }

    public static Hero createBasicMeleePhysical100CritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicMeleePhysical100CritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicRangedPhysical100CritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.PHYSICAL, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicRangedPhysical100CritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.PHYSICAL, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicMeleeMagic100CritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.MAGIC, -1, null, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicMeleeMagic100CritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.MAGIC, -1, null, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicRangedMagic100CritNoDodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.MAGIC, 100, 100, 1, 1, 100, 100, 0, 0, 100);
    }

    public static Hero createBasicRangedMagic100CritNoDodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.MAGIC, 100, 100, 1, 1, 100, 100, 0, 0, 200);
    }

    public static Hero createBasicMeleePhysicalNoCrit100DodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicMeleePhysicalNoCrit100DodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, -1, null, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static Hero createBasicRangedPhysicalNoCrit100DodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.PHYSICAL, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicRangedPhysicalNoCrit100DodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.PHYSICAL, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static Hero createBasicMeleeMagicNoCrit100DodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.MAGIC, -1, null, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicMeleeMagicNoCrit100DodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, 100, DamageType.MAGIC, -1, null, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static Hero createBasicRangedMagicNoCrit100DodgeNoBlock100HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.MAGIC, 100, 100, 1, 1, 0, -1, 100, 0, 100);
    }

    public static Hero createBasicRangedMagicNoCrit100DodgeNoBlock200HpHero(HeroType type) {
        return new Hero(UUID.randomUUID(), type, 1, -1, null, 100, DamageType.MAGIC, 100, 100, 1, 1, 0, -1, 100, 0, 200);
    }

    public static PositionedHero createBasicPositionedHero(BattlePositionType battlePositionType, Hero hero) {
        return new PositionedHero(battlePositionType, hero);
    }

    public static Formation createBasicFormation(PositionedHero... heroes) {
        return new Formation(heroes);
    }

    public static Player createBasicPlayer(String name) {
        return new Player(UUID.randomUUID(), name);
    }

    public static Item createBasicChest() {
        return new Item("", ItemQuality.NORMAL, ItemType.CHEST, 0, 0, 10, 10, 0, 0, 0, 0, 0, 0, 50);
    }

    public static Item createBasicBoot() {
        return new Item("", ItemQuality.NORMAL, ItemType.BOOT, 0, 0, 10, 10, 0, 0, 0, 0, 0, 0, 50);
    }

    public static Skill createBasicHealSpell() {
        Skill healSkill = new Skill();
        healSkill.setMainEffect(new SkillEffect(EffectType.DMG, TargetType.MORE_LIFE, 100, DamageType.MAGIC));
        healSkill.addSecundaryEffects(new SkillEffect(EffectType.HEAL, TargetType.LESS_LIFE, 100, 100, 0, DamageType.MAGIC));

        return healSkill;
    }

    public static Skill createHealSpellWithBuff() {
        Skill healSkill = new Skill();
        healSkill.setMainEffect(new SkillEffect(EffectType.DMG, TargetType.MORE_LIFE, 100, DamageType.MAGIC));
        healSkill.addSecundaryEffects(new SkillEffect(EffectType.HEAL, TargetType.LESS_LIFE, 100, 100, 3, DamageType.MAGIC));

        return healSkill;
    }

}
