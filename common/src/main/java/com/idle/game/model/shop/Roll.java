package com.idle.game.model.shop;

import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class Roll implements Serializable {

    private String rollType;
    private String value;

    public String getRollType() {
        return rollType;
    }

    public void setRollType(String rollType) {
        this.rollType = rollType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Roll(String rollType, String value) {
        this.rollType = rollType;
        this.value = value;
    }

    public Roll() {
    }

}
