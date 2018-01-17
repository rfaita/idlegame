package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.hero.type.HeroQuality;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "hero")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Hero implements Serializable {

    @Id
    private String id;
    private String heroType;
    private String player;
    private Integer level;
    private HeroQuality quality;

    private Integer baseDmg;
    private Integer baseArmor;
    private Integer baseMagicResist;
    private Integer baseSpeed;
    private Integer baseLuck;
    private Integer baseCritChance;
    private Integer baseCritDamage;
    private Integer baseDodgeChance;
    private Integer baseHp;

    private Integer maxLevelDmg;
    private Integer maxLevelArmor;
    private Integer maxLevelMagicResist;
    private Integer maxLevelSpeed;
    private Integer maxLevelLuck;
    private Integer maxLevelCritChance;
    private Integer maxLevelCritDamage;
    private Integer maxLevelDodgeChance;
    private Integer maxLevelHp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HeroQuality getQuality() {
        return quality;
    }

    public void setQuality(HeroQuality heroQuality) {
        this.quality = heroQuality;
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

    public Integer getMaxLevelDmg() {
        return maxLevelDmg;
    }

    public void setMaxLevelDmg(Integer maxLevelDmg) {
        this.maxLevelDmg = maxLevelDmg;
    }

    public Integer getMaxLevelArmor() {
        return maxLevelArmor;
    }

    public void setMaxLevelArmor(Integer maxLevelArmor) {
        this.maxLevelArmor = maxLevelArmor;
    }

    public Integer getMaxLevelMagicResist() {
        return maxLevelMagicResist;
    }

    public void setMaxLevelMagicResist(Integer maxLevelMagicResist) {
        this.maxLevelMagicResist = maxLevelMagicResist;
    }

    public Integer getMaxLevelSpeed() {
        return maxLevelSpeed;
    }

    public void setMaxLevelSpeed(Integer maxLevelSpeed) {
        this.maxLevelSpeed = maxLevelSpeed;
    }

    public Integer getMaxLevelLuck() {
        return maxLevelLuck;
    }

    public void setMaxLevelLuck(Integer maxLevelLuck) {
        this.maxLevelLuck = maxLevelLuck;
    }

    public Integer getMaxLevelCritChance() {
        return maxLevelCritChance;
    }

    public void setMaxLevelCritChance(Integer maxLevelCritChance) {
        this.maxLevelCritChance = maxLevelCritChance;
    }

    public Integer getMaxLevelCritDamage() {
        return maxLevelCritDamage;
    }

    public void setMaxLevelCritDamage(Integer maxLevelCritDamage) {
        this.maxLevelCritDamage = maxLevelCritDamage;
    }

    public Integer getMaxLevelDodgeChance() {
        return maxLevelDodgeChance;
    }

    public void setMaxLevelDodgeChance(Integer maxLevelDodgeChance) {
        this.maxLevelDodgeChance = maxLevelDodgeChance;
    }

    public Integer getMaxLevelHp() {
        return maxLevelHp;
    }

    public void setMaxLevelHp(Integer maxLevelHp) {
        this.maxLevelHp = maxLevelHp;
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
