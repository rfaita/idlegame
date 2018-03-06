package com.idle.game.tests;

import com.idle.game.core.battle.Battle;
import com.idle.game.core.battle.BattlePositionedHero;
import static com.idle.game.core.battle.type.BattleTeamType.*;
import com.idle.game.core.formation.type.FormationPosition;
import static com.idle.game.core.formation.type.FormationPosition.*;
import static com.idle.game.core.type.TargetType.*;
import static com.idle.game.tests.helper.TestHelper.*;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class TargetTests {

    @Test
    public void testSelfTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(SELF), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be ATTACK_TEAM", ATTACK_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());

    }

    @Test
    public void testBiggerLifeTarget() {

        Battle b = createBasicBattleWithPositionsBiggerAndLowerHp(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(BIGGER_LIFE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());

    }

    @Test
    public void testLowerLifeTarget() {

        Battle b = createBasicBattleWithPositionsBiggerAndLowerHp(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(LOWER_LIFE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());

    }

    @Test
    public void testAllTargets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL), bph);

        Assert.assertEquals("must be 9 targets", 9, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(2).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(3).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(4).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(5).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(6).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(7).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(8).getPosition());

    }

    @Test
    public void testAllFrontLineTargets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_FRONT_LINE), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{M_0, M_1, M_2, B_0, B_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_FRONT_LINE), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_FRONT_LINE), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_MIDDLE_LINE), bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

    public void testAllMiddleLineNoMiddleLineTargets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_0, F_1, F_2, B_0, B_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_MIDDLE_LINE), bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    public void testAllMiddleLineNoMiddleLineAndNoBackLineTargets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_0, F_1, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_MIDDLE_LINE), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_BACK_LINE), bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_2", B_2, ret.get(2).getPosition());

    }

    public void testAllBackLineNoBackLineTargets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_0, F_1, F_2, M_0, M_1, M_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_MIDDLE_LINE), bph);

        Assert.assertEquals("must be 3 targets", 3, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(2).getPosition());

    }

    public void testAllBackLineNoBackLineAndNoFrontLineTargets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{M_1, M_0, M_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(ALL_MIDDLE_LINE), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(NO_FRONT_LINE), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_0, F_1, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(NO_FRONT_LINE), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_2, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_2, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_2, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, M_0, M_2, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_2, M_2, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, M_0, M_2, F_1, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
    }

    @Test
    public void testInFrontPillarFAndMInFrontPillarFront1Targets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, M_0, M_1, M_2, F_1, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
    }

    @Test
    public void testInFrontPillarBAndMInFrontPillarFront1Targets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_1, B_2, M_0, M_1, M_2, F_0, F_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
    }

    @Test
    public void testInFrontTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());

    }

    @Test
    public void testInFrontNoFrontTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_1, F_2, M_0, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());

    }

    @Test
    public void testInFrontNoFrontAndMoreOn2Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_1, F_2, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());

    }

    @Test
    public void testInFrontNoFrontAndEqualsTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_1, B_2});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());

    }

    @Test
    public void testInFrontSmallPillarFront0Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarFront1Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarFront2Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_2, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarMiddle0Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarMiddle1Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarMiddle2Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_2, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarBack0Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_0", F_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarBack1Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarBack2Target() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_2, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_2", F_2, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarFront0AndNoFrontLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, B_1, M_2, M_1, M_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 2 targets", 2, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(1).getPosition());

    }

    @Test
    public void testInFrontSmallPillarFront0AndNoFrontLineAndNoMiddleLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, B_1});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(IN_FRONT_SMALL_PILLAR), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be B_0", B_0, ret.get(0).getPosition());

    }

    @Test
    public void testFrontLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(FRONT_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(F_0, F_1, F_2);
        Assert.assertTrue("must be F_0, F_1 OR F_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testFrontLineNoSelfTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(FRONT_LINE_NO_SELF), bph);

        List<FormationPosition> expected = Arrays.asList(F_1, F_2);

        for (int i = 0; i < 100; i++) {
            Assert.assertEquals("must be 1 targets", 1, ret.size());
            Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
            Assert.assertTrue("must be F_1 OR F_2", expected.contains(ret.get(0).getPosition()));
        }

    }

    @Test
    public void testFrontLineNoFrontLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, B_1, M_2, M_1, M_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(FRONT_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(M_0, M_1, M_2);
        Assert.assertTrue("must be M_0, M_1 OR M_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testFrontLineNoFrontLineAndMiddleLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, B_1});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(FRONT_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(B_0, B_1, B_2);
        Assert.assertTrue("must be B_0, B_1 OR B_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testMiddleLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(MIDDLE_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(M_0, M_1, M_2);
        Assert.assertTrue("must be M_0, M_1 OR M_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testMiddleLineNoSelfTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(M_1, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(MIDDLE_LINE_NO_SELF), bph);

        List<FormationPosition> expected = Arrays.asList(M_0, M_2);

        for (int i = 0; i < 100; i++) {
            Assert.assertEquals("must be 1 targets", 1, ret.size());
            Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
            Assert.assertTrue("must be M_0 OR M_2", expected.contains(ret.get(0).getPosition()));
        }

    }

    @Test
    public void testMiddleLineNoMiddleLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{B_0, B_2, B_1, F_2, F_1, F_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(MIDDLE_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(B_0, B_1, B_2);
        Assert.assertTrue("must be B_0, B_1 OR B_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testMiddleLineNoMiddleLineAndNoBackLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_2, F_1, F_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(MIDDLE_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(F_0, F_1, F_2);
        Assert.assertTrue("must be F_0, F_1 OR F_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testBackLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(BACK_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(B_0, B_1, B_2);
        Assert.assertTrue("must be B_0, B_1 OR B_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testBackLineNoSelfTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(B_2, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(BACK_LINE_NO_SELF), bph);

        List<FormationPosition> expected = Arrays.asList(B_0, B_1);

        for (int i = 0; i < 100; i++) {
            Assert.assertEquals("must be 1 targets", 1, ret.size());
            Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

            Assert.assertTrue("must be B_0 OR B_1", expected.contains(ret.get(0).getPosition()));
        }

    }

    @Test
    public void testBackLineNoBackLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{M_0, M_2, M_1, F_2, F_1, F_0});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(BACK_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(F_0, F_1, F_2);
        Assert.assertTrue("must be F_0, F_1 OR F_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testBackLineNoBackLineAndNoFrontLineTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{M_0, M_2, M_1});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(BACK_LINE), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(M_0, M_1, M_2);
        Assert.assertTrue("must be M_0, M_1 OR M_2", expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testRandomTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(RANDOM), bph);

        Assert.assertEquals("must be 1 targets", 1, ret.size());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());

        List<FormationPosition> expected = Arrays.asList(FormationPosition.values());
        Assert.assertTrue(expected.contains(ret.get(0).getPosition()));

    }

    @Test
    public void testRandomNoSelfTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<FormationPosition> expected = Arrays.asList(F_1, F_2, M_0, M_1, M_2, B_0, B_1, B_2);

        for (int i = 0; i < 1000; i++) {
            List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(RANDOM_NO_SELF), bph);

            Assert.assertEquals("must be 1 targets", 1, ret.size());
            Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
            Assert.assertTrue(expected.contains(ret.get(0).getPosition()));
        }

    }

    @Test
    public void testTwoRandomTarget() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(TWO_RANDOM), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(THREE_RANDOM), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(FOUR_RANDOM), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), new FormationPosition[]{F_1, F_2, B_1});

        BattlePositionedHero bph = createBasicAttackPositionedHero(F_0, createBasicMeleeMagicNoCritNoDodge100HpHero());

        List<BattlePositionedHero> ret = b.getTargetsOfActionEffect(createBasicActionEffect(FOUR_RANDOM), bph);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, F_0);

        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(2).getPosition());

    }

    public void testAdjacentFront1Targets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, F_1);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, F_2);

        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be F_1", F_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

    public void testAdjacentMiddle0Targets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, M_0);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, M_1);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, M_2);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, B_0);

        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_0", M_0, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(2).getPosition());

    }

    public void testAdjacentBack1Targets() {

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, B_1);

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

        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        List<BattlePositionedHero> ret = b.getHeroesByAdjacent(DEFENSE_TEAM, B_2);

        Assert.assertEquals("must be 3 targets", 3, ret.size());

        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(0).getBattleTeamType());
        Assert.assertEquals("must be M_1", M_1, ret.get(0).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(1).getBattleTeamType());
        Assert.assertEquals("must be B_1", B_1, ret.get(1).getPosition());
        Assert.assertEquals("must be DEFENSE_TEAM", DEFENSE_TEAM, ret.get(2).getBattleTeamType());
        Assert.assertEquals("must be M_2", M_2, ret.get(2).getPosition());

    }

}
