package com.idle.game.tests;

import com.idle.game.model.battle.BattleField;
import com.idle.game.model.battle.BattleSite;
import com.idle.game.model.battle.pve.PveBattleFieldConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author rafael
 */
public class BattleFieldTest {

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    private PveBattleFieldConfig getDefaultConfig() {
        PveBattleFieldConfig ret = new PveBattleFieldConfig();
        ret.setMaxSiteSize(0, 1);
        ret.setMaxSiteSize(1, 3);
        ret.setMaxSiteSize(2, 9);
        return ret;
    }

    @Test
    public void testSuccess() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "01"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "04"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        field.validate(getDefaultConfig());

    }

    @Test
    public void testInitialSiteWithNextFormationId() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", "00"));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "01"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "04"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

    @Test
    public void testFirstLayerWithTwoSites() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));
        field.getLayer(0).addSite(new BattleSite("30", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "01"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "04"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

    @Test
    public void testSecondLayerWithFourSites() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "01"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));
        field.getLayer(1).addSite(new BattleSite("30", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "04"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

    @Test
    public void testThirdLayerWithTenSites() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "01"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "04"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));
        field.getLayer(2).addSite(new BattleSite("30", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

    @Test
    public void testSecondLayerWithInvalidAheadLayerNextFormation() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "02"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "04"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

    @Test
    public void testThirdLayerWithInvalidAheadLayerNextFormation() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "01"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "05"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

    @Test
    public void testSecondLayerWithNullAheadLayerNextFormation() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", null));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", "04"));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

    @Test
    public void testThirdLayerWithNullAheadLayerNextFormation() {

        BattleField field = new BattleField();

        field.addInitialLayers();

        field.getLayer(0).addSite(new BattleSite("01", null));

        field.getLayer(1).addSite(new BattleSite("02", "01"));
        field.getLayer(1).addSite(new BattleSite("03", "01"));
        field.getLayer(1).addSite(new BattleSite("04", "01"));

        field.getLayer(2).addSite(new BattleSite("05", "02"));
        field.getLayer(2).addSite(new BattleSite("06", "02"));
        field.getLayer(2).addSite(new BattleSite("07", "02"));

        field.getLayer(2).addSite(new BattleSite("08", "03"));
        field.getLayer(2).addSite(new BattleSite("09", "03"));
        field.getLayer(2).addSite(new BattleSite("10", "03"));

        field.getLayer(2).addSite(new BattleSite("11", "04"));
        field.getLayer(2).addSite(new BattleSite("12", null));
        field.getLayer(2).addSite(new BattleSite("13", "04"));

        expcetionExpect.expectMessage("battle.field.wrong.configuration");

        field.validate(getDefaultConfig());

    }

}
