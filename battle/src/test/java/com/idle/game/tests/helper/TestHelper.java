package com.idle.game.tests.helper;

import com.idle.game.model.battle.BattleField;
import com.idle.game.model.battle.BattleSite;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static BattleField createBattleField(String id) {
        BattleField ret = new BattleField();

        ret.setId(id);

        ret.addInitialLayers();

        ret.getLayer(0).addSite(new BattleSite("01", null));

        ret.getLayer(1).addSite(new BattleSite("02", "01"));
        ret.getLayer(1).addSite(new BattleSite("03", "01"));
        ret.getLayer(1).addSite(new BattleSite("04", "01"));

        ret.getLayer(2).addSite(new BattleSite("05", "02"));
        ret.getLayer(2).addSite(new BattleSite("06", "02"));
        ret.getLayer(2).addSite(new BattleSite("07", "02"));

        ret.getLayer(2).addSite(new BattleSite("08", "03"));
        ret.getLayer(2).addSite(new BattleSite("09", "03"));
        ret.getLayer(2).addSite(new BattleSite("10", "03"));

        ret.getLayer(2).addSite(new BattleSite("11", "04"));
        ret.getLayer(2).addSite(new BattleSite("12", "04"));
        ret.getLayer(2).addSite(new BattleSite("13", "04"));

        return ret;
    }

}
