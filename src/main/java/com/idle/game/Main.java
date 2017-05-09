package com.idle.game;

import com.idle.game.core.AtittudeType;
import com.idle.game.core.Battle;
import com.idle.game.core.BattlePositionType;
import com.idle.game.core.DamageType;
import com.idle.game.core.Formation;
import com.idle.game.core.Hero;
import com.idle.game.core.HeroType;
import com.idle.game.core.Player;
import com.idle.game.core.PositionedHero;
import com.idle.game.core.Skill;
import com.idle.game.core.SkillEffect;
import com.idle.game.core.SkillEffectType;
import com.idle.game.core.TargetType;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class Main {

    public static void main(String args[]) throws Exception {

        Skill healSkill = new Skill();
        healSkill.setMainEffect(new SkillEffect(SkillEffectType.DMG, TargetType.BACK_LINE, 120, DamageType.MAGIC));
        healSkill.addSecundaryEffects(new SkillEffect(SkillEffectType.HEAL, TargetType.FRONT_LINE, 80, 100, 0, DamageType.MAGIC));

        HeroType type = new HeroType();
        type.setAtittudeType(AtittudeType.HEALER);
        type.setSpecialSkill(healSkill);

        Skill dmgSkill = new Skill();
        dmgSkill.setMainEffect(new SkillEffect(SkillEffectType.DMG, TargetType.MORE_LIFE, 120, DamageType.PHYSICAL));
        dmgSkill.addSecundaryEffects(new SkillEffect(SkillEffectType.DMG, TargetType.LAST_ONE, 80, 100, 0, DamageType.PHYSICAL));

        HeroType type2 = new HeroType();
        type2.setAtittudeType(AtittudeType.HUNTER);
        type2.setSpecialSkill(dmgSkill);

        Hero h1 = new Hero(UUID.randomUUID(), type, 1, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 100, 100, 1, 10, 10, 50, 10, 11, 400);
        Hero h2 = new Hero(UUID.randomUUID(), type, 2, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 100, 10, 2, 10, 10, 50, 10, 11, 400);
        Hero h3 = new Hero(UUID.randomUUID(), type, 3, 100, DamageType.MAGIC, 100, DamageType.PHYSICAL, 20, 10, 102, 10, 10, 50, 10, 11, 400);
        Hero h4 = new Hero(UUID.randomUUID(), type2, 4, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 50, 140, 3, 10, 10, 50, 10, 11, 400);
        Hero h5 = new Hero(UUID.randomUUID(), type2, 5, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 200, 150, 40, 10, 50, 50, 10, 11, 400);
        Hero h6 = new Hero(UUID.randomUUID(), type2, 6, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 10, 160, 11, 10, 10, 50, 10, 11, 400);

        Formation fAttack = new Formation();
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_TOP, h1));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_BOTTOM, h2));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.BACK_0, h3));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.BACK_1, h4));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.BACK_2, h5));
        fAttack.addPositionedHero(new PositionedHero(BattlePositionType.BACK_3, h6));

        Hero h7 = new Hero(UUID.randomUUID(), type, 7, 100, DamageType.MAGIC, 100, DamageType.PHYSICAL, 120, 101, 4, 10, 10, 50, 10, 11, 400);
        Hero h8 = new Hero(UUID.randomUUID(), type, 8, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 134, 130, 5, 10, 10, 50, 10, 11, 400);
        Hero h9 = new Hero(UUID.randomUUID(), type, 9, 100, DamageType.MAGIC, 100, DamageType.PHYSICAL, 110, -50, 8, 10, 10, 50, 10, 11, 400);
        Hero h10 = new Hero(UUID.randomUUID(), type2, 10, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 150, -10, 7, 10, 10, 50, 10, 11, 400);
        Hero h11 = new Hero(UUID.randomUUID(), type2, 11, 100, DamageType.MAGIC, 100, DamageType.PHYSICAL, 160, 134, 6, 10, 10, 50, 10, 11, 400);
        Hero h12 = new Hero(UUID.randomUUID(), type, 12, 100, DamageType.PHYSICAL, 100, DamageType.PHYSICAL, 112, 500, 9, 10, 10, 50, 10, 11, 400);

        Formation fDef = new Formation();
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_TOP, h7));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.FRONT_BOTTOM, h8));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.BACK_0, h9));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.BACK_1, h10));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.BACK_2, h11));
        fDef.addPositionedHero(new PositionedHero(BattlePositionType.BACK_3, h12));

        Player pAttack = new Player(UUID.randomUUID(), "test1");
        pAttack.setAttackFormation(fAttack);

        Player pDefense = new Player(UUID.randomUUID(), "test2");
        pDefense.setDefenseFormation(fDef);

        Battle b = new Battle(pAttack, pDefense);

        b.doBattle();

    }

}
