package com.idle.game.core.buff;

import com.idle.game.core.BaseObject;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.buff.type.BuffEffectType;
import com.idle.game.core.type.AttributeType;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class Buff extends BaseObject {

    private Integer turnDuration;
    private Integer currTurn;
    private Integer value;
    private DamageType damageType;
    private BuffEffectType effectType;
    private AttributeType attributeType;

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Integer getTurnDuration() {
        return turnDuration;
    }

    public void setTurnDuration(Integer turnDuration) {
        this.turnDuration = turnDuration;
    }

    public Integer getCurrTurn() {
        return currTurn;
    }

    public void setCurrTurn(Integer currTurn) {
        this.currTurn = currTurn;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BuffEffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(BuffEffectType effectType) {
        this.effectType = effectType;
    }

    public Buff() {
    }

    private Buff(Integer turnDuration, Integer value, DamageType damageType, BuffEffectType effectType, AttributeType attributeType) {
        this.uuid = UUID.randomUUID();
        this.turnDuration = turnDuration;
        this.value = value;
        this.damageType = damageType;
        this.effectType = effectType;
        this.attributeType = attributeType;
        this.currTurn = 0;
    }

    public Buff(Integer turnDuration, Integer value, BuffEffectType effectType, AttributeType attributeType) {
        this(turnDuration, value, null, effectType, attributeType);
    }

    public Buff(Integer turnDuration, Integer value, BuffEffectType effectType) {
        this(turnDuration, value, null, effectType, null);
    }

    public Buff(Integer turnDuration, Integer value, BuffEffectType effectType, DamageType damageType) {
        this(turnDuration, value, damageType, effectType, null);
    }

    public Buff(Integer turnDuration, BuffEffectType effectType) {
        this(turnDuration, null, null, effectType, null);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.uuid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Buff other = (Buff) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "B{" + "td=" + turnDuration + ", ct=" + currTurn + ", v=" + value + ", dt=" + damageType + ", et=" + effectType + '}';
    }

}
