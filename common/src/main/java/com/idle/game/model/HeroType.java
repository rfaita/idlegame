package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.BattleHeroType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.hero.type.HeroTypeRole;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "heroType")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HeroType implements Serializable {

    @Id
    private String id;
    private HeroTypeQuality quality;
    private HeroTypeFaction faction;
    private HeroTypeRole role;
    private String name;
    private Action specialAction;
    private Action defaultAction;
    private DamageType damageType;
    private DistanceType distanceType;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    private Integer minBaseDmg = 0;
    private Integer minBaseArmor = 0;
    private Integer minBaseMagicResist = 0;
    private Integer minBaseSpeed = 0;
    private Integer minBaseLuck = 0;
    private Integer minBaseCritChance = 0;
    private Integer minBaseCritDamage = 0;
    private Integer minBaseDodgeChance = 0;
    private Integer minBaseHp = 0;

    private Integer minMaxLevelDmg = 0;
    private Integer minMaxLevelArmor = 0;
    private Integer minMaxLevelMagicResist = 0;
    private Integer minMaxLevelSpeed = 0;
    private Integer minMaxLevelLuck = 0;
    private Integer minMaxLevelCritChance = 0;
    private Integer minMaxLevelCritDamage = 0;
    private Integer minMaxLevelDodgeChance = 0;
    private Integer minMaxLevelHp = 0;

    private Integer maxBaseDmg = 0;
    private Integer maxBaseArmor = 0;
    private Integer maxBaseMagicResist = 0;
    private Integer maxBaseSpeed = 0;
    private Integer maxBaseLuck = 0;
    private Integer maxBaseCritChance = 0;
    private Integer maxBaseCritDamage = 0;
    private Integer maxBaseDodgeChance = 0;
    private Integer maxBaseHp = 0;

    private Integer maxMaxLevelDmg = 0;
    private Integer maxMaxLevelArmor = 0;
    private Integer maxMaxLevelMagicResist = 0;
    private Integer maxMaxLevelSpeed = 0;
    private Integer maxMaxLevelLuck = 0;
    private Integer maxMaxLevelCritChance = 0;
    private Integer maxMaxLevelCritDamage = 0;
    private Integer maxMaxLevelDodgeChance = 0;
    private Integer maxMaxLevelHp = 0;
    
    public HeroType() {
    }

    public BattleHeroType toBattleHeroType() {
        BattleHeroType ret = new BattleHeroType();
        ret.setDamageType(this.getDamageType());
        ret.setDefaultAction(this.getDefaultAction());
        ret.setDistanceType(this.getDistanceType());
        ret.setQuality(this.getQuality());
        ret.setRole(this.getRole());
        ret.setFaction(this.getFaction());
        ret.setMaxLevel(this.getMaxLevel());
        ret.setName(this.getName());
        ret.setPassives(this.getPassives());
        ret.setSpecialAction(this.getSpecialAction());

        return ret;
    }

    public HeroTypeQuality getQuality() {
        return quality;
    }

    public void setQuality(HeroTypeQuality quality) {
        this.quality = quality;
    }

    public HeroTypeFaction getFaction() {
        return faction;
    }

    public void setFaction(HeroTypeFaction faction) {
        this.faction = faction;
    }

    public HeroTypeRole getRole() {
        return role;
    }

    public void setRole(HeroTypeRole role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getSpecialAction() {
        return specialAction;
    }

    public void setSpecialAction(Action specialAction) {
        this.specialAction = specialAction;
    }

    public Action getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(Action defaultAction) {
        this.defaultAction = defaultAction;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public DistanceType getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }

    public List<Passive> getPassives() {
        return passives;
    }

    public void setPassives(List<Passive> passives) {
        this.passives = passives;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getMinBaseDmg() {
        return minBaseDmg;
    }

    public void setMinBaseDmg(Integer minBaseDmg) {
        this.minBaseDmg = minBaseDmg;
    }

    public Integer getMinBaseArmor() {
        return minBaseArmor;
    }

    public void setMinBaseArmor(Integer minBaseArmor) {
        this.minBaseArmor = minBaseArmor;
    }

    public Integer getMinBaseMagicResist() {
        return minBaseMagicResist;
    }

    public void setMinBaseMagicResist(Integer minBaseMagicResist) {
        this.minBaseMagicResist = minBaseMagicResist;
    }

    public Integer getMinBaseSpeed() {
        return minBaseSpeed;
    }

    public void setMinBaseSpeed(Integer minBaseSpeed) {
        this.minBaseSpeed = minBaseSpeed;
    }

    public Integer getMinBaseLuck() {
        return minBaseLuck;
    }

    public void setMinBaseLuck(Integer minBaseLuck) {
        this.minBaseLuck = minBaseLuck;
    }

    public Integer getMinBaseCritChance() {
        return minBaseCritChance;
    }

    public void setMinBaseCritChance(Integer minBaseCritChance) {
        this.minBaseCritChance = minBaseCritChance;
    }

    public Integer getMinBaseCritDamage() {
        return minBaseCritDamage;
    }

    public void setMinBaseCritDamage(Integer minBaseCritDamage) {
        this.minBaseCritDamage = minBaseCritDamage;
    }

    public Integer getMinBaseDodgeChance() {
        return minBaseDodgeChance;
    }

    public void setMinBaseDodgeChance(Integer minBaseDodgeChance) {
        this.minBaseDodgeChance = minBaseDodgeChance;
    }

    public Integer getMinBaseHp() {
        return minBaseHp;
    }

    public void setMinBaseHp(Integer minBaseHp) {
        this.minBaseHp = minBaseHp;
    }

    public Integer getMinMaxLevelDmg() {
        return minMaxLevelDmg;
    }

    public void setMinMaxLevelDmg(Integer minMaxLevelDmg) {
        this.minMaxLevelDmg = minMaxLevelDmg;
    }

    public Integer getMinMaxLevelArmor() {
        return minMaxLevelArmor;
    }

    public void setMinMaxLevelArmor(Integer minMaxLevelArmor) {
        this.minMaxLevelArmor = minMaxLevelArmor;
    }

    public Integer getMinMaxLevelMagicResist() {
        return minMaxLevelMagicResist;
    }

    public void setMinMaxLevelMagicResist(Integer minMaxLevelMagicResist) {
        this.minMaxLevelMagicResist = minMaxLevelMagicResist;
    }

    public Integer getMinMaxLevelSpeed() {
        return minMaxLevelSpeed;
    }

    public void setMinMaxLevelSpeed(Integer minMaxLevelSpeed) {
        this.minMaxLevelSpeed = minMaxLevelSpeed;
    }

    public Integer getMinMaxLevelLuck() {
        return minMaxLevelLuck;
    }

    public void setMinMaxLevelLuck(Integer minMaxLevelLuck) {
        this.minMaxLevelLuck = minMaxLevelLuck;
    }

    public Integer getMinMaxLevelCritChance() {
        return minMaxLevelCritChance;
    }

    public void setMinMaxLevelCritChance(Integer minMaxLevelCritChance) {
        this.minMaxLevelCritChance = minMaxLevelCritChance;
    }

    public Integer getMinMaxLevelCritDamage() {
        return minMaxLevelCritDamage;
    }

    public void setMinMaxLevelCritDamage(Integer minMaxLevelCritDamage) {
        this.minMaxLevelCritDamage = minMaxLevelCritDamage;
    }

    public Integer getMinMaxLevelDodgeChance() {
        return minMaxLevelDodgeChance;
    }

    public void setMinMaxLevelDodgeChance(Integer minMaxLevelDodgeChance) {
        this.minMaxLevelDodgeChance = minMaxLevelDodgeChance;
    }

    public Integer getMinMaxLevelHp() {
        return minMaxLevelHp;
    }

    public void setMinMaxLevelHp(Integer minMaxLevelHp) {
        this.minMaxLevelHp = minMaxLevelHp;
    }

    public Integer getMaxBaseDmg() {
        return maxBaseDmg;
    }

    public void setMaxBaseDmg(Integer maxBaseDmg) {
        this.maxBaseDmg = maxBaseDmg;
    }

    public Integer getMaxBaseArmor() {
        return maxBaseArmor;
    }

    public void setMaxBaseArmor(Integer maxBaseArmor) {
        this.maxBaseArmor = maxBaseArmor;
    }

    public Integer getMaxBaseMagicResist() {
        return maxBaseMagicResist;
    }

    public void setMaxBaseMagicResist(Integer maxBaseMagicResist) {
        this.maxBaseMagicResist = maxBaseMagicResist;
    }

    public Integer getMaxBaseSpeed() {
        return maxBaseSpeed;
    }

    public void setMaxBaseSpeed(Integer maxBaseSpeed) {
        this.maxBaseSpeed = maxBaseSpeed;
    }

    public Integer getMaxBaseLuck() {
        return maxBaseLuck;
    }

    public void setMaxBaseLuck(Integer maxBaseLuck) {
        this.maxBaseLuck = maxBaseLuck;
    }

    public Integer getMaxBaseCritChance() {
        return maxBaseCritChance;
    }

    public void setMaxBaseCritChance(Integer maxBaseCritChance) {
        this.maxBaseCritChance = maxBaseCritChance;
    }

    public Integer getMaxBaseCritDamage() {
        return maxBaseCritDamage;
    }

    public void setMaxBaseCritDamage(Integer maxBaseCritDamage) {
        this.maxBaseCritDamage = maxBaseCritDamage;
    }

    public Integer getMaxBaseDodgeChance() {
        return maxBaseDodgeChance;
    }

    public void setMaxBaseDodgeChance(Integer maxBaseDodgeChance) {
        this.maxBaseDodgeChance = maxBaseDodgeChance;
    }

    public Integer getMaxBaseHp() {
        return maxBaseHp;
    }

    public void setMaxBaseHp(Integer maxBaseHp) {
        this.maxBaseHp = maxBaseHp;
    }

    public Integer getMaxMaxLevelDmg() {
        return maxMaxLevelDmg;
    }

    public void setMaxMaxLevelDmg(Integer maxMaxLevelDmg) {
        this.maxMaxLevelDmg = maxMaxLevelDmg;
    }

    public Integer getMaxMaxLevelArmor() {
        return maxMaxLevelArmor;
    }

    public void setMaxMaxLevelArmor(Integer maxMaxLevelArmor) {
        this.maxMaxLevelArmor = maxMaxLevelArmor;
    }

    public Integer getMaxMaxLevelMagicResist() {
        return maxMaxLevelMagicResist;
    }

    public void setMaxMaxLevelMagicResist(Integer maxMaxLevelMagicResist) {
        this.maxMaxLevelMagicResist = maxMaxLevelMagicResist;
    }

    public Integer getMaxMaxLevelSpeed() {
        return maxMaxLevelSpeed;
    }

    public void setMaxMaxLevelSpeed(Integer maxMaxLevelSpeed) {
        this.maxMaxLevelSpeed = maxMaxLevelSpeed;
    }

    public Integer getMaxMaxLevelLuck() {
        return maxMaxLevelLuck;
    }

    public void setMaxMaxLevelLuck(Integer maxMaxLevelLuck) {
        this.maxMaxLevelLuck = maxMaxLevelLuck;
    }

    public Integer getMaxMaxLevelCritChance() {
        return maxMaxLevelCritChance;
    }

    public void setMaxMaxLevelCritChance(Integer maxMaxLevelCritChance) {
        this.maxMaxLevelCritChance = maxMaxLevelCritChance;
    }

    public Integer getMaxMaxLevelCritDamage() {
        return maxMaxLevelCritDamage;
    }

    public void setMaxMaxLevelCritDamage(Integer maxMaxLevelCritDamage) {
        this.maxMaxLevelCritDamage = maxMaxLevelCritDamage;
    }

    public Integer getMaxMaxLevelDodgeChance() {
        return maxMaxLevelDodgeChance;
    }

    public void setMaxMaxLevelDodgeChance(Integer maxMaxLevelDodgeChance) {
        this.maxMaxLevelDodgeChance = maxMaxLevelDodgeChance;
    }

    public Integer getMaxMaxLevelHp() {
        return maxMaxLevelHp;
    }

    public void setMaxMaxLevelHp(Integer maxMaxLevelHp) {
        this.maxMaxLevelHp = maxMaxLevelHp;
    }

    @Override
    public String toString() {
        return "HT{name=" + name + '}';
    }

}
