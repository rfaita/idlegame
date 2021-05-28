package com.idle.game.core.type;

import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.formation.type.FormationPositionType;
import com.idle.game.core.hero.type.HeroFaction;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.hero.type.HeroRole;
import com.idle.game.core.hero.type.HeroSize;
import com.idle.game.core.passive.Passive;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BattleHeroType {

    private HeroQuality quality;
    private HeroFaction faction;
    private HeroRole role;
    private HeroSize size;
    private String name;
    private List<Action> specialActions = new ArrayList<>();
    private List<Action> defaultActions = new ArrayList<>();
    private DistanceType distanceType;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    public BattleHeroType() {
    }

    public HeroSize getSize() {
        return size;
    }

    public void setSize(HeroSize size) {
        this.size = size;
    }

    public HeroQuality getQuality() {
        return quality;
    }

    public void setQuality(HeroQuality quality) {
        this.quality = quality;
    }

    public HeroFaction getFaction() {
        return faction;
    }

    public void setFaction(HeroFaction faction) {
        this.faction = faction;
    }

    public HeroRole getRole() {
        return role;
    }

    public void setRole(HeroRole role) {
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

        Optional<Action> ret = this.getDefaultActions().stream().filter(d -> d.getFormationPositionType().equals(fpt)).findFirst();
        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    public Action getSpecialAction(FormationPositionType fpt) {

        Optional<Action> ret = this.getSpecialActions().stream().filter(d -> d.getFormationPositionType().equals(fpt)).findFirst();
        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "HT{name=" + name + '}';
    }

}
