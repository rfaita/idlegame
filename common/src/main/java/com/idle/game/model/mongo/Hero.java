package com.idle.game.model.mongo;

import com.idle.game.core.type.HeroQuality;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "hero")
public class Hero implements Serializable {

    @Id
    private String id;
    private String heroType;
    private String player;
    private Integer level;
    private HeroQuality heroQuality;

    private Integer baseDmg;
    private Integer baseArmor;
    private Integer baseMagicResist;
    private Integer baseSpeed;
    private Integer baseLuck;
    private Integer baseCritChance;
    private Integer baseCritDamage;
    private Integer baseDodgeChance;
    private Integer baseHp;

    private Integer maxLevelUpIncDmg;
    private Integer maxLevelUpIncArmor;
    private Integer maxLevelUpIncMagicResist;
    private Integer maxLevelUpIncSpeed;
    private Integer maxLevelUpIncLuck;
    private Integer maxLevelUpIncCritChance;
    private Integer maxLevelUpIncCritDamage;
    private Integer maxLevelUpIncDodgeChance;
    private Integer maxLevelUpIncHp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HeroQuality getHeroQuality() {
        return heroQuality;
    }

    public void setHeroQuality(HeroQuality heroQuality) {
        this.heroQuality = heroQuality;
    }

    public String getHeroType() {
        return heroType;
    }

    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getMaxLevelUpIncHp() {
        return maxLevelUpIncHp;
    }

    public void setMaxLevelUpIncHp(Integer maxLevelUpIncHp) {
        this.maxLevelUpIncHp = maxLevelUpIncHp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        return Objects.equals(this.id, other.id);
    }

}
