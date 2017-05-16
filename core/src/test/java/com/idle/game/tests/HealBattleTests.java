package com.idle.game.tests;

import com.idle.game.core.ActionType;
import com.idle.game.core.AtittudeType;
import com.idle.game.core.Battle;
import com.idle.game.core.BattlePositionType;
import com.idle.game.core.BuffType;
import com.idle.game.core.Formation;
import com.idle.game.core.Hero;
import com.idle.game.core.HeroType;
import com.idle.game.core.Player;
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

        HeroType ht = createBasicHeroType(AtittudeType.AGGRESSIVE);
        ht.setDefaultAction(createBasicHealSpell());

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(ht);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Player p1 = createBasicPlayer("teste1");
        p1.setAttackFormation(f1);

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(ht);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Player p2 = createBasicPlayer("teste2");
        p2.setDefenseFormation(f2);

        Battle b = new Battle(p1, p2);

        Assert.assertEquals("expected p2 win", p2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(1).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(1).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(1).getHeroesTarget().get(0).getHero().getCurrentHp());

        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(2).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(2).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(2).getHeroesTarget().get(0).getHero().getCurrentHp());

    }

    @Test
    public void basicHealTestWithBuff() throws Exception {

        HeroType ht = createBasicHeroType(AtittudeType.AGGRESSIVE);
        ht.setDefaultAction(createHealSpellWithBuff());

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(ht);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Player p1 = createBasicPlayer("teste1");
        p1.setAttackFormation(f1);

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(ht);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Player p2 = createBasicPlayer("teste2");
        p2.setDefenseFormation(f2);

        Battle b = new Battle(p1, p2);

        Assert.assertEquals("expected p2 win", p2, b.doBattle());

        Assert.assertEquals("start of battle", ActionType.BATTLE_START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(1).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(1).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(1).getHeroesTarget().get(0).getHero().getCurrentHp());

        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("100 heal on turn 2", new Integer(100), b.getBattleLog().get(2).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(2).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(2).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h2 heal with buff on turn 3", h2, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal with buff  on turn 3", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(3).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 3", new Integer(25), b.getBattleLog().get(3).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(3).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(3).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h1 heal with buff on turn 3", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal with buff  on turn 3", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(4).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 3", new Integer(25), b.getBattleLog().get(4).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(4).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(4).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h2 heal with buff on turn 4", h2, b.getBattleLog().get(7).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal with buff  on turn 4", h2, b.getBattleLog().get(7).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(7).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(7).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(7).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(7).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h1 heal with buff on turn 4", h1, b.getBattleLog().get(9).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal with buff  on turn 4", h1, b.getBattleLog().get(9).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(9).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(9).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(9).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(9).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h2 heal with buff on turn 5", h2, b.getBattleLog().get(13).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal with buff  on turn 4", h2, b.getBattleLog().get(13).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(13).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(13).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(13).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(13).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h2 buff done", ActionType.BUFF_DONE, b.getBattleLog().get(14).getBattleEvent().getType());
        
        Assert.assertEquals("h1 heal with buff on turn 5", h1, b.getBattleLog().get(17).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal with buff  on turn 4", h1, b.getBattleLog().get(17).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("this buff need to be a buff", BuffType.BUFF, b.getBattleLog().get(17).getBattleEvent().getBuffType());
        Assert.assertEquals("25 heal on turn 4", new Integer(25), b.getBattleLog().get(17).getBattleEvent().getValue());
        Assert.assertEquals("special skill", ActionType.HEAL, b.getBattleLog().get(17).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(17).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        
        Assert.assertEquals("h1 buff done", ActionType.BUFF_DONE, b.getBattleLog().get(18).getBattleEvent().getType());

    }

}
