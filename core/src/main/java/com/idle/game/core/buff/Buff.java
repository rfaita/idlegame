package com.idle.game.core.buff;

import com.idle.game.core.type.DamageType;
import com.idle.game.core.buff.type.BuffEffectType;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.type.AttributeType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;

/**
 *
 * @author rafael
 */
public class Buff implements Serializable {

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
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.damageType);
        hash = 97 * hash + Objects.hashCode(this.effectType);
        hash = 97 * hash + Objects.hashCode(this.attributeType);
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
        if (this.damageType != other.damageType) {
            return false;
        }
        if (this.effectType != other.effectType) {
            return false;
        }
        return this.attributeType == other.attributeType;
    }

    public Buff duplicate() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Buff ret = (Buff) ois.readObject();
            return ret;
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "B{" + "td=" + turnDuration + ", ct=" + currTurn + ", v=" + value + ", dt=" + damageType + ", et=" + effectType + '}';
    }

}
