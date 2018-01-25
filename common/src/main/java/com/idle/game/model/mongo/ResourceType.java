package com.idle.game.model.mongo;

/**
 *
 * @author rafael
 */
public enum ResourceType {

    GOLD_PS(null), GEM_PS(null), RUNE_PS(null), GOLD(GOLD_PS), GEM(GEM_PS), RUNE(RUNE_PS);
    private final ResourceType resourcePS;

    public ResourceType getResourcePS() {
        return resourcePS;
    }

    private ResourceType(ResourceType resourcePS) {
        this.resourcePS = resourcePS;
    }

}
