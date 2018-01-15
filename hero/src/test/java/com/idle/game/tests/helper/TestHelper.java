package com.idle.game.tests.helper;

import com.idle.game.core.type.DamageType;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.core.type.DistanceType;

/**
 *
 * @author rafael
 */
public class TestHelper {

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
