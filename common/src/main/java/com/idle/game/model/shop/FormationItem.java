package com.idle.game.model.shop;

import com.idle.game.model.Formation;

/**
 *
 * @author rafael
 */
public class FormationItem extends InventoryItem {

    public FormationItem() {
    }

    public FormationItem(String itemId) {
        super(Formation.class.getName(), itemId);
    }
    
    
}
