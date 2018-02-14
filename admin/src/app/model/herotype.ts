export class HeroType {

    public id: String;
    public quality: String;
    public faction: String;
    public role: String;
    public name: String;
    //public Action specialAction;
    //public Action defaultAction;
    public damageType: String;
    public distanceType: String;
    //public List<Passive> passives;

    public maxLevel: Number = 0;

    public minBaseDmg: Number = 0;
    public minBaseArmor: Number = 0;
    public minBaseMagicResist: Number = 0;
    public minBaseSpeed: Number = 0;
    public minBaseLuck: Number = 0;
    public minBaseCritChance: Number = 0;
    public minBaseCritDamage: Number = 0;
    public minBaseDodgeChance: Number = 0;
    public minBaseHp: Number = 0;

    public minMaxLevelDmg: Number = 0;
    public minMaxLevelArmor: Number = 0;
    public minMaxLevelMagicResist: Number = 0;
    public minMaxLevelSpeed: Number = 0;
    public minMaxLevelLuck: Number = 0;
    public minMaxLevelCritChance: Number = 0;
    public minMaxLevelCritDamage: Number = 0;
    public minMaxLevelDodgeChance: Number = 0;
    public minMaxLevelHp: Number = 0;

    public maxBaseDmg: Number = 0;
    public maxBaseArmor: Number = 0;
    public maxBaseMagicResist: Number = 0;
    public maxBaseSpeed: Number = 0;
    public maxBaseLuck: Number = 0;
    public maxBaseCritChance: Number = 0;
    public maxBaseCritDamage: Number = 0;
    public maxBaseDodgeChance: Number = 0;
    public maxBaseHp: Number = 0;

    public maxMaxLevelDmg: Number = 0;
    public maxMaxLevelArmor: Number = 0;
    public maxMaxLevelMagicResist: Number = 0;
    public maxMaxLevelSpeed: Number = 0;
    public maxMaxLevelLuck: Number = 0;
    public maxMaxLevelCritChance: Number = 0;
    public maxMaxLevelCritDamage: Number = 0;
    public maxMaxLevelDodgeChance: Number = 0;
    public maxMaxLevelHp: Number = 0;

    constructor(id: string) {
        this.id = id;
    }
}