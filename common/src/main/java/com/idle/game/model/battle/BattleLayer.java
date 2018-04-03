package com.idle.game.model.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael
 */
public class BattleLayer {

    private final List<BattleSite> sites = new ArrayList<>();
    private Integer depth;

    public BattleLayer(Integer depth) {
        this.depth = depth;
    }

    public BattleLayer() {
    }

    public BattleSite getSite(String formationId) {
        Optional<BattleSite> ret = this.sites.stream().filter((s) -> s.getFormationId().equals(formationId)).findFirst();

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    public List<BattleSite> getSites() {
        return sites;
    }

    public void addSite(BattleSite battleSite) {
        this.sites.add(battleSite);
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

}
