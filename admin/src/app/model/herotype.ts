import { Action } from './action';
import { Defense } from './defense';
export class HeroType {

    public id: String;
    public quality: String;
    public faction: String;
    public role: String;
    public name: String;
    public specialActions: Action[];
    public defaultActions: Action[];
    public distanceType: String;
    //public List<Passive> passives;

    public maxLevel: Number = 0;

    public minBaseDmg: Number = 0;
    public minBaseAp: Number = 0;
    public minBaseDefenses: Defense[];
    public minBaseSpeed: Number = 0;
    public minBaseCritChance: Number = 0;
    public minBaseCritDamage: Number = 0;
    public minBaseDodgeChance: Number = 0;
    public minBaseHp: Number = 0;

    public minMaxLevelDmg: Number = 0;
    public minMaxLevelAp: Number = 0;
    public minMaxLevelDefenses: Defense[];
    public minMaxLevelSpeed: Number = 0;
    public minMaxLevelCritChance: Number = 0;
    public minMaxLevelCritDamage: Number = 0;
    public minMaxLevelDodgeChance: Number = 0;
    public minMaxLevelHp: Number = 0;

    public maxBaseDmg: Number = 0;
    public maxBaseAp: Number = 0;
    public maxBaseDefenses: Defense[];
    public maxBaseSpeed: Number = 0;
    public maxBaseCritChance: Number = 0;
    public maxBaseCritDamage: Number = 0;
    public maxBaseDodgeChance: Number = 0;
    public maxBaseHp: Number = 0;

    public maxMaxLevelDmg: Number = 0;
    public maxMaxLevelAp: Number = 0;
    public maxMaxLevelDefenses: Defense[];
    public maxMaxLevelSpeed: Number = 0;
    public maxMaxLevelCritChance: Number = 0;
    public maxMaxLevelCritDamage: Number = 0;
    public maxMaxLevelDodgeChance: Number = 0;
    public maxMaxLevelHp: Number = 0;


}