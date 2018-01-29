package com.idle.game.tests;

import com.idle.game.server.type.EloOutcome;
import com.idle.game.server.util.EloRating;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class EloRatingTests {

    @Test
    public void test2500Vs500Lose() {

        Integer value = EloRating.calculate2PlayersRating(2500, 500, EloOutcome.LOSE);

        Assert.assertEquals("elo expected must be 2484", new Integer(2484), value);

    }

    @Test
    public void test2500Vs500Win() {

        Integer value = EloRating.calculate2PlayersRating(2500, 500, EloOutcome.WIN);

        Assert.assertEquals("elo expected must be 2500", new Integer(2500), value);

    }

    @Test
    public void test500Vs2500Lose() {

        Integer value = EloRating.calculate2PlayersRating(500, 2500, EloOutcome.LOSE);

        Assert.assertEquals("elo expected must be 500", new Integer(500), value);

    }

    @Test
    public void test500Vs2500Win() {

        Integer value = EloRating.calculate2PlayersRating(500, 2500, EloOutcome.WIN);

        Assert.assertEquals("elo expected must be 532", new Integer(532), value);

    }

    @Test
    public void test500Vs1000Win() {

        Integer value = EloRating.calculate2PlayersRating(500, 1000, EloOutcome.WIN);

        Assert.assertEquals("elo expected must be 530", new Integer(530), value);

    }

    @Test
    public void test500Vs1000Lose() {

        Integer value = EloRating.calculate2PlayersRating(500, 1000, EloOutcome.LOSE);

        Assert.assertEquals("elo expected must be 498", new Integer(498), value);

    }

    @Test
    public void test1000Vs500Lose() {

        Integer value = EloRating.calculate2PlayersRating(1000, 500, EloOutcome.LOSE);

        Assert.assertEquals("elo expected must be 970", new Integer(970), value);

    }

    @Test
    public void test1000Vs500Win() {

        Integer value = EloRating.calculate2PlayersRating(1000, 500, EloOutcome.WIN);

        Assert.assertEquals("elo expected must be 1002", new Integer(1002), value);

    }

    @Test
    public void test1000Vs1000Win() {

        Integer value = EloRating.calculate2PlayersRating(1000, 1000, EloOutcome.WIN);

        Assert.assertEquals("elo expected must be 1016", new Integer(1016), value);

    }

    @Test
    public void teste2001Vs2001Win() {

        Integer value = EloRating.calculate2PlayersRating(2001, 2001, EloOutcome.WIN);

        Assert.assertEquals("elo expected must be 2013", new Integer(2013), value);

    }

    @Test
    public void teste2500Vs2500Win() {

        Integer value = EloRating.calculate2PlayersRating(2500, 2500, EloOutcome.WIN);

        Assert.assertEquals("elo expected must be 2508", new Integer(2508), value);

    }

    @Test
    public void test1000Vs1000Lose() {

        Integer value = EloRating.calculate2PlayersRating(1000, 1000, EloOutcome.LOSE);

        Assert.assertEquals("elo expected must be 984", new Integer(984), value);

    }

    @Test
    public void teste2001Vs2001Lose() {

        Integer value = EloRating.calculate2PlayersRating(2001, 2001, EloOutcome.LOSE);

        Assert.assertEquals("elo expected must be 1989", new Integer(1989), value);

    }

    @Test
    public void teste2500Vs2500Lose() {

        Integer value = EloRating.calculate2PlayersRating(2500, 2500, EloOutcome.LOSE);

        Assert.assertEquals("elo expected must be 2492", new Integer(2492), value);

    }

    @Test
    public void testeVeryHardRating() {

        int[] ret = EloRating.veryHardRatingRange(1000);

        Assert.assertEquals("elo expected must be 1500", new Integer(1500), new Integer(ret[0]));
        Assert.assertEquals("elo expected must be 1650", new Integer(1650), new Integer(ret[1]));

    }

    @Test
    public void testeHardRating() {

        int[] ret = EloRating.hardRatingRange(1000);

        Assert.assertEquals("elo expected must be 1100", new Integer(1100), new Integer(ret[0]));
        Assert.assertEquals("elo expected must be 1210", new Integer(1210), new Integer(ret[1]));

    }

    @Test
    public void testNormalRating() {

        int[] ret = EloRating.normalRatingRange(1000);

        Assert.assertEquals("elo expected must be 1000", new Integer(1000), new Integer(ret[0]));
        Assert.assertEquals("elo expected must be 1100", new Integer(1100), new Integer(ret[1]));

    }

    @Test
    public void testEasyRating() {

        int[] ret = EloRating.easyRatingRange(1000);

        Assert.assertEquals("elo expected must be 900", new Integer(900), new Integer(ret[0]));
        Assert.assertEquals("elo expected must be 990", new Integer(990), new Integer(ret[1]));

    }

}
