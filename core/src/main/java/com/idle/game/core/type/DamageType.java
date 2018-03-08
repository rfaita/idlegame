package com.idle.game.core.type;

/**
 *
 * @author rafael
 */
public enum DamageType {

    SLASH(DefenseType.SLASH), BLUNT(DefenseType.BLUNT), PIERCE(DefenseType.PIERCE),
    FIRE(DefenseType.FIRE), ICE(DefenseType.ICE), THUNDER(DefenseType.THUNDER),
    EARTH(DefenseType.EARTH), DARK(DefenseType.DARK), HOLY(DefenseType.HOLY);

    private final DefenseType defenseType;

    public DefenseType getDefenseType() {
        return defenseType;
    }

    private DamageType(DefenseType defenseType) {
        this.defenseType = defenseType;
    }

}
