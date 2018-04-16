package com.idle.game.model.battle.pve;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.model.battle.BattleField;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "pvebattlefield")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PveBattleField implements Serializable {

    @Id
    private String id;

    @Indexed
    private String userId;

    private String battleFieldId;

    @Transient
    private transient BattleField battleField;

    public BattleField getBattleField() {
        return battleField;
    }

    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
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

    public String getBattleFieldId() {
        return battleFieldId;
    }

    public void setBattleFieldId(String battleFieldId) {
        this.battleFieldId = battleFieldId;
    }

}
