package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Reward implements Serializable {


    private final List<RewardValue> rewards = new ArrayList<>();

    public List<RewardValue> getRewards() {
        return rewards;
    }

    public void addReward(RewardValue rewardValue) {
        this.rewards.add(rewardValue);
    }
    
    
}
