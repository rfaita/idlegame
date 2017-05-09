package com.idle.game.core;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author rafael
 */
public class Buff extends BaseObject {

    private Integer turnDuration;
    private Integer currentTurn;
    private Integer value;
    private EffectType effectType;

    public Integer getTurnDuration() {
        return turnDuration;
    }

    public void setTurnDuration(Integer turnDuration) {
        this.turnDuration = turnDuration;
    }

    public Integer getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Integer currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public Buff() {
    }

    public Buff(Integer turnDuration, Integer value, EffectType effectType) {
        this.uuid = UUID.randomUUID();
        this.turnDuration = turnDuration;
        this.value = value;
        this.effectType = effectType;
        this.currentTurn = 0;
    }

    public Buff(Integer turnDuration, Integer currentTurn, Integer value, EffectType effectType) {
        this.uuid = UUID.randomUUID();
        this.turnDuration = turnDuration;
        this.currentTurn = currentTurn;
        this.value = value;
        this.effectType = effectType;
        this.currentTurn = 0;
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
        return "B{" + "td=" + turnDuration + ", ct=" + currentTurn + ", v=" + value + ", et=" + effectType + '}';
    }

}
