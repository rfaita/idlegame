package com.idle.game.core.formation.type;

public enum FormationAllocation {

    PVP_DEFENSE(null), PVP(PVP_DEFENSE), PVE_DEFENSE(null), PVE(PVE_DEFENSE), PVE_DUNGEON_DEFENSE(null), PVE_DUNGEON(PVE_DUNGEON_DEFENSE);

    private final FormationAllocation counter;

    public FormationAllocation getCounter() {
        return counter;
    }

    FormationAllocation(FormationAllocation counter) {
        this.counter = counter;
    }

}
