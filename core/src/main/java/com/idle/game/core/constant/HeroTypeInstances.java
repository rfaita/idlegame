package com.idle.game.core.constant;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.ActionType;
import com.idle.game.core.type.AtittudeType;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.type.HeroType;
import com.idle.game.core.type.PassiveType;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class HeroTypeInstances {

    public static final HeroType BERZERKER;
    public static final HeroType CHAMPION;
    public static final HeroType ARCHER;
    public static final HeroType HIGHLANDER;
    public static final HeroType GHOST;

    static {
        BERZERKER = new HeroType(AtittudeType.AGGRESSIVE);
        BERZERKER.setUUID(UUID.fromString("91b9cddc-3fbe-11e7-a919-92ebcb67fe33"));
        BERZERKER.setName("BERZERKER");
        BERZERKER.setBaseArmor(1);
        BERZERKER.setBaseBlockChance(0);
        BERZERKER.setBaseCritChance(15);
        BERZERKER.setBaseCritDamage(100);
        BERZERKER.setBaseDmg(20);
        BERZERKER.setBaseDodgeChance(10);
        BERZERKER.setBaseHp(90);
        BERZERKER.setBaseLuck(0);
        BERZERKER.setBaseMagicResist(1);
        BERZERKER.setBaseSpeed(15);

        BERZERKER.setDamageType(DamageType.PHYSICAL);
        BERZERKER.setDistanceType(DistanceType.MELEE);

        BERZERKER.setMaxLevelUpIncArmor(19);
        BERZERKER.setMaxLevelUpIncBlockChance(0);
        BERZERKER.setMaxLevelUpIncCritChance(5);
        BERZERKER.setMaxLevelUpIncCritDamage(0);
        BERZERKER.setMaxLevelUpIncDmg(180);
        BERZERKER.setMaxLevelUpIncDodgeChance(5);
        BERZERKER.setMaxLevelUpIncHp(710);
        BERZERKER.setMaxLevelUpIncLuck(0);
        BERZERKER.setMaxLevelUpIncMagicResist(9);
        BERZERKER.setMaxLevelUpIncSpeed(10);

        BERZERKER.setDefaultAction(new Action(new ActionEffect(ActionType.DMG, 100)));
        BERZERKER.addPassive(new Passive(PassiveType.TRADE_ATTRIBUTE,
                AttributeType.HP, AttributeType.DMG, 100));

        CHAMPION = new HeroType(AtittudeType.AGGRESSIVE);
        CHAMPION.setUUID(UUID.fromString("91b9d066-3fbe-11e7-a919-92ebcb67fe33"));
        CHAMPION.setName("CHAMPION");
        CHAMPION.setBaseArmor(40);
        CHAMPION.setBaseBlockChance(0);
        CHAMPION.setBaseCritChance(15);
        CHAMPION.setBaseCritDamage(100);
        CHAMPION.setBaseDmg(22);
        CHAMPION.setBaseDodgeChance(5);
        CHAMPION.setBaseHp(150);
        CHAMPION.setBaseLuck(0);
        CHAMPION.setBaseMagicResist(15);
        CHAMPION.setBaseSpeed(12);

        CHAMPION.setDamageType(DamageType.PHYSICAL);
        CHAMPION.setDistanceType(DistanceType.MELEE);

        CHAMPION.setMaxLevelUpIncArmor(19);
        CHAMPION.setMaxLevelUpIncBlockChance(0);
        CHAMPION.setMaxLevelUpIncCritChance(5);
        CHAMPION.setMaxLevelUpIncCritDamage(0);
        CHAMPION.setMaxLevelUpIncDmg(180);
        CHAMPION.setMaxLevelUpIncDodgeChance(5);
        CHAMPION.setMaxLevelUpIncHp(710);
        CHAMPION.setMaxLevelUpIncLuck(0);
        CHAMPION.setMaxLevelUpIncMagicResist(9);
        CHAMPION.setMaxLevelUpIncSpeed(10);
        
        CHAMPION.setDefaultAction(new Action(new ActionEffect(ActionType.DMG, 100)));

        ARCHER = new HeroType(AtittudeType.AGGRESSIVE);
        ARCHER.setUUID(UUID.fromString("91b9d174-3fbe-11e7-a919-92ebcb67fe33"));
        ARCHER.setName("ARCHER");
        ARCHER.setBaseArmor(1);
        ARCHER.setBaseBlockChance(0);
        ARCHER.setBaseCritChance(18);
        ARCHER.setBaseCritDamage(110);
        ARCHER.setBaseDmg(25);
        ARCHER.setBaseDodgeChance(12);
        ARCHER.setBaseHp(65);
        ARCHER.setBaseLuck(0);
        ARCHER.setBaseMagicResist(1);
        ARCHER.setBaseSpeed(17);

        ARCHER.setDamageType(DamageType.PHYSICAL);
        ARCHER.setDistanceType(DistanceType.RANGED);

        ARCHER.setMaxLevelUpIncArmor(19);
        ARCHER.setMaxLevelUpIncBlockChance(0);
        ARCHER.setMaxLevelUpIncCritChance(5);
        ARCHER.setMaxLevelUpIncCritDamage(0);
        ARCHER.setMaxLevelUpIncDmg(180);
        ARCHER.setMaxLevelUpIncDodgeChance(5);
        ARCHER.setMaxLevelUpIncHp(710);
        ARCHER.setMaxLevelUpIncLuck(0);
        ARCHER.setMaxLevelUpIncMagicResist(9);
        ARCHER.setMaxLevelUpIncSpeed(10);
        
        ARCHER.setDefaultAction(new Action(new ActionEffect(ActionType.DMG, 100)));

        HIGHLANDER = new HeroType(AtittudeType.AGGRESSIVE);
        HIGHLANDER.setUUID(UUID.fromString("91b9d386-3fbe-11e7-a919-92ebcb67fe33"));
        HIGHLANDER.setName("HIGHLANDER");
        HIGHLANDER.setBaseArmor(60);
        HIGHLANDER.setBaseBlockChance(0);
        HIGHLANDER.setBaseCritChance(10);
        HIGHLANDER.setBaseCritDamage(100);
        HIGHLANDER.setBaseDmg(15);
        HIGHLANDER.setBaseDodgeChance(10);
        HIGHLANDER.setBaseHp(195);
        HIGHLANDER.setBaseLuck(0);
        HIGHLANDER.setBaseMagicResist(40);
        HIGHLANDER.setBaseSpeed(13);

        HIGHLANDER.setDamageType(DamageType.PHYSICAL);
        HIGHLANDER.setDistanceType(DistanceType.MELEE);

        HIGHLANDER.setMaxLevelUpIncArmor(19);
        HIGHLANDER.setMaxLevelUpIncBlockChance(0);
        HIGHLANDER.setMaxLevelUpIncCritChance(5);
        HIGHLANDER.setMaxLevelUpIncCritDamage(0);
        HIGHLANDER.setMaxLevelUpIncDmg(180);
        HIGHLANDER.setMaxLevelUpIncDodgeChance(5);
        HIGHLANDER.setMaxLevelUpIncHp(710);
        HIGHLANDER.setMaxLevelUpIncLuck(0);
        HIGHLANDER.setMaxLevelUpIncMagicResist(9);
        HIGHLANDER.setMaxLevelUpIncSpeed(10);

        HIGHLANDER.setDefaultAction(new Action(new ActionEffect(ActionType.DMG, 100)));
        HIGHLANDER.addPassive(new Passive(PassiveType.TRADE_ATTRIBUTE,
                AttributeType.HP, AttributeType.DEFENSE, 100));

        GHOST = new HeroType(AtittudeType.AGGRESSIVE);
        GHOST.setUUID(UUID.fromString("91b9d516-3fbe-11e7-a919-92ebcb67fe33"));
        GHOST.setName("GHOST");
        GHOST.setBaseArmor(60);
        GHOST.setBaseBlockChance(0);
        GHOST.setBaseCritChance(10);
        GHOST.setBaseCritDamage(100);
        GHOST.setBaseDmg(20);
        GHOST.setBaseDodgeChance(8);
        GHOST.setBaseHp(95);
        GHOST.setBaseLuck(0);
        GHOST.setBaseMagicResist(1);
        GHOST.setBaseSpeed(13);

        GHOST.setDamageType(DamageType.MAGIC);
        GHOST.setDistanceType(DistanceType.MELEE);

        GHOST.setMaxLevelUpIncArmor(19);
        GHOST.setMaxLevelUpIncBlockChance(0);
        GHOST.setMaxLevelUpIncCritChance(5);
        GHOST.setMaxLevelUpIncCritDamage(0);
        GHOST.setMaxLevelUpIncDmg(180);
        GHOST.setMaxLevelUpIncDodgeChance(5);
        GHOST.setMaxLevelUpIncHp(710);
        GHOST.setMaxLevelUpIncLuck(0);
        GHOST.setMaxLevelUpIncMagicResist(9);
        GHOST.setMaxLevelUpIncSpeed(10);

        GHOST.setDefaultAction(new Action(new ActionEffect(ActionType.DMG, 100)));
        GHOST.addPassive(new Passive(PassiveType.UNIQUE));

    }

}
