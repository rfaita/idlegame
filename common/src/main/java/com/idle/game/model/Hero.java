package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.type.Defense;
import com.idle.game.core.type.DefenseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "hero")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Hero implements Serializable {

    @Id
    private String id;
    private String heroType;
    @Indexed
    private String player;
    private Integer level;
    private HeroQuality quality;

    private Integer baseDmg;
    private Integer baseAp;
    private List<Defense> baseDefenses = new ArrayList<>();
    private Integer baseSpeed;
    private Integer baseCritChance;
    private Integer baseCritDamage;
    private Integer baseDodgeChance;
    private Integer baseHp;

    private Integer maxLevelDmg;
    private Integer maxLevelAp;
    private List<Defense> maxLevelDefenses = new ArrayList<>();
    private Integer maxLevelSpeed;
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

    public Integer getBaseAp() {
        return baseAp;
    }

    public void setBaseAp(Integer baseAp) {
        this.baseAp = baseAp;
    }

    public Integer getMaxLevelAp() {
        return maxLevelAp;
    }

    public void setMaxLevelAp(Integer maxLevelAp) {
        this.maxLevelAp = maxLevelAp;
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

    public Integer getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(Integer baseSpeed) {
        this.baseSpeed = baseSpeed;
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

    public Integer getMaxLevelSpeed() {
        return maxLevelSpeed;
    }

    public void setMaxLevelSpeed(Integer maxLevelSpeed) {
        this.maxLevelSpeed = maxLevelSpeed;
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

    public List<Defense> getBaseDefenses() {
        return baseDefenses;
    }

    public void setBaseDefenses(List<Defense> baseDefenses) {
        this.baseDefenses = baseDefenses;
    }

    public List<Defense> getMaxLevelDefenses() {
        return maxLevelDefenses;
    }

    public void setMaxLevelDefenses(List<Defense> maxLevelDefenses) {
        this.maxLevelDefenses = maxLevelDefenses;
    }

    public Defense getBaseDefense(DefenseType dt) {

        Optional<Defense> ret = this.getBaseDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            this.getBaseDefenses().add(new Defense(dt, 0));
            return getBaseDefense(dt);
        }
    }

    public void setBaseDefense(DefenseType dt, Integer value) {
        Defense d = getBaseDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getBaseDefenses().add(new Defense(dt, value));
        }
    }

    public Defense getMaxLevelDefense(DefenseType dt) {

        Optional<Defense> ret = this.getMaxLevelDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            this.getMaxLevelDefenses().add(new Defense(dt, 0));
            return getMaxLevelDefense(dt);
        }
    }

    public void setMaxLevelDefense(DefenseType dt, Integer value) {
        Defense d = getMaxLevelDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getMaxLevelDefenses().add(new Defense(dt, value));
        }
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
