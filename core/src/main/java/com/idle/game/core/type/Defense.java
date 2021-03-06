package com.idle.game.core.type;

import java.util.Objects;

public class Defense {

    private DefenseType type;
    private Double value;

    public Defense() {
    }

    public Defense(DefenseType type) {
        this.type = type;
    }

    public Defense(DefenseType type, Double value) {
        this.type = type;
        this.value = value;
    }

    public DefenseType getType() {
        return type;
    }

    public void setType(DefenseType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.type);
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
        final Defense other = (Defense) obj;
        return this.type == other.type;
    }

}
