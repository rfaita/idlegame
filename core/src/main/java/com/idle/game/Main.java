package com.idle.game;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.type.ActionType;
import com.idle.game.core.type.AtittudeType;
import com.idle.game.core.Battle;
import com.idle.game.core.type.BattlePositionType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.Formation;
import com.idle.game.core.Hero;
import com.idle.game.core.type.HeroType;
import com.idle.game.core.PositionedHero;

/**
 *
 * @author rafael
 */
public class Main {

    public static void main(String args[]) throws Exception {

        HeroType type = new HeroType();
        type.setAtittudeType(AtittudeType.AGGRESSIVE);
        type.setDefaultAction(new Action(new ActionEffect(ActionType.DMG, 50, DamageType.MAGIC), null));

        HeroType type2 = new HeroType();
        type2.setAtittudeType(AtittudeType.AGGRESSIVE);
        type2.setDefaultAction(new Action(new ActionEffect(ActionType.DMG, 50, DamageType.PHYSICAL), null));

        Hero h1 = new Hero(type, 1, 100, 100, 100, 1, 10, 10, 50, 10, 11, 400);
        Hero h2 = new Hero(type, 2, 100, 100, 10, 2, 10, 10, 50, 10, 11, 400);
        Hero h3 = new Hero(type, 3, 100, 20, 10, 102, 10, 10, 50, 10, 11, 400);
        Hero h4 = new Hero(type2, 4, 100, 50, 140, 3, 10, 10, 50, 10, 11, 400);
        Hero h5 = new Hero(type2, 5, 100, 200, 150, 40, 10, 50, 50, 10, 11, 400);
        Hero h6 = new Hero(type2, 6, 100, 10, 160, 11, 10, 10, 50, 10, 11, 400);

        Formation fAttack = new Formation();
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_TOP, h1));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_BOTTOM, h2));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_MIDDLE, h3));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.BACK_TOP, h4));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.BACK_MIDDLE, h5));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.BACK_BOTTOM, h6));

        Hero h7 = new Hero(type, 7, 100, 120, 101, 4, 10, 10, 50, 10, 11, 400);
        Hero h8 = new Hero(type, 8, 100, 134, 130, 5, 10, 10, 50, 10, 11, 400);
        Hero h9 = new Hero(type, 9, 100, 110, -50, 8, 10, 10, 50, 10, 11, 400);
        Hero h10 = new Hero(type2, 10, 100, 150, -10, 7, 10, 10, 50, 10, 11, 400);
        Hero h11 = new Hero(type2, 11, 100, 160, 134, 6, 10, 10, 50, 10, 11, 400);
        Hero h12 = new Hero(type, 12, 100, 112, 500, 9, 10, 10, 50, 10, 11, 400);

        Formation fDef = new Formation();
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_TOP, h7));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_BOTTOM, h8));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_MIDDLE, h9));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.BACK_TOP, h10));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.BACK_MIDDLE, h11));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.BACK_BOTTOM, h12));

        Battle b = new Battle(fAttack, fDef);

        b.doBattle();

    }

}
