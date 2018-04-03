package com.idle.game.model.campaign;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "usecampaignpath")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserCampaignPath {

    @Id
    private String id;

    private String userId;
    private String campaignPathId;
    private String formationId;

    public String getFormationId() {
        return formationId;
    }

    public void setFormationId(String formationId) {
        this.formationId = formationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCampaignPathId() {
        return campaignPathId;
    }

    public void setCampaignPathId(String campaignPathId) {
        this.campaignPathId = campaignPathId;
    }

}
