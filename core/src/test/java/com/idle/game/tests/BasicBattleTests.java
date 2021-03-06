package com.idle.game.tests;

/**
 *
 * @author rafael
 */
public class BasicBattleTests {
//
//    @Test
//    public void basicBattleTestLimitTurns() throws Exception {
//
//        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge10000HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge10000HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f2 win", f2, b.doBattle());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgMelee1() throws Exception {
//
//        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(12).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgMelee2() throws Exception {
//
//        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f2 win", f2, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(11).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(11).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(11).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(13).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgRanged1() throws Exception {
//
//        BattleHero h1 = createBasicRangedPhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicRangedPhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(12).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgRanged2() throws Exception {
//
//        BattleHero h1 = createBasicRangedPhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicRangedPhysicalNoCritNoDodge200HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f2 win", f2, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(11).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(11).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(11).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(13).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgMeleeMagic1() throws Exception {
//
//        BattleHero h1 = createBasicMeleeMagicNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleeMagicNoCritNoDodge100HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(12).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgMeleeMagic2() throws Exception {
//
//        BattleHero h1 = createBasicMeleeMagicNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleeMagicNoCritNoDodge200HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f2 win", f2, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(11).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(11).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(11).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(13).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgRangedMagic1() throws Exception {
//
//        BattleHero h1 = createBasicRangedMagicNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicRangedMagicNoCritNoDodge100HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(12).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgRangedMagic2() throws Exception {
//
//        BattleHero h1 = createBasicRangedMagicNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicRangedMagicNoCritNoDodge200HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f2 win", f2, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(11).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(11).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(11).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(13).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgMeleeHero1100Dodge() throws Exception {
//
//        BattleHero h1 = createBasicMeleePhysicalNoCrit100Dodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("dodge dmg on first turn", SubActionType.DODGE, b.getBattleLog().get(5).getBattleEvent().getSubType());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(12).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgMeleeHero2100Dodge() throws Exception {
//
//        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCrit100Dodge100HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f2 win", f2, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("dodge dmg on first turn", SubActionType.DODGE, b.getBattleLog().get(4).getBattleEvent().getSubType());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("dodge dmg on first turn", SubActionType.DODGE, b.getBattleLog().get(10).getBattleEvent().getSubType());
//
//        Assert.assertEquals("h2 attack second on turn 2", h2, b.getBattleLog().get(11).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on turn 2", h1, b.getBattleLog().get(11).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on turn 2", new Integer(-50), b.getBattleLog().get(11).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(13).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgMeleeHero1100Crit() throws Exception {
//
//        BattleHero h1 = createBasicMeleePhysical100CritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-100 dmg on first turn", new Integer(-100), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-100 dmg on first turn", new Integer(-100), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(12).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgRangedHero1100Crit() throws Exception {
//
//        BattleHero h1 = createBasicRangedPhysical100CritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-100 dmg on first turn", new Integer(-100), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-100 dmg on turn 2", new Integer(-100), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(12).getBattleEvent().getActionType());
//
//    }
//
//    @Test
//    public void basicBattleTestDmgRangedHero1100Crit2() throws Exception {
//
//        BattleHero h1 = createBasicRangedPhysical100CritNoDodge100HpHero();
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-100 dmg on first turn, but h2 only have 50 hp", new Integer(-100), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("end of battle", ActionType.BATTLE_END, b.getBattleLog().get(6).getBattleEvent().getActionType());
//
//    }
//
//    //@Test
//    public void basicBattleTestDmgMeleeWithChest1() throws Exception {
//
//        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        h1.setChest(createBasicChest());
//
//        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));
//
//        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge100HpHero();
//
//        h2.setBoot(createBasicBoot());
//
//        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));
//
//        Battle b = new Battle(f1, f2);
//
//        Assert.assertEquals("expected f1 win", f1, b.doBattle());
//
//        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());
//
//        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-47 dmg on first turn", new Integer(-47), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-47 dmg on first turn", new Integer(-47), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(10).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(10).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-47 dmg on turn 2", new Integer(-47), b.getBattleLog().get(10).getBattleEvent().getValue());
//
//        Assert.assertEquals("h2 attack second on turn 2", h2, b.getBattleLog().get(4).getHeroOrigin().getHero());
//        Assert.assertEquals("h1 target second on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-47 dmg on turn 2", new Integer(-47), b.getBattleLog().get(4).getBattleEvent().getValue());
//
//        Assert.assertEquals("h1 attack first on turn 3", h1, b.getBattleLog().get(5).getHeroOrigin().getHero());
//        Assert.assertEquals("h2 target first on turn 3", h2, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
//        Assert.assertEquals("-47 dmg on turn 3", new Integer(-47), b.getBattleLog().get(5).getBattleEvent().getValue());
//
//    }

}
