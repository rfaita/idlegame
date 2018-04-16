package com.idle.game.model.battle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ValidationException;
import org.springframework.data.annotation.Transient;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class BattleFieldConfig implements Serializable {

    private Integer level;
    private Integer maxLayerSize = 3;
    @Transient
    private transient final Map<Integer, Integer> maxSitesSize = new HashMap<>();

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
