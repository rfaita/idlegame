package com.idle.game.model.battle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "battlefieldconfig")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BattleFieldConfig implements Serializable {

    @Id
    private String id;

    private Integer maxLayerSize = 3;
    @Transient
    private transient final Map<Integer, Integer> maxSitesSize = new HashMap<>();

    @Indexed
    private String guildId;
    @Indexed
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMaxLayerSize() {
        return maxLayerSize;
    }

    public void setMaxLayerSize(Integer maxLayerSize) {
        this.maxLayerSize = maxLayerSize;
    }

    public Integer getMaxSiteSize(Integer layer) {
        if (layer > maxLayerSize - 1) {
            throw new ValidationException("layer.greater.max.layer.size");
        }
        return maxSitesSize.getOrDefault(layer, 1);
    }

    public void setMaxSiteSize(Integer layer, Integer maxSiteSize) {
        if (layer > maxLayerSize - 1) {
            throw new ValidationException("layer.greater.max.layer.size");
        }
        this.maxSitesSize.put(layer, maxSiteSize);
    }

}
