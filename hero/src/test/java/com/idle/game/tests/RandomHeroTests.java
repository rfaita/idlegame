package com.idle.game.tests;

import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.type.DefenseType;
import com.idle.game.model.Hero;
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

            for (DefenseType dt : DefenseType.values()) {
                Assert.assertTrue("Value must be greater or equal to 10", h.getBaseDefenses().get(dt) >= 10);
                Assert.assertTrue("Value must be lower or equal to 12", h.getBaseDefenses().get(dt) <= 12);
                Assert.assertTrue("Value must be greater or equal to 10", h.getMaxLevelDefenses().get(dt) >= 10);
                Assert.assertTrue("Value must be lower or equal to 12", h.getMaxLevelDefenses().get(dt) <= 12);
            }

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
            Assert.assertTrue("Value must be greater or equal to 895", h.getBaseSpeed() >= 895);
            Assert.assertTrue("Value must be lower or equal to 930", h.getBaseSpeed() <= 930);

            Assert.assertTrue("Value must be greater or equal to 10", h.getMaxLevelCritChance() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getMaxLevelCritChance() <= 12);
            Assert.assertTrue("Value must be greater or equal to 100", h.getMaxLevelCritDamage() >= 100);
            Assert.assertTrue("Value must be lower or equal to 120", h.getMaxLevelCritDamage() <= 127);
            Assert.assertTrue("Value must be greater or equal to 1000", h.getMaxLevelDmg() >= 1000);
            Assert.assertTrue("Value must be lower or equal to 1277", h.getMaxLevelDmg() <= 1277);
            Assert.assertTrue("Value must be greater or equal to 10", h.getMaxLevelDodgeChance() >= 10);
            Assert.assertTrue("Value must be lower or equal to 12", h.getMaxLevelDodgeChance() <= 12);
            Assert.assertTrue("Value must be greater or equal to 10000", h.getMaxLevelHp() >= 10000);
            Assert.assertTrue("Value must be lower or equal to 12167", h.getMaxLevelHp() <= 12167);
            Assert.assertTrue("Value must be greater or equal to 895", h.getMaxLevelSpeed() >= 895);
            Assert.assertTrue("Value must be lower or equal to 930", h.getMaxLevelSpeed() <= 930);
        }

    }

    @Test
    public void testRandomHeroAverageQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollHero(HeroQuality.AVERAGE, createGenericHeroType());

            for (DefenseType dt : DefenseType.values()) {
                Assert.assertTrue("Value must be greater or equal to 11", h.getBaseDefenses().get(dt) >= 11);
                Assert.assertTrue("Value must be lower or equal to 13", h.getBaseDefenses().get(dt) <= 13);
                Assert.assertTrue("Value must be greater or equal to 11", h.getMaxLevelDefenses().get(dt) >= 11);
                Assert.assertTrue("Value must be lower or equal to 13", h.getMaxLevelDefenses().get(dt) <= 13);
            }

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
            Assert.assertTrue("Value must be greater or equal to 930", h.getBaseSpeed() >= 930);
            Assert.assertTrue("Value must be lower or equal to 965", h.getBaseSpeed() <= 965);

            Assert.assertTrue("Value must be greater or equal to 11", h.getMaxLevelCritChance() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getMaxLevelCritChance() <= 13);
            Assert.assertTrue("Value must be greater or equal to 116", h.getMaxLevelCritDamage() >= 116);
            Assert.assertTrue("Value must be lower or equal to 133", h.getMaxLevelCritDamage() <= 133);
            Assert.assertTrue("Value must be greater or equal to 1166", h.getMaxLevelDmg() >= 1166);
            Assert.assertTrue("Value must be lower or equal to 1333", h.getMaxLevelDmg() <= 1333);
            Assert.assertTrue("Value must be greater or equal to 11", h.getMaxLevelDodgeChance() >= 11);
            Assert.assertTrue("Value must be lower or equal to 13", h.getMaxLevelDodgeChance() <= 13);
            Assert.assertTrue("Value must be greater or equal to 12166", h.getMaxLevelHp() >= 12166);
            Assert.assertTrue("Value must be lower or equal to 14333", h.getMaxLevelHp() <= 14333);
            Assert.assertTrue("Value must be greater or equal to 930", h.getMaxLevelSpeed() >= 930);
            Assert.assertTrue("Value must be lower or equal to 965", h.getMaxLevelSpeed() <= 965);
        }

    }

    @Test
    public void testRandomHeroBestQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollHero(HeroQuality.BEST, createGenericHeroType());

            for (DefenseType dt : DefenseType.values()) {
                Assert.assertTrue("Value must be greater or equal to 13", h.getBaseDefenses().get(dt) >= 13);
                Assert.assertTrue("Value must be lower or equal to 15", h.getBaseDefenses().get(dt) <= 15);
                Assert.assertTrue("Value must be greater or equal to 13", h.getMaxLevelDefenses().get(dt) >= 13);
                Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelDefenses().get(dt) <= 15);
            }

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
            Assert.assertTrue("Value must be greater or equal to 965", h.getBaseSpeed() >= 965);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getBaseSpeed() <= 1000);

            Assert.assertTrue("Value must be greater or equal to 13", h.getMaxLevelCritChance() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelCritChance() <= 15);
            Assert.assertTrue("Value must be greater or equal to 133", h.getMaxLevelCritDamage() >= 133);
            Assert.assertTrue("Value must be lower or equal to 150", h.getMaxLevelCritDamage() <= 150);
            Assert.assertTrue("Value must be greater or equal to 1333", h.getMaxLevelDmg() >= 1333);
            Assert.assertTrue("Value must be lower or equal to 1500", h.getMaxLevelDmg() <= 1500);
            Assert.assertTrue("Value must be greater or equal to 13", h.getMaxLevelDodgeChance() >= 13);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelDodgeChance() <= 15);
            Assert.assertTrue("Value must be greater or equal to 14333", h.getMaxLevelHp() >= 14333);
            Assert.assertTrue("Value must be lower or equal to 16500", h.getMaxLevelHp() <= 16500);
            Assert.assertTrue("Value must be greater or equal to 965", h.getMaxLevelSpeed() >= 965);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getMaxLevelSpeed() <= 1000);
        }

    }

    @Test
    public void testRandomHeroShineQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollShineHero(createGenericHeroType());

            for (DefenseType dt : DefenseType.values()) {
                Assert.assertTrue("Value must be equal to 15", h.getBaseDefenses().get(dt) <= 15);
                Assert.assertTrue("Value must be equal to 15", h.getMaxLevelDefenses().get(dt) <= 15);
            }

            Assert.assertTrue("Value must be equal to 15", h.getBaseCritChance() == 15);
            Assert.assertTrue("Value must be equal to 150", h.getBaseCritDamage() == 150);
            Assert.assertTrue("Value must be equal to 1500", h.getBaseDmg() == 1500);
            Assert.assertTrue("Value must be equal to 15", h.getBaseDodgeChance() == 15);
            Assert.assertTrue("Value must be equal to 16500", h.getBaseHp() == 16500);
            Assert.assertTrue("Value must be equal to 1000", h.getBaseSpeed() == 1000);

            Assert.assertTrue("Value must be equal to 15", h.getMaxLevelCritChance() == 15);
            Assert.assertTrue("Value must be equal to 150", h.getMaxLevelCritDamage() == 150);
            Assert.assertTrue("Value must be equal to 1500", h.getMaxLevelDmg() == 1500);
            Assert.assertTrue("Value must be equal to 15", h.getMaxLevelDodgeChance() == 15);
            Assert.assertTrue("Value must be equal to 16500", h.getMaxLevelHp() == 16500);
            Assert.assertTrue("Value must be equal to 1000", h.getMaxLevelSpeed() == 1000);
        }

    }

    @Test
    public void testRandomHeroUniqueQuality() {
        HeroService hs = new HeroService();

        for (int i = 0; i < 10000; i++) {

            Hero h = hs.rollUniqueHero(createGenericHeroType());

            for (DefenseType dt : DefenseType.values()) {
                Assert.assertTrue("Value must be greater or equal to 15", h.getBaseDefenses().get(dt) >= 15);
                Assert.assertTrue("Value must be lower or equal to 16", h.getBaseDefenses().get(dt) <= 16);
                Assert.assertTrue("Value must be greater or equal to 15", h.getMaxLevelDefenses().get(dt) >= 15);
                Assert.assertTrue("Value must be lower or equal to 16", h.getMaxLevelDefenses().get(dt) <= 16);
            }

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
            Assert.assertTrue("Value must be greater or equal to 1000", h.getBaseSpeed() >= 1000);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getBaseSpeed() <= 1100);

            Assert.assertTrue("Value must be greater or equal to 15", h.getMaxLevelCritChance() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelCritChance() <= 16);
            Assert.assertTrue("Value must be greater or equal to 150", h.getMaxLevelCritDamage() >= 150);
            Assert.assertTrue("Value must be lower or equal to 150", h.getMaxLevelCritDamage() <= 165);
            Assert.assertTrue("Value must be greater or equal to 1500", h.getMaxLevelDmg() >= 1500);
            Assert.assertTrue("Value must be lower or equal to 1500", h.getMaxLevelDmg() <= 1650);
            Assert.assertTrue("Value must be greater or equal to 15", h.getMaxLevelDodgeChance() >= 15);
            Assert.assertTrue("Value must be lower or equal to 15", h.getMaxLevelDodgeChance() <= 16);
            Assert.assertTrue("Value must be greater or equal to 16500", h.getMaxLevelHp() >= 16500);
            Assert.assertTrue("Value must be lower or equal to 16500", h.getMaxLevelHp() <= 18150);
            Assert.assertTrue("Value must be greater or equal to 1000", h.getMaxLevelSpeed() >= 1000);
            Assert.assertTrue("Value must be lower or equal to 1000", h.getMaxLevelSpeed() <= 1100);
        }

    }

}
