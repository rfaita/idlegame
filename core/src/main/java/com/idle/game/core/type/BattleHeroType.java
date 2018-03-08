package com.idle.game.core.type;

import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.type.FormationPositionType;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeRole;
import com.idle.game.core.hero.type.HeroTypeSize;
import com.idle.game.core.passive.Passive;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rafael
 */
public class BattleHeroType implements Serializable {

    private HeroTypeQuality quality;
    private HeroTypeFaction faction;
    private HeroTypeRole role;
    private HeroTypeSize size;
    private String name;
    private final Map<FormationPositionType, Action> specialActions = new HashMap<>();
    private final Map<FormationPositionType, Action> defaultActions = new HashMap<>();
    private DistanceType distanceType;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    public BattleHeroType() {
    }

    public HeroTypeSize getSize() {
        return size;
    }

    public void setSize(HeroTypeSize size) {
        this.size = size;
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

    public Map<FormationPositionType, Action> getSpecialActions() {
        return specialActions;
    }

    public Map<FormationPositionType, Action> getDefaultActions() {
        return defaultActions;
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
