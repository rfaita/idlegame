package com.idle.game.core.type;

/**
 *
 * @author rafael
 */
public enum FormationAllocation {

    PVP_DEFENSE(null), PVP(PVP_DEFENSE), PVE_DEFENSE(null), PVE(PVE_DEFENSE);

    private final FormationAllocation counter;

    public FormationAllocation getCounter() {
        return counter;
    }

    private FormationAllocation(FormationAllocation counter) {
        this.counter = counter;
    }

}
