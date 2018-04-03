package com.idle.game.model.battle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.annotation.Id;
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
    private final Map<Integer, Integer> maxSitesSize = new HashMap<>();
    
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
        return maxSitesSize.getOrDefault(layer, 1);
    }

    public void setMaxSiteSize(Integer layer, Integer maxSiteSize) {
        this.maxSitesSize.put(layer, maxSiteSize);
    }

}
