package com.idle.game.tests;

import com.idle.game.core.action.Action;
import com.idle.game.core.formation.type.FormationPosition;
import static com.idle.game.core.type.DamageType.BLUNT;
import static com.idle.game.core.type.DamageType.FIRE;
import static com.idle.game.core.type.DamageType.HOLY;
import static com.idle.game.tests.helper.TestHelper.*;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class NextActionTests {

    @Test
    public void testPositionedHeroBackAndActionBack() {

        Action a = createBasicAttackPositionedHero(FormationPosition.B_0, createActionBackHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndActionBack() {

        Action a = createBasicAttackPositionedHero(FormationPosition.F_0, createActionBackHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndActionBack() {

        Action a = createBasicAttackPositionedHero(FormationPosition.M_0, createActionBackHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroBackAndActionMiddle() {

        Action a = createBasicAttackPositionedHero(FormationPosition.B_0, createActionMiddleHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndActionMiddle() {

        Action a = createBasicAttackPositionedHero(FormationPosition.F_0, createActionMiddleHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndActionMiddle() {

        Action a = createBasicAttackPositionedHero(FormationPosition.M_0, createActionMiddleHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroBackAndActionFront() {

        Action a = createBasicAttackPositionedHero(FormationPosition.B_0, createActionFrontHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndActionFront() {

        Action a = createBasicAttackPositionedHero(FormationPosition.F_0, createActionFrontHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndActionFront() {

        Action a = createBasicAttackPositionedHero(FormationPosition.M_0, createActionFrontHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroBackAndActionBackAndAll() {

        Action a = createBasicAttackPositionedHero(FormationPosition.B_0, createActionBackAndAllHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndActionBackAndAll() {

        Action a = createBasicAttackPositionedHero(FormationPosition.F_0, createActionBackAndAllHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(HOLY, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndActionBackAndAll() {

        Action a = createBasicAttackPositionedHero(FormationPosition.M_0, createActionBackAndAllHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(HOLY, a.getMainActionEffect().getDamageType());

    }

}
