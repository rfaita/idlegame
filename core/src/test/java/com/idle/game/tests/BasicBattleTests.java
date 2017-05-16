package com.idle.game.tests;

import com.idle.game.core.ActionType;
import com.idle.game.core.AtittudeType;
import com.idle.game.core.Battle;
import com.idle.game.core.BattlePositionType;
import com.idle.game.core.Formation;
import com.idle.game.core.Hero;
import com.idle.game.core.SubActionType;
import com.idle.game.core.exception.ValidationException;
import static com.idle.game.tests.helper.TestHelper.*;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author rafael
 */
public class BasicBattleTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void basicBattleTestValidationFormationAttack() throws Exception {

        thrown.expect(ValidationException.class);
        thrown.expectMessage("not.have.attack.formation");

        Battle b = new Battle(null, null);

        b.doBattle();
    }

    @Test
    public void basicBattleTestValidationFormationDefense() throws Exception {

        thrown.expect(ValidationException.class);
        thrown.expectMessage("not.have.defense.formation");

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Battle b = new Battle(f1, null);

        b.doBattle();
    }

    @Test
    public void basicBattleTestLimitTurns() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock10000HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock10000HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

    }

    @Test
    public void basicBattleTestDmgMelee1() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(4).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMelee2() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(5).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgRanged1() throws Exception {

        Hero h1 = createBasicRangedPhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicRangedPhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(4).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgRanged2() throws Exception {

        Hero h1 = createBasicRangedPhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicRangedPhysicalNoCritNoDodgeNoBlock200HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(5).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMeleeMagic1() throws Exception {

        Hero h1 = createBasicMeleeMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleeMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(4).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMeleeMagic2() throws Exception {

        Hero h1 = createBasicMeleeMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleeMagicNoCritNoDodgeNoBlock200HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(5).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgRangedMagic1() throws Exception {

        Hero h1 = createBasicRangedMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicRangedMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(4).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgRangedMagic2() throws Exception {

        Hero h1 = createBasicRangedMagicNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicRangedMagicNoCritNoDodgeNoBlock200HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(5).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMeleeHero1100Dodge() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCrit100DodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("dodge dmg on first turn", SubActionType.DODGE, b.getBattleLog().get(2).getBattleEvent().getSubType());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(4).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMeleeHero2100Dodge() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCrit100DodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("dodge dmg on first turn", SubActionType.DODGE, b.getBattleLog().get(1).getBattleEvent().getSubType());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("dodge dmg on first turn", SubActionType.DODGE, b.getBattleLog().get(1).getBattleEvent().getSubType());

        Assert.assertEquals("h2 attack second on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on turn 2", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(5).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMeleeHero1100Crit() throws Exception {

        Hero h1 = createBasicMeleePhysical100CritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-100 dmg on first turn", new Integer(-100), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-100 dmg on first turn", new Integer(-100), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(4).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgRangedHero1100Crit() throws Exception {

        Hero h1 = createBasicRangedPhysical100CritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-100 dmg on first turn", new Integer(-100), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-100 dmg on turn 2", new Integer(-100), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(4).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgRangedHero1100Crit2() throws Exception {

        Hero h1 = createBasicRangedPhysical100CritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-100 dmg on first turn, but h2 only have 50 hp", new Integer(-100), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(2).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMeleeHero110xFaster() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero10XFaster(AtittudeType.AGGRESSIVE);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("double act, h1 attack first on first turn", h1, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("double act, h2 target first on first turn", h2, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("double act, -50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(3).getBattleEvent().getType());

    }

    @Test
    public void basicBattleTestDmgMeleeWithChest1() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        h1.setChest(createBasicChest());

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock100HpHero(AtittudeType.AGGRESSIVE);

        h2.setBoot(createBasicBoot());

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-52 dmg on first turn", new Integer(-52), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-52 dmg on first turn", new Integer(-52), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-52 dmg on turn 2", new Integer(-52), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-52 dmg on turn 2", new Integer(-52), b.getBattleLog().get(4).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 3", h1, b.getBattleLog().get(5).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 3", h2, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-52 dmg on turn 3", new Integer(-52), b.getBattleLog().get(5).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(6).getBattleEvent().getType());

    }

}
