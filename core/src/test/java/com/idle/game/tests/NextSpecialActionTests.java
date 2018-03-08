package com.idle.game.tests;

import com.idle.game.core.action.Action;
import com.idle.game.core.battle.Battle;
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
public class NextSpecialActionTests {

    @Test
    public void testPositionedHeroBackAndSpecialActionBack() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.B_0, createSpecialBackHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndSpecialActionBack() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.F_0, createSpecialBackHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndSpecialActionBack() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.M_0, createSpecialBackHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroBackAndSpecialActionMiddle() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.B_0, createSpecialMiddleHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndSpecialActionMiddle() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.F_0, createSpecialMiddleHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndSpecialActionMiddle() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.M_0, createSpecialMiddleHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroBackAndSpecialActionFront() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.B_0, createSpecialFrontHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndSpecialActionFront() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.F_0, createSpecialFrontHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndSpecialActionFront() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.M_0, createSpecialFrontHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(BLUNT, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroBackAndSpecialActionBackAndAll() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.B_0, createSpecialBackAndAllHero()).getNextAction();

        Assert.assertTrue(a != null);
        Assert.assertEquals(FIRE, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroFrontAndSpecialActionBackAndAll() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.F_0, createSpecialBackAndAllHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(HOLY, a.getMainActionEffect().getDamageType());

    }

    @Test
    public void testPositionedHeroMiddleAndSpecialActionBackAndAll() {

        Action a = createBasicAttackPositionedHeroMaxEnergy(FormationPosition.M_0, createSpecialBackAndAllHero()).getNextAction();
        Assert.assertTrue(a != null);
        Assert.assertEquals(HOLY, a.getMainActionEffect().getDamageType());

    }
}
