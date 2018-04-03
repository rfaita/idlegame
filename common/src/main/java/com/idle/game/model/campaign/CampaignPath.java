package com.idle.game.model.campaign;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.model.Reward;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "campaignpath")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CampaignPath implements Serializable {

    @Id
    private String id;

    private String battleFieldId;
    private String nextCampaignPathId;

    private Boolean initialPath;

    private Reward reward;

    public Boolean getInitialPath() {
        return initialPath;
    }

    public void setInitialPath(Boolean initialPath) {
        this.initialPath = initialPath;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBattleFieldId() {
        return battleFieldId;
    }

    public void setBattleFieldId(String battleFieldId) {
        this.battleFieldId = battleFieldId;
    }

    public String getNextCampaignPathId() {
        return nextCampaignPathId;
    }

    public void setNextCampaignPathId(String nextCampaignPathId) {
        this.nextCampaignPathId = nextCampaignPathId;
    }

}
