import { Defense } from './defense';
import { BattleHeroType } from './battleHeroType';
export class BattleHero {
    public id: String;
    public heroType: BattleHeroType;

    public level: Number;

    public dmg: Number;
    public ap: Number;
    public defenses: Defense[];
    public speed: Number;
    public critChance: Number;
    public critDamage: Number;
    public dodgeChance: Number;
    public hp: Number;

    public currDmg: Number;
    public currAp: Number;
    public currDefenses: Defense[];
    public currSpeed: Number;
    public currCritChance: Number;
    public currCritDamage: Number;
    public currDodgeChance: Number;
    public currHp: Number;

    public canDoAction: Boolean;
    public canDoSpecialAction: Boolean;

    /*public currBuffs:Buff[];

    public chest:Item;
    public weapon:Item;
    public boot:Item;
    public ring:Item;
    public ammulet:Item;
    public jewel:Item;*/
}