package com.idle.game.core.action;

import com.idle.game.core.BaseObject;
import com.idle.game.core.formation.type.FormationPositionType;
import java.util.ArrayList;
import java.util.List;

public class Action extends BaseObject {

    private Boolean passive = Boolean.FALSE;
    private ActionEffect mainActionEffect;
    private List<ActionEffect> secondaryActionsEffects = new ArrayList<>();
    
    private FormationPositionType formationPositionType;

    public Action() {
    }

    public Action(ActionEffect mainActionEffect) {
        this(mainActionEffect, null);
    }

    public Action(ActionEffect ma, List<ActionEffect> se) {
        this.mainActionEffect = ma;
        this.secondaryActionsEffects = se;
    }

    public FormationPositionType getFormationPositionType() {
        return formationPositionType;
    }

    public void setFormationPositionType(FormationPositionType formationPositionType) {
        this.formationPositionType = formationPositionType;
    }
    
    public ActionEffect getMainActionEffect() {
        return mainActionEffect;
    }

    public void setMainActionEffect(ActionEffect ma) {
        this.mainActionEffect = ma;
    }

    public List<ActionEffect> getSecondaryActionsEffects() {
        return secondaryActionsEffects;
    }

    public void addSecondaryActionsEffects(ActionEffect se) {
        this.secondaryActionsEffects.add(se);
    }

    public Boolean getPassive() {
        return passive;
    }

    public void setPassive(Boolean passive) {
        this.passive = passive;
    }

    @Override
    public String toString() {
        return (passive ? "PA" : "A") + "{" + "ma=" + mainActionEffect + ", sas=" + secondaryActionsEffects + '}';
    }

}
