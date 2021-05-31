package com.idle.game.core.passive;

import com.idle.game.core.action.type.ActionType;
import java.io.Serializable;

public class Condiction {

    private ActionType actionType;
    private Integer percentage;

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer perc) {
        this.percentage = perc;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Condiction() {
    }

    public Condiction(ActionType actionType) {
        this.actionType = actionType;
    }

    public Condiction(ActionType actionType, Integer percentage) {
        this.actionType = actionType;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "C{" + "t=" + actionType + ", p=" + percentage + '}';
    }

}
