package com.idle.game.core.action;

import com.idle.game.core.BaseObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Action extends BaseObject {

    private Boolean special = Boolean.FALSE;
    private ActionEffect mainActionEffect;
    private List<ActionEffect> secundaryActionsEffects = new ArrayList<>();

    public Action() {
    }

    public Action(ActionEffect mainActionEffect) {
        this(mainActionEffect, Boolean.FALSE);
    }

    public Action(ActionEffect mainActionEffect, Boolean special) {
        this(mainActionEffect, null, special);
    }

    public Action(ActionEffect ma, List<ActionEffect> se, Boolean special) {
        this.mainActionEffect = ma;
        this.secundaryActionsEffects = se;
        this.special = special;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public ActionEffect getMainActionEffect() {
        return mainActionEffect;
    }

    public void setMainActionEffect(ActionEffect ma) {
        this.mainActionEffect = ma;
    }

    public List<ActionEffect> getSecundaryActionsEffects() {
        return secundaryActionsEffects;
    }

    public void addSecundaryActionsEffects(ActionEffect se) {
        this.secundaryActionsEffects.add(se);
    }

    @Override
    public String toString() {
        return (special ? "AS" : "A") + "{" + "ma=" + mainActionEffect + ", sas=" + secundaryActionsEffects + '}';
    }

}
