package com.idle.game.server.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.buff.type.BuffEffectType;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.TargetType;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ActionEffectTypes implements Serializable {

    private final ActionType[] actionTypes = ActionType.values();
    private final TargetType[] targetTypes= TargetType.values();
    private final DamageType[] damageTypes = DamageType.values();
    private final BuffEffectType[] buffEffectTypes = BuffEffectType.values();
    private final AttributeType[] attributeTypes= AttributeType.values();

    public ActionType[] getActionTypes() {
        return actionTypes;
    }

    public TargetType[] getTargetTypes() {
        return targetTypes;
    }

    public DamageType[] getDamageTypes() {
        return damageTypes;
    }

    public BuffEffectType[] getBuffEffectTypes() {
        return buffEffectTypes;
    }

    public AttributeType[] getAttributeTypes() {
        return attributeTypes;
    }
    
    
}
