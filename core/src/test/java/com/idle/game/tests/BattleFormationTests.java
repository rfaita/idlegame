package com.idle.game.tests;

import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.formation.type.FormationPosition;
import static com.idle.game.tests.helper.TestHelper.*;
import javax.validation.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author rafael
 */
public class BattleFormationTests {

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testHeroTypeSmallMax() {

        BattleFormation bf = createBasicFormation();

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.B_0, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.B_1, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.B_2, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.M_0, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.M_1, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.M_2, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_0, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_1, createBasicHeroSmall()));
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_2, createBasicHeroSmall()));

        expcetionExpect.expectMessage("Formation max size reached, BUG");
        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_2, createBasicHeroSmall()));

    }

    @Test
    public void testHeroTypeMediumError1() {

        BattleFormation bf = createBasicFormation();

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("can.not.allocate.hero.here");

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.B_0, createBasicHeroMedium()));

    }

    @Test
    public void testHeroTypeMediumError2() {

        BattleFormation bf = createBasicFormation();

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("can.not.allocate.hero.here");

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.B_1, createBasicHeroMedium()));

    }

    @Test
    public void testHeroTypeMediumError3() {

        BattleFormation bf = createBasicFormation();

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("can.not.allocate.hero.here");

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.B_2, createBasicHeroMedium()));

    }

    @Test
    public void testHeroTypeMediumSuccess1() {

        BattleFormation bf = createBasicFormation();

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.M_0, createBasicHeroMedium()));

        Assert.assertEquals(4, bf.getHeroes().size());
        Assert.assertEquals(new Integer(4), bf.getSize());

    }

    @Test
    public void testHeroTypeMediumSuccess2() {

        BattleFormation bf = createBasicFormation();

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.M_1, createBasicHeroMedium()));

        Assert.assertEquals(4, bf.getHeroes().size());
        Assert.assertEquals(new Integer(4), bf.getSize());

    }

    @Test
    public void testHeroTypeMediumSuccess3() {

        BattleFormation bf = createBasicFormation();

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("can.not.allocate.hero.here");

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.M_2, createBasicHeroMedium()));

    }

    @Test
    public void testHeroTypeMediumSuccess4() {

        BattleFormation bf = createBasicFormation();

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_0, createBasicHeroMedium()));

        Assert.assertEquals(4, bf.getHeroes().size());
        Assert.assertEquals(new Integer(4), bf.getSize());

    }

    @Test
    public void testHeroTypeMediumSuccess5() {

        BattleFormation bf = createBasicFormation();

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_1, createBasicHeroMedium()));

        Assert.assertEquals(4, bf.getHeroes().size());
        Assert.assertEquals(new Integer(4), bf.getSize());

    }

    @Test
    public void testHeroTypeMediumSuccess6() {

        BattleFormation bf = createBasicFormation();

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("can.not.allocate.hero.here");

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_2, createBasicHeroMedium()));

    }

    @Test
    public void testHeroTypeLargeError() {

        BattleFormation bf = createBasicFormation();

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("can.not.allocate.hero.here");

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.F_2, createBasicHeroLarge()));
    }

    @Test
    public void testHeroTypeLargeSuccess() {

        BattleFormation bf = createBasicFormation();

        bf.addBattlePositionedHero(createBasicAttackPositionedHero(FormationPosition.M_1, createBasicHeroLarge()));

        Assert.assertEquals(9, bf.getHeroes().size());
        Assert.assertEquals(new Integer(9), bf.getSize());

    }
}
