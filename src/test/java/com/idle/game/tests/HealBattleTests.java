package com.idle.game.tests;

import com.idle.game.core.AtittudeType;
import com.idle.game.core.Battle;
import com.idle.game.core.BattleEventType;
import com.idle.game.core.BattlePositionType;
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
        ht.setSpecialSkill(createBasicHealSpell());

        Hero h1 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(ht);

        Formation f1 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h1));

        Player p1 = createBasicPlayer("teste1");
        p1.setAttackFormation(f1);

        Hero h2 = createBasicMeleePhysicalNoCritNoDodgeNoBlock200Hp10000SpeedHero(ht);

        Formation f2 = createBasicFormation(createBasicPositionedHero(BattlePositionType.FRONT_TOP, h2));

        Player p2 = createBasicPlayer("teste2");
        p2.setDefenseFormation(f2);

        Battle b = new Battle(p1, p2);

        Assert.assertEquals("expected p1 win", p1, b.doBattle());

        Assert.assertEquals("start of battle", BattleEventType.START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on turn 2", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.SPECIAL, b.getBattleLog().get(3).getBattleEvent().getType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("50 heal on turn 2", new Integer(50), b.getBattleLog().get(4).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.HEAL, b.getBattleLog().get(4).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(4).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on turn 2", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.SPECIAL, b.getBattleLog().get(5).getBattleEvent().getType());
        
        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(6).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(6).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("50 heal on turn 2", new Integer(50), b.getBattleLog().get(6).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.HEAL, b.getBattleLog().get(6).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(150), b.getBattleLog().get(6).getHeroesTarget().get(0).getHero().getCurrentHp());

    }
    
    @Test
    public void basicHealTestWithBuff() throws Exception {

        HeroType ht = createBasicHeroType(AtittudeType.AGGRESSIVE);
        ht.setSpecialSkill(createHealSpellWithBuff());

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

        Assert.assertEquals("start of battle", BattleEventType.START, b.getBattleLog().get(0).getBattleEvent().getType());

        Assert.assertEquals("h1 attack first on first turn", h1, b.getBattleLog().get(1).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on first turn", h2, b.getBattleLog().get(1).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(1).getBattleEvent().getValue());

        Assert.assertEquals("h2 attack second on first turn", h2, b.getBattleLog().get(2).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target second on first turn", h1, b.getBattleLog().get(2).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on first turn", new Integer(-50), b.getBattleLog().get(2).getBattleEvent().getValue());

        Assert.assertEquals("h1 attack first on turn 2", h1, b.getBattleLog().get(3).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target first on turn 2", h2, b.getBattleLog().get(3).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on turn 2", new Integer(-50), b.getBattleLog().get(3).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.SPECIAL, b.getBattleLog().get(3).getBattleEvent().getType());

        Assert.assertEquals("h1 heal on turn 2", h1, b.getBattleLog().get(4).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target himself to heal on turn 2", h1, b.getBattleLog().get(4).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("50 heal on turn 2", new Integer(50), b.getBattleLog().get(4).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.HEAL, b.getBattleLog().get(4).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(200), b.getBattleLog().get(4).getHeroesTarget().get(0).getHero().getCurrentHp());
        
        Assert.assertEquals("h2 attack first on turn 2", h2, b.getBattleLog().get(5).getHeroOrigin().getHero());
        Assert.assertEquals("h1 target first on turn 2", h1, b.getBattleLog().get(5).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("-50 dmg on turn 2", new Integer(-50), b.getBattleLog().get(5).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.SPECIAL, b.getBattleLog().get(5).getBattleEvent().getType());
        
        Assert.assertEquals("h2 heal on turn 2", h2, b.getBattleLog().get(6).getHeroOrigin().getHero());
        Assert.assertEquals("h2 target himself to heal on turn 2", h2, b.getBattleLog().get(6).getHeroesTarget().get(0).getHero());
        Assert.assertEquals("50 heal on turn 2", new Integer(50), b.getBattleLog().get(6).getBattleEvent().getValue());
        Assert.assertEquals("special skill", BattleEventType.HEAL, b.getBattleLog().get(6).getBattleEvent().getType());
        Assert.assertEquals("life expected 200", new Integer(150), b.getBattleLog().get(6).getHeroesTarget().get(0).getHero().getCurrentHp());

    }

}
