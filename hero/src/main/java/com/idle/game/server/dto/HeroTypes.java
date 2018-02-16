package com.idle.game.server.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.hero.type.HeroQuality;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HeroTypes implements Serializable {

    private final HeroQuality[] heroQualitys = HeroQuality.values();

    public HeroQuality[] getHeroQualitys() {
        return heroQualitys;
    }

}
