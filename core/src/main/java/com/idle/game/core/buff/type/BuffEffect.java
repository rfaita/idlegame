package com.idle.game.core.buff.type;

import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.BaseObject;

/**
 *
 * @author rafael
 */
public class BuffEffect extends BaseObject {

    private BuffType buffType;
    //Percentual do dano que sera utilizado no efeito caso o mesmo necessite
    private Integer percentage;
    //Percentual do efeito ocorrer;
    private Integer chance;
    //Numero de turnos que o efeito persiste
    private Integer duration;
    private ActionType actionType;

    public BuffType getBuffType() {
        return buffType;
    }

    public void setBuffType(BuffType buffType) {
        this.buffType = buffType;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public BuffEffect(BuffType buffType, Integer percentage, Integer chance, Integer duration, ActionType actionType) {
        this.buffType = buffType;
        this.percentage = percentage;
        this.chance = chance;
        this.duration = duration;
        this.actionType = actionType;
    }

    

    public BuffEffect() {
    }

    @Override
    public String toString() {
        return "BE{" + "bt=" + buffType + ", p=" + percentage + ", c=" + chance + ", d=" + duration + ", at=" + actionType + '}';
    }

   

}
