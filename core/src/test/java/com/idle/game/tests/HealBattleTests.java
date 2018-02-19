package com.idle.game.tests;

import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.battle.Battle;
import com.idle.game.core.buff.type.BuffEffectType;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.buff.type.BuffType;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.hero.BattleHero;
import static com.idle.game.tests.helper.TestHelper.*;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class HealBattleTests {

    @Test
    public void basicHealTest1() throws Exception {

        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge200Hp10000SpeedHero();
        h1.getHeroType().setDefaultAction(createBasicHealSpell());

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200Hp10000SpeedHero();
        h2.getHeroType().setDefaultAction(createBasicHealSpell());

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(4).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(4).getBattleEvent().getActionType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(4).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(5).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(5).getBattleEvent().getActionType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(5).getHeroesTarget().get(0).getHero().getCurrHp());

    }

    @Test
    public void basicHealTestWithBuff() throws Exception {

        BattleHero h1 = createBasicMeleePhysicalNoCritNoDodge200Hp10000SpeedHero();
        h1.getHeroType().setDefaultAction(createHealSpellWithBuff());

        BattleFormation f1 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h1));

        BattleHero h2 = createBasicMeleePhysicalNoCritNoDodge200Hp10000SpeedHero();
        h2.getHeroType().setDefaultAction(createHealSpellWithBuff());

        BattleFormation f2 = createBasicFormation(createBasicPositionedHero(FormationPosition.FRONT_1, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getActionType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(5).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(5).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(5).getBattleEvent().getActionType());
        Assert.assertEquals("buff effect type must be null", null, b.getBattleLog().get(5).getBattleEvent().getBuffEffectType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(5).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(7).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(7).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(7).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(7).getBattleEvent().getActionType());
        Assert.assertEquals("buff effect type must be null", null, b.getBattleLog().get(7).getBattleEvent().getBuffEffectType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(7).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal with buff on turn 3", h2, b.getBattleLog().get(11).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal with buff  on turn 3", h2, b.getBattleLog().get(11).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(11).getBattleEvent().getBuffEffectType().getBuffType());
        Assert.assertEquals("25 heal on turn 3", new Integer(25), b.getBattleLog().get(11).getBattleEvent().getValue());
        Assert.assertEquals("action type must be null", null, b.getBattleLog().get(11).getBattleEvent().getActionType());
        Assert.assertEquals("special skill", BuffEffectType.HEAL, b.getBattleLog().get(11).getBattleEvent().getBuffEffectType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(11).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h1 heal with buff on turn 3", h1, b.getBattleLog().get(12).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal with buff  on turn 3", h1, b.getBattleLog().get(12).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(12).getBattleEvent().getBuffEffectType().getBuffType());
        Assert.assertEquals("25 heal on turn 3", new Integer(25), b.getBattleLog().get(12).getBattleEvent().getValue());
        Assert.assertEquals("action type must be null", null, b.getBattleLog().get(12).getBattleEvent().getActionType());
        Assert.assertEquals("special skill", BuffEffectType.HEAL, b.getBattleLog().get(12).getBattleEvent().getBuffEffectType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(12).getHeroesTarget().get(0).getHero().getCurrHp());


    }

}
