package com.idle.game.core.formation.type;

public enum FormationPositionType {

    F("M"), M("B"), B("F"), ALL_LINES(null);

    private final String next;

    FormationPositionType(String next) {
        this.next = next;
    }

    public FormationPositionType getNext() {
        return FormationPositionType.valueOf(next);
    }

}
