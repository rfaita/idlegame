package com.idle.game.core.buff;

import com.idle.game.core.BaseObject;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.buff.type.BuffType;
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
    private ActionType effectType;
    private BuffType buffType;

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

    public ActionType getEffectType() {
        return effectType;
    }

    public void setEffectType(ActionType effectType) {
        this.effectType = effectType;
    }

    public Buff() {
    }

    public Buff(Integer turnDuration, Integer value, ActionType effectType, DamageType damageType, BuffType buffType) {
        this.uuid = UUID.randomUUID();
        this.turnDuration = turnDuration;
        this.value = value;
        this.effectType = effectType;
        this.currTurn = 0;
        this.damageType = damageType;
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
        return "B{" + "td=" + turnDuration + ", ct=" + currTurn + ", v=" + value + ", dt=" + damageType + ", et=" + effectType + ", bt=" + buffType + '}';
    }

    

}
