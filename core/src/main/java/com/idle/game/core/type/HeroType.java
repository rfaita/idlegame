package com.idle.game.core.type;

import com.idle.game.core.BaseObject;
import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.passive.Passive;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class HeroType extends BaseObject {

    private String name;
    private Integer stars;
    private Integer size = 1;
    private AtittudeType atittudeType;
    private Action defaultAction;
    private DamageType damageType;
    private DistanceType distanceType;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    private Integer baseDmg;
    private Integer baseArmor;
    private Integer baseMagicResist;
    private Integer baseSpeed;
    private Integer baseLuck;
    private Integer baseCritChance;
    private Integer baseCritDamage;
    private Integer baseDodgeChance;
    private Integer baseBlockChance;
    private Integer baseHp;

    private Integer maxLevelUpIncDmg;
    private Integer maxLevelUpIncArmor;
    private Integer maxLevelUpIncMagicResist;
    private Integer maxLevelUpIncSpeed;
    private Integer maxLevelUpIncLuck;
    private Integer maxLevelUpIncCritChance;
    private Integer maxLevelUpIncCritDamage;
    private Integer maxLevelUpIncDodgeChance;
    private Integer maxLevelUpIncBlockChance;
    private Integer maxLevelUpIncHp;

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getBaseDmg() {
        return baseDmg;
    }

    public void setBaseDmg(Integer baseDmg) {
        this.baseDmg = baseDmg;
    }

    public Integer getBaseArmor() {
        return baseArmor;
    }

    public void setBaseArmor(Integer baseArmor) {
        this.baseArmor = baseArmor;
    }

    public Integer getBaseMagicResist() {
        return baseMagicResist;
    }

    public void setBaseMagicResist(Integer baseMagicResist) {
        this.baseMagicResist = baseMagicResist;
    }

    public Integer getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(Integer baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public Integer getBaseLuck() {
        return baseLuck;
    }

    public void setBaseLuck(Integer baseLuck) {
        this.baseLuck = baseLuck;
    }

    public Integer getBaseCritChance() {
        return baseCritChance;
    }

    public void setBaseCritChance(Integer baseCritChance) {
        this.baseCritChance = baseCritChance;
    }

    public Integer getBaseCritDamage() {
        return baseCritDamage;
    }

    public void setBaseCritDamage(Integer baseCritDamage) {
        this.baseCritDamage = baseCritDamage;
    }

    public Integer getBaseDodgeChance() {
        return baseDodgeChance;
    }

    public void setBaseDodgeChance(Integer baseDodgeChance) {
        this.baseDodgeChance = baseDodgeChance;
    }

    public Integer getBaseBlockChance() {
        return baseBlockChance;
    }

    public void setBaseBlockChance(Integer baseBlockChance) {
        this.baseBlockChance = baseBlockChance;
    }

    public Integer getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(Integer baseHp) {
        this.baseHp = baseHp;
    }

    public Integer getMaxLevelUpIncDmg() {
        return maxLevelUpIncDmg;
    }

    public void setMaxLevelUpIncDmg(Integer maxLevelUpIncDmg) {
        this.maxLevelUpIncDmg = maxLevelUpIncDmg;
    }

    public Integer getMaxLevelUpIncArmor() {
        return maxLevelUpIncArmor;
    }

    public void setMaxLevelUpIncArmor(Integer maxLevelUpIncArmor) {
        this.maxLevelUpIncArmor = maxLevelUpIncArmor;
    }

    public Integer getMaxLevelUpIncMagicResist() {
        return maxLevelUpIncMagicResist;
    }

    public void setMaxLevelUpIncMagicResist(Integer maxLevelUpIncMagicResist) {
        this.maxLevelUpIncMagicResist = maxLevelUpIncMagicResist;
    }

    public Integer getMaxLevelUpIncSpeed() {
        return maxLevelUpIncSpeed;
    }

    public void setMaxLevelUpIncSpeed(Integer maxLevelUpIncSpeed) {
        this.maxLevelUpIncSpeed = maxLevelUpIncSpeed;
    }

    public Integer getMaxLevelUpIncLuck() {
        return maxLevelUpIncLuck;
    }

    public void setMaxLevelUpIncLuck(Integer maxLevelUpIncLuck) {
        this.maxLevelUpIncLuck = maxLevelUpIncLuck;
    }

    public Integer getMaxLevelUpIncCritChance() {
        return maxLevelUpIncCritChance;
    }

    public void setMaxLevelUpIncCritChance(Integer maxLevelUpIncCritChance) {
        this.maxLevelUpIncCritChance = maxLevelUpIncCritChance;
    }

    public Integer getMaxLevelUpIncCritDamage() {
        return maxLevelUpIncCritDamage;
    }

    public void setMaxLevelUpIncCritDamage(Integer maxLevelUpIncCritDamage) {
        this.maxLevelUpIncCritDamage = maxLevelUpIncCritDamage;
    }

    public Integer getMaxLevelUpIncDodgeChance() {
        return maxLevelUpIncDodgeChance;
    }

    public void setMaxLevelUpIncDodgeChance(Integer maxLevelUpIncDodgeChance) {
        this.maxLevelUpIncDodgeChance = maxLevelUpIncDodgeChance;
    }

    public Integer getMaxLevelUpIncBlockChance() {
        return maxLevelUpIncBlockChance;
    }

    public void setMaxLevelUpIncBlockChance(Integer maxLevelUpIncBlockChance) {
        this.maxLevelUpIncBlockChance = maxLevelUpIncBlockChance;
    }

    public Integer getMaxLevelUpIncHp() {
        return maxLevelUpIncHp;
    }

    public void setMaxLevelUpIncHp(Integer maxLevelUpIncHp) {
        this.maxLevelUpIncHp = maxLevelUpIncHp;
    }

    public List<Passive> getPassives() {
        return passives;
    }

    public void setPassives(List<Passive> passives) {
        this.passives = passives;
    }

    public void addPassive(Passive passive) {
        if (this.passives == null) {
            this.passives = new ArrayList<>(3);
        }
        this.passives.add(passive);
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

    public Action getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(Action a) {
        this.defaultAction = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public AtittudeType getAtittudeType() {
        return atittudeType;
    }

    public void setAtittudeType(AtittudeType atittudeType) {
        this.atittudeType = atittudeType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public HeroType() {
    }

    public HeroType(AtittudeType atittudeType) {
        this.atittudeType = atittudeType;
    }

    @Override
    public String toString() {
        return "HT{" + "t=" + atittudeType + ", name=" + name + '}';
    }

}
