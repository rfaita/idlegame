package com.idle.game.core.action;

import com.idle.game.core.BaseObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Action extends BaseObject {

    private ActionEffect mainActionEffect;

    private List<ActionEffect> secundaryActionsEffects = new ArrayList<>();

    public Action() {
    }

    public Action(ActionEffect mainActionEffect) {
        this.mainActionEffect = mainActionEffect;
    }
    
    public Action(ActionEffect ma, List<ActionEffect> se) {
        this.mainActionEffect = ma;
        this.secundaryActionsEffects = se;
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
        return "SS{" + "ma=" + mainActionEffect + ", sas=" + secundaryActionsEffects + '}';
    }

}
