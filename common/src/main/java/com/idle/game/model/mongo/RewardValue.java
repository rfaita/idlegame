package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RewardValue implements Serializable {

    private ResourceType resource;
    private Long value;

    public RewardValue(ResourceType resource, Long value) {
        this.resource = resource;
        this.value = value;
    }

    public RewardValue() {
    }

    public ResourceType getRewardType() {
        return resource;
    }

    public void setRewardType(ResourceType rewardType) {
        this.resource = rewardType;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
