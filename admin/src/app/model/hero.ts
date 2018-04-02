import { Defense } from './defense';
export class Hero {
    public id: String;
    public heroTypeId: String;

    public heroTypeName: String;

    public userId: String;
    public level: Number;
    public quality: String;

    public baseDmg: Number;
    public baseAp: Number;
    public baseDefenses: Defense[];
    public baseSpeed: Number;
    public baseCritChance: Number;
    public baseCritDamage: Number;
    public baseDodgeChance: Number;
    public baseHp: Number;

    public maxLevelDmg: Number;
    public maxLevelAp: Number;
    public maxLevelDefenses: Defense[];
    public maxLevelSpeed: Number;
    public maxLevelCritChance: Number;
    public maxLevelCritDamage: Number;
    public maxLevelDodgeChance: Number;
    public maxLevelHp: Number;
}