package com.idle.game.tests;

import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.battle.Battle;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.core.action.type.SubActionType;
import static com.idle.game.tests.helper.TestHelper.*;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class BasicBattleTests {

    @Test
    public void basicBattleTestLimitTurns() throws Exception {

        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge10000HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge10000HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

    }

    @Test
    public void basicBattleTestDmgMelee1() throws Exception {

        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicRangedPhysicalNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicRangedPhysicalNoCritNoDodge100HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicRangedPhysicalNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicRangedPhysicalNoCritNoDodge200HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicMeleeMagicNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleeMagicNoCritNoDodge100HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicMeleeMagicNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleeMagicNoCritNoDodge200HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicRangedMagicNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicRangedMagicNoCritNoDodge100HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicRangedMagicNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicRangedMagicNoCritNoDodge200HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicMeleePhysicalNoCrit100Dodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCrit100Dodge100HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicMeleePhysical100CritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicRangedPhysical100CritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

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

        BattleHero h1 = createBasicRangedPhysical100CritNoDodge100HpHero();

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-100 dmg on first turn, but h2 only have 50 hp", new Integer(-100), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(2).getBattleEvent().getType());

    }

    //@Test
    public void basicBattleTestDmgMeleeWithChest1() throws Exception {

        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        h1.setChest(createBasicChest());

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();

        h2.setBoot(createBasicBoot());

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f1 win", f1, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-47 dmg on first turn", new Integer(-47), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-47 dmg on first turn", new Integer(-47), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-47 dmg on turn 2", new Integer(-47), b.getBattleLog().get(3).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-47 dmg on turn 2", new Integer(-47), b.getBattleLog().get(4).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 3", h1, b.getBattleLog().get(5).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 3", h2, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-47 dmg on turn 3", new Integer(-47), b.getBattleLog().get(5).getBattleEvent().getValue());

    }

}
