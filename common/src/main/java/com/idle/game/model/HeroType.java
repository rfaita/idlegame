package com.idle.game.model;

import com.idle.game.core.type.Defense;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.type.FormationPositionType;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.BattleHeroType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.hero.type.HeroTypeRole;
import com.idle.game.core.hero.type.HeroTypeSize;
import com.idle.game.core.type.DefenseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    private List<Action> specialActions = new ArrayList<>();
    private List<Action> defaultActions = new ArrayList<>();
    private DistanceType distanceType;
    private HeroTypeSize size;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    private Integer minBaseDmg = 0;
    private Integer minBaseAp = 0;
    private List<Defense> minBaseDefenses = new ArrayList<>();
    private Integer minBaseSpeed = 0;
    private Integer minBaseCritChance = 0;
    private Integer minBaseCritDamage = 0;
    private Integer minBaseDodgeChance = 0;
    private Integer minBaseHp = 0;

    private Integer minMaxLevelDmg = 0;
    private Integer minMaxLevelAp = 0;
    private List<Defense> minMaxLevelDefenses = new ArrayList<>();
    private Integer minMaxLevelSpeed = 0;
    private Integer minMaxLevelCritChance = 0;
    private Integer minMaxLevelCritDamage = 0;
    private Integer minMaxLevelDodgeChance = 0;
    private Integer minMaxLevelHp = 0;

    private Integer maxBaseDmg = 0;
    private Integer maxBaseAp = 0;
    private List<Defense> maxBaseDefenses = new ArrayList<>();
    private Integer maxBaseSpeed = 0;
    private Integer maxBaseCritChance = 0;
    private Integer maxBaseCritDamage = 0;
    private Integer maxBaseDodgeChance = 0;
    private Integer maxBaseHp = 0;

    private Integer maxMaxLevelDmg = 0;
    private Integer maxMaxLevelAp = 0;
    private List<Defense> maxMaxLevelDefenses = new ArrayList<>();
    private Integer maxMaxLevelSpeed = 0;
    private Integer maxMaxLevelCritChance = 0;
    private Integer maxMaxLevelCritDamage = 0;
    private Integer maxMaxLevelDodgeChance = 0;
    private Integer maxMaxLevelHp = 0;

    public HeroType() {
    }

    public BattleHeroType toBattleHeroType() {
        BattleHeroType ret = new BattleHeroType();
        ret.setDefaultActions(this.getDefaultActions());
        ret.setDistanceType(this.getDistanceType());
        ret.setQuality(this.getQuality());
        ret.setRole(this.getRole());
        ret.setFaction(this.getFaction());
        ret.setMaxLevel(this.getMaxLevel());
        ret.setName(this.getName());
        ret.setPassives(this.getPassives());
        ret.setSpecialActions(this.getSpecialActions());
        ret.setSize(this.getSize());

        return ret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DistanceType getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }

    public HeroTypeSize getSize() {
        return size;
    }

    public void setSize(HeroTypeSize size) {
        this.size = size;
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

    public Integer getMinBaseAp() {
        return minBaseAp;
    }

    public void setMinBaseAp(Integer minBaseAp) {
        this.minBaseAp = minBaseAp;
    }

    public Integer getMinBaseSpeed() {
        return minBaseSpeed;
    }

    public void setMinBaseSpeed(Integer minBaseSpeed) {
        this.minBaseSpeed = minBaseSpeed;
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

    public Integer getMinMaxLevelAp() {
        return minMaxLevelAp;
    }

    public void setMinMaxLevelAp(Integer minMaxLevelAp) {
        this.minMaxLevelAp = minMaxLevelAp;
    }

    public Integer getMinMaxLevelSpeed() {
        return minMaxLevelSpeed;
    }

    public void setMinMaxLevelSpeed(Integer minMaxLevelSpeed) {
        this.minMaxLevelSpeed = minMaxLevelSpeed;
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

    public Integer getMaxBaseAp() {
        return maxBaseAp;
    }

    public void setMaxBaseAp(Integer maxBaseAp) {
        this.maxBaseAp = maxBaseAp;
    }

    public Integer getMaxBaseSpeed() {
        return maxBaseSpeed;
    }

    public void setMaxBaseSpeed(Integer maxBaseSpeed) {
        this.maxBaseSpeed = maxBaseSpeed;
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

    public Integer getMaxMaxLevelAp() {
        return maxMaxLevelAp;
    }

    public void setMaxMaxLevelAp(Integer maxMaxLevelAp) {
        this.maxMaxLevelAp = maxMaxLevelAp;
    }

    public Integer getMaxMaxLevelSpeed() {
        return maxMaxLevelSpeed;
    }

    public void setMaxMaxLevelSpeed(Integer maxMaxLevelSpeed) {
        this.maxMaxLevelSpeed = maxMaxLevelSpeed;
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

    public List<Defense> getMinBaseDefenses() {
        return minBaseDefenses;
    }

    public void setMinBaseDefenses(List<Defense> minBaseDefenses) {
        this.minBaseDefenses = minBaseDefenses;
    }

    public List<Defense> getMinMaxLevelDefenses() {
        return minMaxLevelDefenses;
    }

    public void setMinMaxLevelDefenses(List<Defense> minMaxLevelDefenses) {
        this.minMaxLevelDefenses = minMaxLevelDefenses;
    }

    public List<Defense> getMaxBaseDefenses() {
        return maxBaseDefenses;
    }

    public void setMaxBaseDefenses(List<Defense> maxBaseDefenses) {
        this.maxBaseDefenses = maxBaseDefenses;
    }

    public List<Defense> getMaxMaxLevelDefenses() {
        return maxMaxLevelDefenses;
    }

    public void setMaxMaxLevelDefenses(List<Defense> maxMaxLevelDefenses) {
        this.maxMaxLevelDefenses = maxMaxLevelDefenses;
    }

    public List<Action> getSpecialActions() {
        return specialActions;
    }

    public void setSpecialActions(List<Action> specialActions) {
        this.specialActions = specialActions;
    }

    public List<Action> getDefaultActions() {
        return defaultActions;
    }

    public void setDefaultActions(List<Action> defaultActions) {
        this.defaultActions = defaultActions;
    }

    public Action getDefaultAction(FormationPositionType fpt) {

        Optional<Action> ret = this.getDefaultActions().stream().filter((d) -> d.getFormationPositionType().equals(fpt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Action getSpecialAction(FormationPositionType fpt) {

        Optional<Action> ret = this.getSpecialActions().stream().filter((d) -> d.getFormationPositionType().equals(fpt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Defense getMinBaseDefense(DefenseType dt) {

        Optional<Defense> ret = this.getMinBaseDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            this.getMinBaseDefenses().add(new Defense(dt, 0));
            return getMinBaseDefense(dt);
        }
    }

    public void setMinBaseDefense(DefenseType dt, Integer value) {
        Defense d = getMinBaseDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getMinBaseDefenses().add(new Defense(dt, value));
        }
    }

    public Defense getMaxBaseDefense(DefenseType dt) {

        Optional<Defense> ret = this.getMaxBaseDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            this.getMaxBaseDefenses().add(new Defense(dt, 0));
            return getMaxBaseDefense(dt);
        }
    }

    public void setMaxBaseDefense(DefenseType dt, Integer value) {
        Defense d = getMaxBaseDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getMaxBaseDefenses().add(new Defense(dt, value));
        }
    }

    public Defense getMinMaxLevelDefense(DefenseType dt) {

        Optional<Defense> ret = this.getMinMaxLevelDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            this.getMinMaxLevelDefenses().add(new Defense(dt, 0));
            return getMinMaxLevelDefense(dt);
        }
    }

    public void setMinMaxLevelDefense(DefenseType dt, Integer value) {
        Defense d = getMinMaxLevelDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getMinMaxLevelDefenses().add(new Defense(dt, value));
        }
    }

    public Defense getMaxMaxLevelDefense(DefenseType dt) {

        Optional<Defense> ret = this.getMaxMaxLevelDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        try {
            return ret.get();
        } catch (NoSuchElementException e) {
            this.getMaxMaxLevelDefenses().add(new Defense(dt, 0));
            return getMaxMaxLevelDefense(dt);
        }
    }

    public void setMaxMaxLevelDefense(DefenseType dt, Integer value) {
        Defense d = getMaxMaxLevelDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getMaxMaxLevelDefenses().add(new Defense(dt, value));
        }
    }

    @Override
    public String toString() {
        return "HT{name=" + name + '}';
    }

}
