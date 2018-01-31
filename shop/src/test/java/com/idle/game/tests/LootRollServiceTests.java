package com.idle.game.tests;

import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.model.mongo.shop.LootRoll;
import com.idle.game.model.mongo.shop.LootRollValue;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.validation.ValidationException;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author rafael
 */
public class LootRollServiceTests {

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testLootRoll() {

        LootRoll lr = new LootRoll();

        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.POOR.toString(), 50.0));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.AVERAGE.toString(), 30.0));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.BEST.toString(), 19.0));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.SHINE.toString(), 0.9));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.UNIQUE.toString(), 0.1));

        Map<String, Integer> ret = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            String roll = lr.roll(HeroQuality.class);
            if (ret.containsKey(roll)) {
                ret.put(roll, ret.get(roll) + 1);
            } else {
                ret.put(roll, 1);
            }
        }

        LOG.log(Level.INFO, ret.toString());

        Assert.assertTrue("POOR must be greater or equals 49%", ret.get(HeroQuality.POOR.toString()) / 10000d >= 49.0);
        Assert.assertTrue("POOR must be less or equals 51%", ret.get(HeroQuality.POOR.toString()) / 10000d <= 51.0);
        Assert.assertTrue("AVERAGE must be greater or equals 29%", ret.get(HeroQuality.AVERAGE.toString()) / 10000d >= 29.0);
        Assert.assertTrue("AVERAGE must be less or equals 31%", ret.get(HeroQuality.AVERAGE.toString()) / 10000d <= 31.0);
        Assert.assertTrue("BEST must be greater or equals 18%", ret.get(HeroQuality.BEST.toString()) / 10000d >= 18.0);
        Assert.assertTrue("BEST must be less or equals 20%", ret.get(HeroQuality.BEST.toString()) / 10000d <= 20.0);
        Assert.assertTrue("SHINE must be greater or equals .8%", ret.get(HeroQuality.SHINE.toString()) / 10000d >= 0.8);
        Assert.assertTrue("SHINE must be less or equals 1.1%", ret.get(HeroQuality.SHINE.toString()) / 10000d <= 1.1);
        Assert.assertTrue("UNIQUE must be greater or equals 0%", ret.get(HeroQuality.UNIQUE.toString()) / 10000d >= 0);
        Assert.assertTrue("UNIQUE must be less or equals .2%", ret.get(HeroQuality.UNIQUE.toString()) / 10000d <= 0.2);

    }

    @Test
    public void testLootRollMore100Perc() {

        LootRoll lr = new LootRoll();

        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.POOR.toString(), 50.0));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.AVERAGE.toString(), 30.0));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.BEST.toString(), 19.0));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.SHINE.toString(), 0.9));
        lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.UNIQUE.toString(), 0.2));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("loot.roll.invalid");
        
        lr.roll(HeroQuality.class);

    }

}
