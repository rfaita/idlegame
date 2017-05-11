package com.idle.game.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Action extends BaseObject {
    
    private ActionEffect mainAction;
    
    private List<ActionEffect> secundaryActions = new ArrayList<>();
    
    public Action() {
    }
    
    public Action(ActionEffect ma, List<ActionEffect> se) {
        this.mainAction = ma;
        this.secundaryActions = se;
    }
    
    public ActionEffect getMainAction() {
        return mainAction;
    }
    
    public void setMainAction(ActionEffect ma) {
        this.mainAction = ma;
    }
    
    public List<ActionEffect> getSecundaryActions() {
        return secundaryActions;
    }
    
    public void addSecundaryActions(ActionEffect se) {
        this.secundaryActions.add(se);
    }

    @Override
    public String toString() {
        return "SS{" + "ma=" + mainAction + ", sas=" + secundaryActions + '}';
    }
    
    
}
