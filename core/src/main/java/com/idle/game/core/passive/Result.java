package com.idle.game.core.passive;

import com.idle.game.core.type.AttributeType;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class Result implements Serializable {

    private AttributeType fromAttribute;
    private AttributeType toAttribute;
    private Integer percentage;

    public Result() {
    }

    public Result(AttributeType fromAttribute, AttributeType toAttribute, Integer perc) {
        this.fromAttribute = fromAttribute;
        this.toAttribute = toAttribute;
        this.percentage = percentage;
    }

    public AttributeType getFromAttribute() {
        return fromAttribute;
    }

    public void setFromAttribute(AttributeType fromAttribute) {
        this.fromAttribute = fromAttribute;
    }

    public AttributeType getToAttribute() {
        return toAttribute;
    }

    public void setToAttribute(AttributeType toAttribute) {
        this.toAttribute = toAttribute;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer perc) {
        this.percentage = perc;
    }

    @Override
    public String toString() {
        return "R{" + "fa=" + fromAttribute + ", ta=" + toAttribute + ", p=" + percentage + '}';
    }

}
