package com.idle.game.core.type;

import com.idle.game.core.type.DefenseType;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class Defense implements Serializable {

    private DefenseType type;
    private Integer value;

    public Defense() {
    }

    public Defense(DefenseType type) {
        this.type = type;
    }

    public Defense(DefenseType type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public DefenseType getType() {
        return type;
    }

    public void setType(DefenseType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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
