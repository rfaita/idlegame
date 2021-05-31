package com.idle.game.core.passive;

import com.idle.game.core.passive.type.PassiveType;
import com.idle.game.core.BaseObject;
import com.idle.game.core.action.Action;

public class Passive extends BaseObject {

    private PassiveType passiveType;
    private Condiction condiction;
    private Action action;
    private Result result;

    public Passive() {
    }

    public Passive(PassiveType passiveType, Condiction condiction, Action action) {
        this.passiveType = passiveType;
        this.condiction = condiction;
        this.action = action;
    }

    public Passive(PassiveType passiveType, Condiction condiction, Result result) {
        this.passiveType = passiveType;
        this.condiction = condiction;
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Condiction getCondiction() {
        return condiction;
    }

    public void setCondiction(Condiction condiction) {
        this.condiction = condiction;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Passive(PassiveType passiveType) {
        this.passiveType = passiveType;
    }

    public PassiveType getPassiveType() {
        return passiveType;
    }

    public void setPassiveType(PassiveType passiveType) {
        this.passiveType = passiveType;
    }

    @Override
    public String toString() {
        if (action != null) {
            return "P{" + "pt=" + passiveType + ", c=" + condiction + ", a=" + action + '}';
        } else {
            return "P{" + "pt=" + passiveType + ", c=" + condiction + ", r=" + result + '}';
        }
    }

}
