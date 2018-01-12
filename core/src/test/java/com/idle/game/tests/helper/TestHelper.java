package com.idle.game.tests.helper;

import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.formation.Formation;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.core.item.Item;
import com.idle.game.core.item.ItemQuality;
import com.idle.game.core.item.ItemType;
import com.idle.game.core.formation.PositionedHero;
import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.type.ActionType;
import static com.idle.game.core.action.type.ActionType.HEAL;
import com.idle.game.core.buff.type.BuffEffect;
import static com.idle.game.core.buff.type.BuffType.BUFF;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.type.TargetType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class TestHelper {

    private static HeroType createHeroTypePhysicalMelee() {
        HeroType ret = new HeroType();
        ret.setDamageType(DamageType.PHYSICAL);
        ret.setDistanceType(DistanceType.MELEE);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }

    private static HeroType createHeroTypePhysicalRanged() {
        HeroType ret = new HeroType();
        ret.setDamageType(DamageType.PHYSICAL);
        ret.setDistanceType(DistanceType.RANGED);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }

    private static HeroType createHeroTypeMagicMelee() {
        HeroType ret = new HeroType();
        ret.setDamageType(DamageType.MAGIC);
        ret.setDistanceType(DistanceType.MELEE);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }

    private static HeroType createHeroTypeMagicRanged() {
        HeroType ret = new HeroType();
        ret.setDamageType(DamageType.MAGIC);
        ret.setDistanceType(DistanceType.RANGED);
        ret.setDefaultAction(createBasicDmgAction());
        return ret;
    }
    

    public static BattleHero createBasicMeleePhysicalNoCritNoDodge100HpHero() {
        BattleHero ret = new BattleHero(UUID.randomUUID().toString(), createHeroTypePhysicalMelee(), 1);
        ret.setDmg(100);
        ret.setArmor(100);
        ret.setMagicResist(100);
        ret.setSpeed(1);
        ret.setLuck(1);
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
        ret.setArmor(100);
        ret.setMagicResist(100);
        ret.setSpeed(1);
        ret.setLuck(1);
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
        ret.setArmor(100);
        ret.setMagicResist(100);
        ret.setSpeed(1);
        ret.setLuck(1);
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
        ret.setArmor(100);
        ret.setMagicResist(100);
        ret.setSpeed(1);
        ret.setLuck(1);
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

    public static PositionedHero createBasicPositionedHero(FormationPosition battlePositionType, BattleHero hero) {
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
        healSkill.setMainActionEffect(new ActionEffect(ActionType.DMG, TargetType.LESS_LIFE, 100, DamageType.PHYSICAL, Boolean.FALSE));

        return healSkill;
    }

    public static Action createBasicHealSpell() {
        Action healSkill = new Action();
        healSkill.setMainActionEffect(new ActionEffect(ActionType.HEAL, TargetType.LESS_LIFE, 100, DamageType.MAGIC, Boolean.TRUE));

        return healSkill;
    }

    public static Action createHealSpellWithBuff() {
        Action healSkill = new Action();

        List<BuffEffect> bes = new ArrayList<>();
        bes.add(new BuffEffect(BUFF, 25, 0, 3, HEAL));

        healSkill.setMainActionEffect(new ActionEffect(ActionType.HEAL, TargetType.LESS_LIFE, 100, DamageType.MAGIC, Boolean.TRUE, bes));

        return healSkill;
    }
    
    public static HeroType createGenericHeroType() {
        HeroType ret = new HeroType();
        ret.setDamageType(DamageType.MAGIC);
        ret.setDistanceType(DistanceType.MELEE);
        ret.setMinBaseArmor(10);
        ret.setMaxBaseArmor(15);
        ret.setMinBaseCritChance(10);
        ret.setMaxBaseCritChance(15);
        ret.setMinBaseCritDamage(100);
        ret.setMaxBaseCritDamage(150);
        ret.setMinBaseDmg(1000);
        ret.setMaxBaseDmg(1500);
        ret.setMinBaseDodgeChance(10);
        ret.setMaxBaseDodgeChance(15);
        ret.setMinBaseHp(10000);
        ret.setMaxBaseHp(16500);
        ret.setMinBaseLuck(1);
        ret.setMaxBaseLuck(2);
        ret.setMinBaseMagicResist(155);
        ret.setMaxBaseMagicResist(233);
        ret.setMinBaseSpeed(895);
        ret.setMaxBaseSpeed(1000);
        
        ret.setMinMaxLevelUpIncArmor(10);
        ret.setMaxMaxLevelUpIncArmor(15);
        ret.setMinMaxLevelUpIncCritChance(10);
        ret.setMaxMaxLevelUpIncCritChance(15);
        ret.setMinMaxLevelUpIncCritDamage(100);
        ret.setMaxMaxLevelUpIncCritDamage(150);
        ret.setMinMaxLevelUpIncDmg(1000);
        ret.setMaxMaxLevelUpIncDmg(1500);
        ret.setMinMaxLevelUpIncDodgeChance(10);
        ret.setMaxMaxLevelUpIncDodgeChance(15);
        ret.setMinMaxLevelUpIncHp(10000);
        ret.setMaxMaxLevelUpIncHp(16500);
        ret.setMinMaxLevelUpIncLuck(1);
        ret.setMaxMaxLevelUpIncLuck(2);
        ret.setMinMaxLevelUpIncMagicResist(155);
        ret.setMaxMaxLevelUpIncMagicResist(233);
        ret.setMinMaxLevelUpIncSpeed(895);
        ret.setMaxMaxLevelUpIncSpeed(1000);
        
        return ret;
    }
}
