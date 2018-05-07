package com.idle.game.tests.helper;

import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.model.shop.LootRollItem;
import com.idle.game.model.shop.LootRollType;
import com.idle.game.model.shop.LootRollValue;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static InventoryItem createLootRoll(String itemId) {
        return new InventoryItem("teste", itemId);
    }

    public static InventoryItem createLootRollWithLootRollItem(String itemId) {
        return new LootRollItem(itemId);
    }

    public static Inventory createInventory(String userId) {

        Inventory ret = new Inventory();

        ret.setUserId(userId);

        return ret;

    }

    public static LootRoll createLootRollToHero(String id) {

        LootRoll ret = new LootRoll();

        ret.setType(LootRollType.HERO);

        return ret;
    }

    public static LootRoll createLootRollToItem(String id) {

        LootRoll ret = new LootRoll();

        ret.setType(LootRollType.ITEM);

        ret.addRoll("teste", new LootRollValue(id, 100.0));

        return ret;
    }

    public static Answer<Inventory> createInventoryAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (Inventory) invocation.getArguments()[0];
        };
    }

}
