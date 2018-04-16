package com.idle.game.model.shop;

/**
 *
 * @author rafael
 */
public class LootRollItem extends InventoryItem {

    public LootRollItem() {
    }

    public LootRollItem(String itemId) {
        super(LootRoll.class.getName(), itemId);
    }

}
