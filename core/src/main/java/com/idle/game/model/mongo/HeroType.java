package com.idle.game.model.mongo;

import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.type.HeroTypeQuality;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "heroType")
public class HeroType implements Serializable {

    @Id
    private String id;
    private HeroTypeQuality heroTypeQuality;
    private String name;
    private Action specialAction;
    private Action defaultAction;
    private DamageType damageType;
    private DistanceType distanceType;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    private Integer minBaseDmg;
    private Integer minBaseArmor;
    private Integer minBaseMagicResist;
    private Integer minBaseSpeed;
    private Integer minBaseLuck;
    private Integer minBaseCritChance;
    private Integer minBaseCritDamage;
    private Integer minBaseDodgeChance;
    private Integer minBaseHp;

    private Integer minMaxLevelUpIncDmg;
    private Integer minMaxLevelUpIncArmor;
    private Integer minMaxLevelUpIncMagicResist;
    private Integer minMaxLevelUpIncSpeed;
    private Integer minMaxLevelUpIncLuck;
    private Integer minMaxLevelUpIncCritChance;
    private Integer minMaxLevelUpIncCritDamage;
    private Integer minMaxLevelUpIncDodgeChance;
    private Integer minMaxLevelUpIncHp;

    private Integer maxBaseDmg;
    private Integer maxBaseArmor;
    private Integer maxBaseMagicResist;
    private Integer maxBaseSpeed;
    private Integer maxBaseLuck;
    private Integer maxBaseCritChance;
    private Integer maxBaseCritDamage;
    private Integer maxBaseDodgeChance;
    private Integer maxBaseHp;

    private Integer maxMaxLevelUpIncDmg;
    private Integer maxMaxLevelUpIncArmor;
    private Integer maxMaxLevelUpIncMagicResist;
    private Integer maxMaxLevelUpIncSpeed;
    private Integer maxMaxLevelUpIncLuck;
    private Integer maxMaxLevelUpIncCritChance;
    private Integer maxMaxLevelUpIncCritDamage;
    private Integer maxMaxLevelUpIncDodgeChance;
    private Integer maxMaxLevelUpIncHp;

    public HeroType() {
    }

    public HeroTypeQuality getHeroTypeQuality() {
        return heroTypeQuality;
    }

