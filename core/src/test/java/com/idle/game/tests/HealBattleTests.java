package com.idle.game.tests;

import com.idle.game.core.type.ActionType;
import com.idle.game.core.type.AtittudeType;
import com.idle.game.core.Battle;
import com.idle.game.core.type.BattlePositionType;
import com.idle.game.core.type.BuffType;
import com.idle.game.core.Formation;
import com.idle.game.core.Hero;
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

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(AtittudeType.AGGRESSIVE);
        h1.getHeroType().setDefaultAction(createBasicHealSpell());

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(AtittudeType.AGGRESSIVE);
        h2.getHeroType().setDefaultAction(createBasicHealSpell());

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(1).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(1).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(1).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(2).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(2).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(2).getHeroesTarget().get(0).getHero().getCurrHp());

    }

    @Test
    public void basicHealTestWithBuff() throws Exception {

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(AtittudeType.AGGRESSIVE);
        h1.getHeroType().setDefaultAction(createHealSpellWithBuff());

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(AtittudeType.AGGRESSIVE);
        h2.getHeroType().setDefaultAction(createHealSpellWithBuff());

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Battle b = new Battle(f1, f2);

        Assert.assertEquals("expected f2 win", f2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(1).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(1).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(1).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(2).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(2).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(2).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal with buff on turn 3", h2, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal with buff  on turn 3", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(3).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 3", new Integer(25), b.getBattleLog().get(3).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(3).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(3).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h1 heal with buff on turn 3", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal with buff  on turn 3", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(4).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 3", new Integer(25), b.getBattleLog().get(4).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(4).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(4).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal with buff on turn 4", h2, b.getBattleLog().get(7).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal with buff  on turn 4", h2, b.getBattleLog().get(7).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(7).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(7).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(7).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(7).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h1 heal with buff on turn 4", h1, b.getBattleLog().get(9).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal with buff  on turn 4", h1, b.getBattleLog().get(9).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(9).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(9).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(9).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(9).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 heal with buff on turn 5", h2, b.getBattleLog().get(13).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal with buff  on turn 4", h2, b.getBattleLog().get(13).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(13).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(13).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(13).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(13).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h2 buff done", ActionType.BUFF_DONE, b.getBattleLog().get(14).getBattleEvent().getType());

        Assert.assertEquals("h1 heal with buff on turn 5", h1, b.getBattleLog().get(17).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal with buff  on turn 4", h1, b.getBattleLog().get(17).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(17).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(17).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(17).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(17).getHeroesTarget().get(0).getHero().getCurrHp());

        Assert.assertEquals("h1 buff done", ActionType.BUFF_DONE, b.getBattleLog().get(18).getBattleEvent().getType());

    }

}
