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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    private List<Action> specialActions = new ArrayList<>();
    private List<Action> defaultActions = new ArrayList<>();
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

    @Override
    public String toString() {
        return "HT{name=" + name + '}';
    }

}
