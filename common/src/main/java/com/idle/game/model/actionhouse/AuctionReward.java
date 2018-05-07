package com.idle.game.model.actionhouse;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.model.shop.InventoryItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AuctionReward implements Serializable {

    private List<InventoryItem> items = new ArrayList<>();
    //private List<Hero> heroes = new ArrayList<>();

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }

//    public List<Hero> getHeroes() {
//        return heroes;
//    }
//
//    public void setHeroes(List<Hero> heroes) {
//        this.heroes = heroes;
//    }

}
