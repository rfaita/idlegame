package com.idle.game.core.type;

import com.idle.game.core.action.Action;
import com.idle.game.core.constant.IdleConstants;
import com.idle.game.core.passive.Passive;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rafael
 */
public class BattleHeroType implements Serializable {

    private HeroTypeQuality heroTypeQuality;
    private String name;
    private Action specialAction;
    private Action defaultAction;
    private DamageType damageType;
    private DistanceType distanceType;
    private List<Passive> passives;

    private Integer maxLevel = IdleConstants.HERO_MAX_LEVEL;

    public BattleHeroType() {
    }

    public HeroTypeQuality getHeroTypeQuality() {
        return heroTypeQuality;
    }

    public void setHeroTypeQuality(HeroTypeQuality heroTypeQuality) {
        this.heroTypeQuality = heroTypeQuality;
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

    @Override
    public String toString() {
        return "HT{name=" + name + '}';
    }

}
