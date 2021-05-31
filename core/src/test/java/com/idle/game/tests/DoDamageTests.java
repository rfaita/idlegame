package com.idle.game.tests;

import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.battle.Battle;
import com.idle.game.core.battle.Event;
import com.idle.game.core.battle.BattleUnit;
import com.idle.game.core.formation.type.FormationPosition;
import static com.idle.game.core.type.DamageType.BLUNT;
import static com.idle.game.core.type.DamageType.FIRE;
import com.idle.game.core.type.TargetType;
import static com.idle.game.tests.helper.TestHelper.*;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class DoDamageTests {

    @Test
    public void testBasicDmg() {
        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        Event be = new Event();

        ActionEffect ae = createBasicActionEffect(TargetType.RANDOM);

        BattleUnit aPositionedHero = createBasicAttackPositionedHero(FormationPosition.F_0, createBasicBluntNoCritNoDodge100HpHero());
        BattleUnit tPositionedHero = createBasicDefensePositionedHero(FormationPosition.F_0, createBasicBluntNoCritNoDodge100HpHero());

        b.doDamage(be, aPositionedHero, ae, tPositionedHero, Boolean.FALSE);

        Assert.assertEquals(new Integer(-50), be.getValue());
        Assert.assertEquals(BLUNT, be.getDamageType());

    }

    @Test
    public void testCritDmg() {
        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        Event be = new Event();

        ActionEffect ae = createBasicActionEffect(TargetType.RANDOM);

        BattleUnit aPositionedHero = createBasicAttackPositionedHero(FormationPosition.F_0, createBasicBlunt100CritNoDodge100HpHero());
        BattleUnit tPositionedHero = createBasicDefensePositionedHero(FormationPosition.F_0, createBasicBluntNoCritNoDodge100HpHero());

        b.doDamage(be, aPositionedHero, ae, tPositionedHero, Boolean.FALSE);

        Assert.assertEquals(new Integer(-100), be.getValue());
        Assert.assertEquals(BLUNT, be.getDamageType());

    }

    @Test
    public void testBasicSpecialDmg() {
        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        Event be = new Event();

        ActionEffect ae = createFireActionEffect(TargetType.RANDOM);

        BattleUnit aPositionedHero = createBasicAttackPositionedHero(FormationPosition.F_0, createBasicBluntNoCritNoDodge100HpHero());
        BattleUnit tPositionedHero = createBasicDefensePositionedHero(FormationPosition.F_0, createBasicBluntNoCritNoDodge100HpHero());

        b.doDamage(be, aPositionedHero, ae, tPositionedHero, Boolean.TRUE);

        Assert.assertEquals(new Integer(-50), be.getValue());
        Assert.assertEquals(FIRE, be.getDamageType());

    }

    @Test
    public void testCritSpecialDmg() {
        Battle b = createBasicBattleWithPositions(FormationPosition.values(), FormationPosition.values());

        Event be = new Event();

        ActionEffect ae = createFireActionEffect(TargetType.RANDOM);

        BattleUnit aPositionedHero = createBasicAttackPositionedHero(FormationPosition.F_0, createBasicBlunt100CritNoDodge100HpHero());
        BattleUnit tPositionedHero = createBasicDefensePositionedHero(FormationPosition.F_0, createBasicBluntNoCritNoDodge100HpHero());

        b.doDamage(be, aPositionedHero, ae, tPositionedHero, Boolean.TRUE);

        Assert.assertEquals(new Integer(-100), be.getValue());
        Assert.assertEquals(FIRE, be.getDamageType());

    }

}
