package com.idle.game.model.battle.pve;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.model.battle.BattleFieldConfig;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "pveBattleFieldConfig")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PveBattleFieldConfig extends BattleFieldConfig {

    @Id
    private String id;

    @Indexed
    private String userId;

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

}
