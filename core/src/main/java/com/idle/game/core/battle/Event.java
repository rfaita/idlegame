package com.idle.game.core.battle;

import com.idle.game.core.type.DamageType;
import com.idle.game.core.action.type.SubActionType;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.buff.Buff;
import com.idle.game.core.buff.type.BuffEffectType;
import com.idle.game.core.type.AttributeType;
import java.io.Serializable;

public class Event {

    private ActionType actionType;
    private BuffEffectType buffEffectType;
    private Integer value;

    private SubActionType subType;

    private DamageType damageType;
    private AttributeType attributeType;

    private Buff buff;

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public BuffEffectType getBuffEffectType() {
        return buffEffectType;
    }

    public void setBuffEffectType(BuffEffectType buffEffectType) {
        this.buffEffectType = buffEffectType;
    }

    public SubActionType getSubType() {
        return subType;
    }

    public void setSubType(SubActionType subType) {
        this.subType = subType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType type) {
        this.actionType = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Event() {
    }

    public Event(ActionType type) {
        this.actionType = type;
    }
    
    public Event(ActionType type, BuffEffectType buffEffectType) {
        this.buffEffectType = buffEffectType;
    }

    @Override
    public String toString() {
        //TODO
        if (actionType != null) {
            return "BE{" + "t=" + actionType + ", st=" + subType + ", value=" + value + ", dt=" + damageType + '}';
        } else if (damageType != null) {
            return "BE{" + "bet=" + buffEffectType + ", st=" + subType + ", value=" + value + ", dt=" + damageType + '}';
        } else if (value != null) {
            return "BE{" + "bet=" + buffEffectType + ", st=" + subType + ", value=" + value + ", at=" + attributeType + '}';
        } else {
            return "BE{" + "bet=" + buffEffectType + ", st=" + subType + ", at=" + attributeType + '}';
        }
    }

}
