package com.idle.game.core.type;

import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeRole;
import com.idle.game.core.passive.Passive;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rafael
 */
public class BattleHeroType implements Serializable {

    private HeroTypeQuality quality;
    private HeroTypeFaction faction;
    private HeroTypeRole role;
    private String name;
    private Action specialAction;
    private Action defaultAction;
    private DistanceType distanceType;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    public BattleHeroType() {
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

    @Override
    public String toString() {
        return "HT{name=" + name + '}';
    }

}
