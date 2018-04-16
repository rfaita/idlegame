package com.idle.game.model.battle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "battlefield")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BattleField implements Serializable {

    @Id
    private String id;

    private final List<BattleLayer> layers = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BattleLayer> getLayers() {
        return layers;
    }

    public void addLayer(BattleLayer layer) {
        this.layers.add(layer);
    }

    public BattleLayer getLayer(Integer layer) {
        Optional<BattleLayer> ret = this.layers.stream().filter((l) -> l.getDepth().equals(layer)).findFirst();

        if (ret.isPresent()) {
            return ret.get();
        } else {
            throw new ValidationException("layer.not.found");
        }
    }

    public BattleSite getSite(String formationId) {
        int i = deeperLayer().getDepth();

        while (i >= 0) {
            BattleSite site = getLayer(i).getSite(formationId);

            if (site != null) {
                return site;
            }
            i--;
        }
        return null;
    }

    public BattleSite getNextSite(String formationId) {
        BattleSite site = getSite(formationId);

        if (site != null) {
            return getSite(site.getNextFormationId());
        } else {
            return null;
        }

    }

    public Boolean isInitialSite(String formationId) {
        return this.deeperLayer().getSite(formationId) != null;
    }

    public BattleLayer deeperLayer() {
        return layers.stream().reduce((BattleLayer t, BattleLayer u) -> t.getDepth() > u.getDepth() ? t : u).get();
    }

    public void validate(BattleFieldConfig battleFieldConfig) {

        int i = deeperLayer().getDepth();

        while (i > 0) {
            BattleLayer layerAhead = getLayer(i - 1);
            BattleLayer layer = getLayer(i);

            if (i == 1) {
                //Verify if deeper node dont lookup for a next node
                if (layerAhead.getSites().stream().filter((s) -> s.getNextFormationId() != null).count() > 0) {
                    throw new ValidationException("battle.field.wrong.configuration");
                }
            }

            if (layerAhead.getSites().size() > battleFieldConfig.getMaxSiteSize(layerAhead.getDepth())
                    || layer.getSites().size() > battleFieldConfig.getMaxSiteSize(layer.getDepth())) {
                throw new ValidationException("battle.field.wrong.configuration");
            }

            for (BattleSite site : layer.getSites()) {
                if (layerAhead.getSites().stream().filter((s) -> s.getFormationId().equals(site.getNextFormationId())).count() <= 0) {
                    throw new ValidationException("battle.field.wrong.configuration");
                }
            }
            i--;
        }

    }

    public void addInitialLayers() {
        this.addLayer(new BattleLayer(0));
        this.addLayer(new BattleLayer(1));
        this.addLayer(new BattleLayer(2));
    }

}
