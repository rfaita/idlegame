package com.idle.game.model.mongo.shop;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LootRollValue implements Serializable {

    private String rollType;
    private Double rollPercentage;

    public LootRollValue() {
    }

    public LootRollValue(String rollType, Double rollPercentage) {
        this.rollType = rollType;
        this.rollPercentage = rollPercentage;
    }

    public String getRollType() {
        return rollType;
    }

    public void setRollType(String rollType) {
        this.rollType = rollType;
    }

    public Double getRollPercentage() {
        return rollPercentage;
    }

    public void setRollPercentage(Double rollPercentage) {
        this.rollPercentage = rollPercentage;
    }

}
