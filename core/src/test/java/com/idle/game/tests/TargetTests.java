package com.idle.game.tests;

import com.idle.game.core.battle.Battle;
import com.idle.game.core.battle.BattlePositionedHero;

import static com.idle.game.core.battle.type.BattleTeamType.*;

import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.formation.type.FormationPosition;

import static com.idle.game.core.formation.type.FormationPosition.*;
import static com.idle.game.tests.helper.TestHelper.*;

import java.util.Arrays;
import java.util.List;

import com.idle.game.core.target.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rafael
 */
public class TargetTests {

    @Test
    public void testSelfTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new SelfTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be ATTACK_TEAM", ATTACK_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());

    }

    @Test
    public void testBiggerLifeTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new HighLifeTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());

    }

    @Test
    public void testLowerLifeTarget() {
        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new LowerLifeTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());

    }


    @Test
    public void testAllFrontLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new FrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllFrontLineNoFrontLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{M_0, M_1, M_2, B_0, B_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new FrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllFrontLineNoFrontLineAndNoMiddleLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new FrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllMiddleLineTargets() {
        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new MiddleLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllMiddleLineNoMiddleLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_0, F_1, F_2, B_0, B_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new MiddleLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllMiddleLineNoMiddleLineAndNoBackLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_0, F_1, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new MiddleLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllBackLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new BackLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllBackLineNoBackLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_0, F_1, F_2, M_0, M_1, M_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new BackLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(2).getPosition());

    }

    @Test
    public void testAllBackLineNoBackLineAndNoFrontLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{M_1, M_0, M_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new BackLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

    @Test
    public void testNoFrontLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new NoFrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 6 targets", 6, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(3).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(3).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(4).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(4).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(5).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(5).getPosition());

    }

    @Test
    public void testNoFrontLineNoFrontLineTargets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_0, F_1, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new NoFrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarFront0Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarFront1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarFront2Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_2, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);
        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarMiddle0Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarMiddle1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarMiddle2Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_2, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarBack0Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarBack1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarBack2Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_2, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    @Test
    public void testInFrontPillarNoFrontPillarFront1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_2, M_0, M_2, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(2).getPosition());
    }

    @Test
    public void testInFrontPillarNoFrontLeftPillarFront1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_2, M_2, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());
    }

    @Test
    public void testInFrontPillarOneInFrontPillarFront1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_2, M_0, M_2, F_1, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
    }

    @Test
    public void testInFrontPillarFAndMInFrontPillarFront1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_2, M_0, M_1, M_2, F_1, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
    }

    @Test
    public void testInFrontPillarBAndMInFrontPillarFront1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_1, B_2, M_0, M_1, M_2, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontPillarTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
    }

    @Test
    public void testInFrontTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());

    }

    @Test
    public void testInFrontNoFrontTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_1, F_2, M_0, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());

    }

    @Test
    public void testInFrontNoFrontAndMoreOn2Target() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_1, F_2, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());

    }

    @Test
    public void testInFrontNoFrontAndEqualsTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new InFrontTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());

    }

    @Test
    public void testFrontLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomFrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(F_0, F_1, F_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be F_0, F_1 OR F_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testFrontLineNoSelfTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomFrontLineNoSelfTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(F_1, F_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be F_1 OR F_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testFrontLineNoFrontLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_2, B_1, M_2, M_1, M_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomFrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(M_0, M_1, M_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be M_0, M_1 OR M_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testFrontLineNoFrontLineAndMiddleLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_2, B_1});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomFrontLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(B_0, B_1, B_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be B_0, B_1 OR B_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testMiddleLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomMiddleLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(M_0, M_1, M_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be M_0, M_1 OR M_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testMiddleLineNoSelfTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomMiddleLineNoSelfTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(M_0, M_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be M_0 OR M_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testMiddleLineNoMiddleLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{B_0, B_2, B_1, F_2, F_1, F_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomMiddleLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(B_0, B_1, B_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be B_0, B_1 OR B_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testMiddleLineNoMiddleLineAndNoBackLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_2, F_1, F_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomMiddleLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(F_0, F_1, F_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be F_0, F_1 OR F_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testBackLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomBackLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(B_0, B_1, B_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be B_0, B_1 OR B_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testBackLineNoSelfTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_2, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomBackLineNoSelfTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(B_0, B_1);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be B_0 OR B_1", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testBackLineNoBackLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{M_0, M_2, M_1, F_2, F_1, F_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomBackLineNoSelfTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(F_0, F_1, F_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be F_0, F_1 OR F_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testBackLineNoBackLineAndNoFrontLineTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{M_0, M_2, M_1});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomBackLineTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        List<FormationPosition> expected = Arrays.asList(M_0, M_1, M_2);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue("must be M_0, M_1 OR M_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testRandomTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(FormationPosition.values());
        Assert.assertTrue(expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testRandomNoSelfTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new RandomNoSelfTarget();

        List<FormationPosition> expected = Arrays.asList(F_1, F_2, M_0, M_1, M_2, B_0, B_1, B_2);

        for (int i = 0; i < 1000; i++) {
            List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

            Assert.assertEquals("must be 1 targets", 1, ret.size());
            Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
            Assert.assertTrue(expected.contains(ret.get(0).getPosition()));
        }

    }

    @Test
    public void testTwoRandomTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new TwoRandomTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);


        Assert.assertEquals("must be 2 targets", 2, ret.size());
        List<FormationPosition> expected = Arrays.asList(FormationPosition.values());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(0).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(1).getPosition()));
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(1).getPosition());

    }

    @Test
    public void testThreeRandomTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new ThreeRandomTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        List<FormationPosition> expected = Arrays.asList(FormationPosition.values());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(0).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(1).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(2).getPosition()));
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(1).getPosition());
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(2).getPosition());
        Assert.assertNotSame(ret.get(1).getPosition(), ret.get(2).getPosition());

    }

    @Test
    public void testFourRandomTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new FourRandomTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 4 targets", 4, ret.size());
        List<FormationPosition> expected = Arrays.asList(FormationPosition.values());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(0).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(1).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(2).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(3).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(3).getPosition()));
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(1).getPosition());
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(2).getPosition());
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(3).getPosition());
        Assert.assertNotSame(ret.get(1).getPosition(), ret.get(2).getPosition());
        Assert.assertNotSame(ret.get(1).getPosition(), ret.get(3).getPosition());
        Assert.assertNotSame(ret.get(2).getPosition(), ret.get(3).getPosition());

    }

    public void testFourRandomOnlyThreeTarget() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(new FormationPosition[]{F_1, F_2, B_1});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new FourRandomTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        List<FormationPosition> expected = Arrays.asList(F_1, F_2, B_1);

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(0).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(1).getPosition()));
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertTrue(expected.contains(ret.get(2).getPosition()));
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(1).getPosition());
        Assert.assertNotSame(ret.get(0).getPosition(), ret.get(2).getPosition());
        Assert.assertNotSame(ret.get(1).getPosition(), ret.get(2).getPosition());

    }

    public void testAdjacentFront0Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(2).getPosition());

    }

    public void testAdjacentFront1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 5 targets", 5, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(2).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(3).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(3).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(4).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(4).getPosition());

    }

    public void testAdjacentFront2Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_2, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);


        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

    public void testAdjacentMiddle0Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 5 targets", 5, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(2).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(3).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(3).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(4).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(4).getPosition());

    }

    public void testAdjacentMiddle1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 8 targets", 8, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(2).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(3).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(3).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(4).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(4).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(5).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(5).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(6).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(6).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(7).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(7).getPosition());

    }

    public void testAdjacentMiddle2Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_2, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 5 targets", 5, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(2).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(3).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(3).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(4).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(4).getPosition());

    }

    public void testAdjacentBack0Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_0, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(2).getPosition());

    }

    public void testAdjacentBack1Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_1, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);


        Assert.assertEquals("must be 5 targets", 5, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(2).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(3).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(3).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(4).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(4).getPosition());

    }

    public void testAdjacentBack2Targets() {

        List<BattlePositionedHero> battlePositionedHeroes = createBattlePositionedHeroes(FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_2, createBasicBluntNoCritNoDodge100HpHero());

        Target target = new AdjacentTarget();

        List<BattlePositionedHero> ret = target.get(BattleTeamType.DEFENSE_TEAM, battlePositionedHeroes, bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

}
