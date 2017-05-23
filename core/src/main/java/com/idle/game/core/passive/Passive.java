package com.idle.game.core.passive;

import com.idle.game.core.type.PassiveType;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.BaseObject;

/**
 *
 * @author rafael
 */
public class Passive extends BaseObject {

    private PassiveType passiveType;
    private AttributeType attributeTypeLost;
    private AttributeType attributeTypeGain;
    private Integer ratioPercentage;

    public Passive(PassiveType passiveType) {
        this.passiveType = passiveType;
    }

    public Passive(PassiveType passiveType, AttributeType atributteTypeLost, AttributeType atributteTypeGain, Integer ratioPercentage) {
        this.passiveType = passiveType;
        this.attributeTypeLost = atributteTypeLost;
        this.attributeTypeGain = atributteTypeGain;
        this.ratioPercentage = ratioPercentage;
    }

    public PassiveType getPassiveType() {
        return passiveType;
    }

    public void setPassiveType(PassiveType passiveType) {
        this.passiveType = passiveType;
    }

    public AttributeType getAttributeTypeLost() {
        return attributeTypeLost;
    }

    public void setAttributeTypeLost(AttributeType attributeTypeLost) {
        this.attributeTypeLost = attributeTypeLost;
    }

    public AttributeType getAttributeTypeGain() {
        return attributeTypeGain;
    }

    public void setAttributeTypeGain(AttributeType attributeTypeGain) {
        this.attributeTypeGain = attributeTypeGain;
    }

    public Integer getRatioPercentage() {
        return ratioPercentage;
    }

    public void setRatioPercentage(Integer ratioPercentage) {
        this.ratioPercentage = ratioPercentage;
    }

    @Override
    public String toString() {
        return "P{" + "pt=" + passiveType + ", atl=" + attributeTypeLost + ", atg=" + attributeTypeGain + ", rp=" + ratioPercentage + '}';
    }

}
