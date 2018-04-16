package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.INVENTORY;
import static com.idle.game.constant.URIConstants.SHOP__BUY_LOOT_ROLL;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael
 */
@RestController
@RequestMapping(INVENTORY)
public class InventoryRest {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private TokenHelper tokenHelper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Inventory> findByUserId() {

        Envelope<Inventory> ret = new Envelope<>();
        ret.setData(inventoryService.findByUserId(tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "/{lootRollId}", method = RequestMethod.PUT)
    public @ResponseBody
    Envelope<Inventory> buyItem(@PathVariable("lootRollId") String lootRollId) {

        Envelope<Inventory> ret = new Envelope<>();
        ret.setData(inventoryService.buyItem(tokenHelper.getUserId(), lootRollId));

        return ret;

    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public @ResponseBody
    Envelope<Inventory> removeItem(@RequestBody InventoryItem item) {

        Envelope<Inventory> ret = new Envelope<>();
        ret.setData(inventoryService.removeItem(tokenHelper.getUserId(), item));

        return ret;

    }

}
