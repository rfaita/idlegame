package com.idle.game.tests;

import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.util.DiceUtil;
import java.util.logging.Level;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class DiceUtilTests {

    @Test
    public void testRandomPoorAttribute() {

        for (int i = 0; i < 10000; i++) {
            Integer r = DiceUtil.randomPoorAttribute(0, 100);
            Assert.assertTrue(r >= 0);
            Assert.assertTrue(r <= 34);
        }

    }

    @Test
    public void testRandomAverageAttribute() {

        for (int i = 0; i < 10000; i++) {
            Integer r = DiceUtil.randomAverageAttribute(0, 100);
            Assert.assertTrue(r >= 33);
            Assert.assertTrue(r <= 67);
        }

    }

    @Test
    public void testRandomBestAttribute() {

        for (int i = 0; i < 10000; i++) {
            Integer r = DiceUtil.randomBestAttribute(0, 100);
            Assert.assertTrue(r >= 66);
            Assert.assertTrue(r <= 100);
        }

    }

}
