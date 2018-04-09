package com.idle.game.model;

import static java.lang.Boolean.*;

/**
 *
 * @author rafael
 */
public enum ResourceType {

    GOLD_PD(TRUE, null, null, null), GEM_PD(TRUE, null, null, null), RUNE_PD(TRUE, null, null, null),
    GOLD_PH(TRUE, null, null, null), GEM_PH(TRUE, null, null, null), RUNE_PH(TRUE, null, null, null),
    GOLD_PS(TRUE, null, null, null), GEM_PS(TRUE, null, null, null), RUNE_PS(TRUE, null, null, null),
    GOLD(FALSE, GOLD_PS, GOLD_PH, GOLD_PD), GEM(FALSE, GEM_PS, GEM_PH, GEM_PD), RUNE(FALSE, RUNE_PS, RUNE_PH, RUNE_PD);

    private final Boolean timeResource;
    private final ResourceType resourcePerSecond;
    private final ResourceType resourcePerHour;
    private final ResourceType resourcePerDay;

    public Boolean isTimeResource() {
        return timeResource;
    }

    public ResourceType getResourcePerSecond() {
        return resourcePerSecond;
    }

    public ResourceType getResourcePerHour() {
        return resourcePerHour;
    }

    public ResourceType getResourcePerDay() {
        return resourcePerDay;
    }

    private ResourceType(Boolean timeResource, ResourceType resourcePerSecond, ResourceType resourcePerHour, ResourceType resourcePerDay) {
        this.timeResource = timeResource;
        this.resourcePerSecond = resourcePerSecond;
        this.resourcePerHour = resourcePerHour;
        this.resourcePerDay = resourcePerDay;
    }

}
