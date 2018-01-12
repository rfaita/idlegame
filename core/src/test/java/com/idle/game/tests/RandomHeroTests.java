package com.idle.game.tests;

import com.idle.game.core.type.HeroQuality;
import com.idle.game.model.mongo.Hero;
import com.idle.game.server.service.HeroService;
import static com.idle.game.tests.helper.TestHelper.createGenericHeroType;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class RandomHeroTests {

    @Test
    public void testRandomHeroPoorQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollHero(HeroQuality.POOR, createGenericHeroType());

            Assert.assertTrue("Value must be greater or equal to 10", h.getBaseArmor() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getBaseArmor() <= 12);
            Assert.assertTrue("Value must be greater or equal to 10", h.getBaseCritChance() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getBaseCritChance() <= 12);
            Assert.assertTrue("Value must be greater or equal to 100", h.getBaseCritDamage() >= 100);
            Assert.assertTrue("Value must be lower or equal to 120", h.getBaseCritDamage() <= 127);
            Assert.assertTrue("Value must be greater or equal to 1000", h.getBaseDmg() >= 1000);
            Assert.assertTrue("Value must be lower or equal to 1277", h.getBaseDmg() <= 1277);
            Assert.assertTrue("Value must be greater or equal to 10", h.getBaseDodgeChance() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getBaseDodgeChance() <= 12);
            Assert.assertTrue("Value must be greater or equal to 10000", h.getBaseHp() >= 10000);
            Assert.assertTrue("Value must be lower or equal to 12167", h.getBaseHp() <= 12167);
            Assert.assertTrue("Value must be greater or equal to 1", h.getBaseLuck() >= 1);
            Assert.assertTrue("Value must be lower or equal to 2", h.getBaseLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 155", h.getBaseMagicResist() >= 155);
            Assert.assertTrue("Value must be lower or equal to 181", h.getBaseMagicResist() <= 181);
            Assert.assertTrue("Value must be greater or equal to 895", h.getBaseSpeed() >= 895);
            Assert.assertTrue("Value must be lower or equal to 930", h.getBaseSpeed() <= 930);

            Assert.assertTrue("Value must be greater or equal to 10", h.getMaxLevelUpIncArmor() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getMaxLevelUpIncArmor() <= 12);
            Assert.assertTrue("Value must be greater or equal to 10", h.getMaxLevelUpIncCritChance() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getMaxLevelUpIncCritChance() <= 12);
            Assert.assertTrue("Value must be greater or equal to 100", h.getMaxLevelUpIncCritDamage() >= 100);
            Assert.assertTrue("Value must be lower or equal to 120", h.getMaxLevelUpIncCritDamage() <= 127);
            Assert.assertTrue("Value must be greater or equal to 1000", h.getMaxLevelUpIncDmg() >= 1000);
            Assert.assertTrue("Value must be lower or equal to 1277", h.getMaxLevelUpIncDmg() <= 1277);
            Assert.assertTrue("Value must be greater or equal to 10", h.getMaxLevelUpIncDodgeChance() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getMaxLevelUpIncDodgeChance() <= 12);
            Assert.assertTrue("Value must be greater or equal to 10000", h.getMaxLevelUpIncHp() >= 10000);
            Assert.assertTrue("Value must be lower or equal to 12167", h.getMaxLevelUpIncHp() <= 12167);
            Assert.assertTrue("Value must be greater or equal to 1", h.getMaxLevelUpIncLuck() >= 1);
            Assert.assertTrue("Value must be lower or equal to 2", h.getMaxLevelUpIncLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 155", h.getMaxLevelUpIncMagicResist() >= 155);
            Assert.assertTrue("Value must be lower or equal to 181", h.getMaxLevelUpIncMagicResist() <= 181);
            Assert.assertTrue("Value must be greater or equal to 895", h.getMaxLevelUpIncSpeed() >= 895);
            Assert.assertTrue("Value must be lower or equal to 930", h.getMaxLevelUpIncSpeed() <= 930);
        }

    }

    @Test
    public void testRandomHeroAverageQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollHero(HeroQuality.AVERAGE, createGenericHeroType());

            Assert.assertTrue("Value must be greater or equal to 11", h.getBaseArmor() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getBaseArmor() <= 13);
            Assert.assertTrue("Value must be greater or equal to 11", h.getBaseCritChance() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getBaseCritChance() <= 13);
            Assert.assertTrue("Value must be greater or equal to 116", h.getBaseCritDamage() >= 116);
            Assert.assertTrue("Value must be lower or equal to 133", h.getBaseCritDamage() <= 133);
            Assert.assertTrue("Value must be greater or equal to 1166", h.getBaseDmg() >= 1166);
            Assert.assertTrue("Value must be lower or equal to 1333", h.getBaseDmg() <= 1333);
            Assert.assertTrue("Value must be greater or equal to 11", h.getBaseDodgeChance() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getBaseDodgeChance() <= 13);
            Assert.assertTrue("Value must be greater or equal to 12166", h.getBaseHp() >= 12166);
            Assert.assertTrue("Value must be lower or equal to 14333", h.getBaseHp() <= 14333);
            Assert.assertTrue("Value must be greater or equal to 1", h.getBaseLuck() >= 1);
            Assert.assertTrue("Value must be lower or equal to 2", h.getBaseLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 181", h.getBaseMagicResist() >= 181);
            Assert.assertTrue("Value must be lower or equal to 207", h.getBaseMagicResist() <= 207);
            Assert.assertTrue("Value must be greater or equal to 930", h.getBaseSpeed() >= 930);
            Assert.assertTrue("Value must be lower or equal to 965", h.getBaseSpeed() <= 965);
            
             Assert.assertTrue("Value must be greater or equal to 11", h.getMaxLevelUpIncArmor() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getMaxLevelUpIncArmor() <= 13);
            Assert.assertTrue("Value must be greater or equal to 11", h.getMaxLevelUpIncCritChance() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getMaxLevelUpIncCritChance() <= 13);
            Assert.assertTrue("Value must be greater or equal to 116", h.getMaxLevelUpIncCritDamage() >= 116);
            Assert.assertTrue("Value must be lower or equal to 133", h.getMaxLevelUpIncCritDamage() <= 133);
            Assert.assertTrue("Value must be greater or equal to 1166", h.getMaxLevelUpIncDmg() >= 1166);
            Assert.assertTrue("Value must be lower or equal to 1333", h.getMaxLevelUpIncDmg() <= 1333);
            Assert.assertTrue("Value must be greater or equal to 11", h.getMaxLevelUpIncDodgeChance() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getMaxLevelUpIncDodgeChance() <= 13);
            Assert.assertTrue("Value must be greater or equal to 12166", h.getMaxLevelUpIncHp() >= 12166);
            Assert.assertTrue("Value must be lower or equal to 14333", h.getMaxLevelUpIncHp() <= 14333);
            Assert.assertTrue("Value must be greater or equal to 1", h.getMaxLevelUpIncLuck() >= 1);
            Assert.assertTrue("Value must be lower or equal to 2", h.getMaxLevelUpIncLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 181", h.getMaxLevelUpIncMagicResist() >= 181);
            Assert.assertTrue("Value must be lower or equal to 207", h.getMaxLevelUpIncMagicResist() <= 207);
            Assert.assertTrue("Value must be greater or equal to 930", h.getMaxLevelUpIncSpeed() >= 930);
            Assert.assertTrue("Value must be lower or equal to 965", h.getMaxLevelUpIncSpeed() <= 965);
        }

    }

    @Test
    public void testRandomHeroBestQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollHero(HeroQuality.BEST, createGenericHeroType());

            Assert.assertTrue("Value must be greater or equal to 13", h.getBaseArmor() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getBaseArmor() <= 15);
            Assert.assertTrue("Value must be greater or equal to 13", h.getBaseCritChance() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getBaseCritChance() <= 15);
            Assert.assertTrue("Value must be greater or equal to 133", h.getBaseCritDamage() >= 133);
            Assert.assertTrue("Value must be lower or equal to 150", h.getBaseCritDamage() <= 150);
            Assert.assertTrue("Value must be greater or equal to 1333", h.getBaseDmg() >= 1333);
            Assert.assertTrue("Value must be lower or equal to 1500", h.getBaseDmg() <= 1500);
            Assert.assertTrue("Value must be greater or equal to 13", h.getBaseDodgeChance() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getBaseDodgeChance() <= 15);
            Assert.assertTrue("Value must be greater or equal to 14333", h.getBaseHp() >= 14333);
            Assert.assertTrue("Value must be lower or equal to 16500", h.getBaseHp() <= 16500);
            Assert.assertTrue("Value must be greater or equal to 1", h.getBaseLuck() >= 1);
            Assert.assertTrue("Value must be lower or equal to 2", h.getBaseLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 207", h.getBaseMagicResist() >= 207);
            Assert.assertTrue("Value must be lower or equal to 233", h.getBaseMagicResist() <= 233);
            Assert.assertTrue("Value must be greater or equal to 965", h.getBaseSpeed() >= 965);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getBaseSpeed() <= 1000);
            
            Assert.assertTrue("Value must be greater or equal to 13", h.getMaxLevelUpIncArmor() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelUpIncArmor() <= 15);
            Assert.assertTrue("Value must be greater or equal to 13", h.getMaxLevelUpIncCritChance() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelUpIncCritChance() <= 15);
            Assert.assertTrue("Value must be greater or equal to 133", h.getMaxLevelUpIncCritDamage() >= 133);
            Assert.assertTrue("Value must be lower or equal to 150", h.getMaxLevelUpIncCritDamage() <= 150);
            Assert.assertTrue("Value must be greater or equal to 1333", h.getMaxLevelUpIncDmg() >= 1333);
            Assert.assertTrue("Value must be lower or equal to 1500", h.getMaxLevelUpIncDmg() <= 1500);
            Assert.assertTrue("Value must be greater or equal to 13", h.getMaxLevelUpIncDodgeChance() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelUpIncDodgeChance() <= 15);
            Assert.assertTrue("Value must be greater or equal to 14333", h.getMaxLevelUpIncHp() >= 14333);
            Assert.assertTrue("Value must be lower or equal to 16500", h.getMaxLevelUpIncHp() <= 16500);
            Assert.assertTrue("Value must be greater or equal to 1", h.getMaxLevelUpIncLuck() >= 1);
            Assert.assertTrue("Value must be lower or equal to 2", h.getMaxLevelUpIncLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 207", h.getMaxLevelUpIncMagicResist() >= 207);
            Assert.assertTrue("Value must be lower or equal to 233", h.getMaxLevelUpIncMagicResist() <= 233);
            Assert.assertTrue("Value must be greater or equal to 965", h.getMaxLevelUpIncSpeed() >= 965);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getMaxLevelUpIncSpeed() <= 1000);
        }

    }

    @Test
    public void testRandomHeroShineQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollShineHero(createGenericHeroType());

            Assert.assertTrue("Value must be equal to 15", h.getBaseArmor() == 15);
            Assert.assertTrue("Value must be equal to 15", h.getBaseCritChance() == 15);
            Assert.assertTrue("Value must be equal to 150", h.getBaseCritDamage() == 150);
            Assert.assertTrue("Value must be equal to 1500", h.getBaseDmg() == 1500);
            Assert.assertTrue("Value must be equal to 15", h.getBaseDodgeChance() == 15);
            Assert.assertTrue("Value must be equal to 16500", h.getBaseHp() == 16500);
            Assert.assertTrue("Value must be equal to 2", h.getBaseLuck() == 2);
            Assert.assertTrue("Value must be equal to 233", h.getBaseMagicResist() == 233);
            Assert.assertTrue("Value must be equal to 1000", h.getBaseSpeed() == 1000);
            
            Assert.assertTrue("Value must be equal to 15", h.getMaxLevelUpIncArmor() == 15);
            Assert.assertTrue("Value must be equal to 15", h.getMaxLevelUpIncCritChance() == 15);
            Assert.assertTrue("Value must be equal to 150", h.getMaxLevelUpIncCritDamage() == 150);
            Assert.assertTrue("Value must be equal to 1500", h.getMaxLevelUpIncDmg() == 1500);
            Assert.assertTrue("Value must be equal to 15", h.getMaxLevelUpIncDodgeChance() == 15);
            Assert.assertTrue("Value must be equal to 16500", h.getMaxLevelUpIncHp() == 16500);
            Assert.assertTrue("Value must be equal to 2", h.getMaxLevelUpIncLuck() == 2);
            Assert.assertTrue("Value must be equal to 233", h.getMaxLevelUpIncMagicResist() == 233);
            Assert.assertTrue("Value must be equal to 1000", h.getMaxLevelUpIncSpeed() == 1000);
        }

    }

    @Test
    public void testRandomHeroUniqueQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollUniqueHero(createGenericHeroType());

            Assert.assertTrue("Value must be greater or equal to 15", h.getBaseArmor() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getBaseArmor() <= 16);
            Assert.assertTrue("Value must be greater or equal to 15", h.getBaseCritChance() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getBaseCritChance() <= 16);
            Assert.assertTrue("Value must be greater or equal to 150", h.getBaseCritDamage() >= 150);
            Assert.assertTrue("Value must be lower or equal to 150", h.getBaseCritDamage() <= 165);
            Assert.assertTrue("Value must be greater or equal to 1500", h.getBaseDmg() >= 1500);
            Assert.assertTrue("Value must be lower or equal to 1500", h.getBaseDmg() <= 1650);
            Assert.assertTrue("Value must be greater or equal to 15", h.getBaseDodgeChance() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getBaseDodgeChance() <= 16);
            Assert.assertTrue("Value must be greater or equal to 16500", h.getBaseHp() >= 16500);
            Assert.assertTrue("Value must be lower or equal to 16500", h.getBaseHp() <= 18150);
            Assert.assertTrue("Value must be greater or equal to 2", h.getBaseLuck() >= 2);
            Assert.assertTrue("Value must be lower or equal to 2", h.getBaseLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 233", h.getBaseMagicResist() >= 233);
            Assert.assertTrue("Value must be lower or equal to 233", h.getBaseMagicResist() <= 256);
            Assert.assertTrue("Value must be greater or equal to 1000", h.getBaseSpeed() >= 1000);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getBaseSpeed() <= 1100);
            
              Assert.assertTrue("Value must be greater or equal to 15", h.getBaseArmor() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelUpIncArmor() <= 16);
            Assert.assertTrue("Value must be greater or equal to 15", h.getMaxLevelUpIncCritChance() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelUpIncCritChance() <= 16);
            Assert.assertTrue("Value must be greater or equal to 150", h.getMaxLevelUpIncCritDamage() >= 150);
            Assert.assertTrue("Value must be lower or equal to 150", h.getMaxLevelUpIncCritDamage() <= 165);
            Assert.assertTrue("Value must be greater or equal to 1500", h.getMaxLevelUpIncDmg() >= 1500);
            Assert.assertTrue("Value must be lower or equal to 1500", h.getMaxLevelUpIncDmg() <= 1650);
            Assert.assertTrue("Value must be greater or equal to 15", h.getMaxLevelUpIncDodgeChance() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelUpIncDodgeChance() <= 16);
            Assert.assertTrue("Value must be greater or equal to 16500", h.getMaxLevelUpIncHp() >= 16500);
            Assert.assertTrue("Value must be lower or equal to 16500", h.getMaxLevelUpIncHp() <= 18150);
            Assert.assertTrue("Value must be greater or equal to 2", h.getMaxLevelUpIncLuck() >= 2);
            Assert.assertTrue("Value must be lower or equal to 2", h.getMaxLevelUpIncLuck() <= 2);
            Assert.assertTrue("Value must be greater or equal to 233", h.getMaxLevelUpIncMagicResist() >= 233);
            Assert.assertTrue("Value must be lower or equal to 233", h.getMaxLevelUpIncMagicResist() <= 256);
            Assert.assertTrue("Value must be greater or equal to 1000", h.getMaxLevelUpIncSpeed() >= 1000);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getMaxLevelUpIncSpeed() <= 1100);
        }

    }

}