    public void setHeroTypeQuality(HeroTypeQuality heroTypeQuality) {
        this.heroTypeQuality = heroTypeQuality;
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

    public Integer getMinMaxLevelUpIncDmg() {
        return minMaxLevelUpIncDmg;
    }

    public void setMinMaxLevelUpIncDmg(Integer minMaxLevelUpIncDmg) {
        this.minMaxLevelUpIncDmg = minMaxLevelUpIncDmg;
    }

    public Integer getMinMaxLevelUpIncArmor() {
        return minMaxLevelUpIncArmor;
    }

    public void setMinMaxLevelUpIncArmor(Integer minMaxLevelUpIncArmor) {
        this.minMaxLevelUpIncArmor = minMaxLevelUpIncArmor;
    }

    public Integer getMinMaxLevelUpIncMagicResist() {
        return minMaxLevelUpIncMagicResist;
    }

    public void setMinMaxLevelUpIncMagicResist(Integer minMaxLevelUpIncMagicResist) {
        this.minMaxLevelUpIncMagicResist = minMaxLevelUpIncMagicResist;
    }

    public Integer getMinMaxLevelUpIncSpeed() {
        return minMaxLevelUpIncSpeed;
    }

    public void setMinMaxLevelUpIncSpeed(Integer minMaxLevelUpIncSpeed) {
        this.minMaxLevelUpIncSpeed = minMaxLevelUpIncSpeed;
    }

    public Integer getMinMaxLevelUpIncLuck() {
        return minMaxLevelUpIncLuck;
    }

    public void setMinMaxLevelUpIncLuck(Integer minMaxLevelUpIncLuck) {
        this.minMaxLevelUpIncLuck = minMaxLevelUpIncLuck;
    }

    public Integer getMinMaxLevelUpIncCritChance() {
        return minMaxLevelUpIncCritChance;
    }

    public void setMinMaxLevelUpIncCritChance(Integer minMaxLevelUpIncCritChance) {
        this.minMaxLevelUpIncCritChance = minMaxLevelUpIncCritChance;
    }

    public Integer getMinMaxLevelUpIncCritDamage() {
        return minMaxLevelUpIncCritDamage;
    }

    public void setMinMaxLevelUpIncCritDamage(Integer minMaxLevelUpIncCritDamage) {
        this.minMaxLevelUpIncCritDamage = minMaxLevelUpIncCritDamage;
    }

    public Integer getMinMaxLevelUpIncDodgeChance() {
        return minMaxLevelUpIncDodgeChance;
    }

    public void setMinMaxLevelUpIncDodgeChance(Integer minMaxLevelUpIncDodgeChance) {
        this.minMaxLevelUpIncDodgeChance = minMaxLevelUpIncDodgeChance;
    }

    public Integer getMinMaxLevelUpIncHp() {
        return minMaxLevelUpIncHp;
    }

    public void setMinMaxLevelUpIncHp(Integer minMaxLevelUpIncHp) {
        this.minMaxLevelUpIncHp = minMaxLevelUpIncHp;
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

    public Integer getMaxMaxLevelUpIncDmg() {
        return maxMaxLevelUpIncDmg;
    }

    public void setMaxMaxLevelUpIncDmg(Integer maxMaxLevelUpIncDmg) {
        this.maxMaxLevelUpIncDmg = maxMaxLevelUpIncDmg;
    }

    public Integer getMaxMaxLevelUpIncArmor() {
        return maxMaxLevelUpIncArmor;
    }

    public void setMaxMaxLevelUpIncArmor(Integer maxMaxLevelUpIncArmor) {
        this.maxMaxLevelUpIncArmor = maxMaxLevelUpIncArmor;
    }

    public Integer getMaxMaxLevelUpIncMagicResist() {
        return maxMaxLevelUpIncMagicResist;
    }

    public void setMaxMaxLevelUpIncMagicResist(Integer maxMaxLevelUpIncMagicResist) {
        this.maxMaxLevelUpIncMagicResist = maxMaxLevelUpIncMagicResist;
    }

    public Integer getMaxMaxLevelUpIncSpeed() {
        return maxMaxLevelUpIncSpeed;
    }

    public void setMaxMaxLevelUpIncSpeed(Integer maxMaxLevelUpIncSpeed) {
        this.maxMaxLevelUpIncSpeed = maxMaxLevelUpIncSpeed;
    }

    public Integer getMaxMaxLevelUpIncLuck() {
        return maxMaxLevelUpIncLuck;
    }

    public void setMaxMaxLevelUpIncLuck(Integer maxMaxLevelUpIncLuck) {
        this.maxMaxLevelUpIncLuck = maxMaxLevelUpIncLuck;
    }

    public Integer getMaxMaxLevelUpIncCritChance() {
        return maxMaxLevelUpIncCritChance;
    }

    public void setMaxMaxLevelUpIncCritChance(Integer maxMaxLevelUpIncCritChance) {
        this.maxMaxLevelUpIncCritChance = maxMaxLevelUpIncCritChance;
    }

    public Integer getMaxMaxLevelUpIncCritDamage() {
        return maxMaxLevelUpIncCritDamage;
    }

    public void setMaxMaxLevelUpIncCritDamage(Integer maxMaxLevelUpIncCritDamage) {
        this.maxMaxLevelUpIncCritDamage = maxMaxLevelUpIncCritDamage;
    }

    public Integer getMaxMaxLevelUpIncDodgeChance() {
        return maxMaxLevelUpIncDodgeChance;
    }

    public void setMaxMaxLevelUpIncDodgeChance(Integer maxMaxLevelUpIncDodgeChance) {
        this.maxMaxLevelUpIncDodgeChance = maxMaxLevelUpIncDodgeChance;
    }

    public Integer getMaxMaxLevelUpIncHp() {
        return maxMaxLevelUpIncHp;
    }

    public void setMaxMaxLevelUpIncHp(Integer maxMaxLevelUpIncHp) {
        this.maxMaxLevelUpIncHp = maxMaxLevelUpIncHp;
    }

    @Override
    public String toString() {
        return "HT{name=" + name + '}';
    }

}
