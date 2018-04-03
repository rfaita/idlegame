package com.idle.game.model.battle;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class BattleSite implements Serializable {

    private String formationId;
    private String nextFormationId;

    public BattleSite(String formationId, String nextFormationId) {
        this.formationId = formationId;
        this.nextFormationId = nextFormationId;
    }

    public BattleSite() {
    }

    public String getFormationId() {
        return formationId;
    }

    public void setFormationId(String formationId) {
        this.formationId = formationId;
    }

    public String getNextFormationId() {
        return nextFormationId;
    }

    public void setNextFormationId(String nextFormationId) {
        this.nextFormationId = nextFormationId;
    }

}
