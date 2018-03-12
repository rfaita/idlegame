package com.idle.game.server.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.formation.type.FormationPositionType;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.core.hero.type.HeroTypeRole;
import com.idle.game.core.hero.type.HeroTypeSize;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DistanceType;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HeroTypeTypes implements Serializable {

    private final HeroTypeFaction[] heroTypeFactions = HeroTypeFaction.values();
    private final HeroTypeQuality[] heroTypeQualitys = HeroTypeQuality.values();
    private final HeroTypeRole[] heroTypeRoles = HeroTypeRole.values();

    private final DamageType[] damageTypes = DamageType.values();
    private final DistanceType[] distanceTypes = DistanceType.values();

    private final FormationPositionType[] formationPositionTypes = FormationPositionType.values();
    private final HeroTypeSize[] heroTypeSizes = HeroTypeSize.values();

    public HeroTypeSize[] getHeroTypeSizes() {
        return heroTypeSizes;
    }

    public FormationPositionType[] getFormationPositionTypes() {
        return formationPositionTypes;
    }

    public HeroTypeFaction[] getHeroTypeFactions() {
        return heroTypeFactions;
    }

    public HeroTypeQuality[] getHeroTypeQualitys() {
        return heroTypeQualitys;
    }

    public HeroTypeRole[] getHeroTypeRoles() {
        return heroTypeRoles;
    }

    public DamageType[] getDamageTypes() {
        return damageTypes;
    }

    public DistanceType[] getDistanceTypes() {
        return distanceTypes;
    }

}
