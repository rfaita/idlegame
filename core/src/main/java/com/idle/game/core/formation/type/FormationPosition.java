package com.idle.game.core.formation.type;

import static com.idle.game.core.formation.type.FormationPositionType.BACK;
import static com.idle.game.core.formation.type.FormationPositionType.FRONT;

/**
 *
 * @author rafael
 */
public enum FormationPosition {

    FRONT_1(0, FRONT), FRONT_2(1, FRONT), BACK_1(2, BACK), BACK_2(3, BACK), BACK_3(4, BACK), BACK_4(5, BACK);

    private final Integer order;
    private final FormationPositionType type;

    public FormationPositionType getType() {
        return type;
    }

    public Integer getOrder() {
        return order;
    }

    private FormationPosition(Integer order, FormationPositionType type) {
        this.order = order;
        this.type = type;
    }

}
