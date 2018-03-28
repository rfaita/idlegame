package com.idle.game.tests.helper;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.type.DefenseType;
import com.idle.game.model.HeroType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.model.Hero;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static Hero createHero(String id, String userId) {
        Hero ret = new Hero();
        ret.setId(id);
        ret.setUserId(userId);
        ret.setLevel(1);
        return ret;
    }

    public static Hero createHeroMaxLevel(String id, String userId) {
        Hero ret = createHero(id, userId);
        ret.setLevel(IdleConstants.HERO_MAX_LEVEL);
        return ret;
    }

    public static HeroType createGenericHeroType() {
        HeroType ret = new HeroType();
        ret.setDistanceType(DistanceType.MELEE);
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
        ret.setMinBaseSpeed(895);
        ret.setMaxBaseSpeed(1000);

        ret.setMinMaxLevelCritChance(10);
        ret.setMaxMaxLevelCritChance(15);
        ret.setMinMaxLevelCritDamage(100);
        ret.setMaxMaxLevelCritDamage(150);
        ret.setMinMaxLevelDmg(1000);
        ret.setMaxMaxLevelDmg(1500);
        ret.setMinMaxLevelDodgeChance(10);
        ret.setMaxMaxLevelDodgeChance(15);
        ret.setMinMaxLevelHp(10000);
        ret.setMaxMaxLevelHp(16500);
        ret.setMinMaxLevelSpeed(895);
        ret.setMaxMaxLevelSpeed(1000);

        for (DefenseType dt : DefenseType.values()) {
            ret.setMinBaseDefense(dt, 10);
            ret.setMaxBaseDefense(dt, 15);
            ret.setMinMaxLevelDefense(dt, 10);
            ret.setMaxMaxLevelDefense(dt, 15);
        }

        return ret;
    }
}
