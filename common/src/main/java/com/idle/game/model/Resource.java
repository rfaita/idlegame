package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Resource implements Serializable {

    private ResourceType type;
    private Long value;

    public Resource() {
    }

    public Resource(ResourceType type, Long value) {
        this.type = type;
        this.value = value;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
