package com.idle.game.core.formation.type;

import static com.idle.game.core.formation.type.FormationPositionType.*;

public enum FormationPosition {

    B_2(2, 2, B), M_2(1, 2, M), F_2(0, 2, F),
    B_1(2, 1, B), M_1(1, 1, M), F_1(0, 1, F),
    B_0(2, 0, B), M_0(1, 0, M), F_0(0, 0, F);

    private final Integer x;
    private final Integer y;
    private final FormationPositionType type;

    public FormationPositionType getType() {
        return type;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }


    FormationPosition(Integer x, Integer y, FormationPositionType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

}
