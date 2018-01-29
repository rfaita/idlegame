package com.idle.game.tests.helper;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.type.DamageType;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.Player;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static Player createPlayer(String id) {
        Player p1 = new Player();
        p1.setId(id);
        p1.setLevel(1);
        p1.setLinkedUser(id);
        p1.setName("test1");

        return p1;
    }

    public static Hero createHero(String id, String playerId) {
        Hero ret = new Hero();
        ret.setId(id);
        ret.setPlayer(playerId);
        ret.setLevel(1);
        return ret;
    }

    public static Hero createHeroMaxLevel(String id, String playerId) {
        Hero ret = createHero(id, playerId);
        ret.setLevel(IdleConstants.HERO_MAX_LEVEL);
        return ret;
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

        ret.setMinMaxLevelArmor(10);
        ret.setMaxMaxLevelArmor(15);
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
        ret.setMinMaxLevelLuck(1);
        ret.setMaxMaxLevelLuck(2);
        ret.setMinMaxLevelMagicResist(155);
        ret.setMaxMaxLevelMagicResist(233);
        ret.setMinMaxLevelSpeed(895);
        ret.setMaxMaxLevelSpeed(1000);

        return ret;
    }
}
