package com.idle.game.helper.client.shop;

import static com.idle.game.constant.URIConstants.INVENTORY;
import static com.idle.game.constant.URIConstants.INVENTORY_ADD_ITEMS;
import static com.idle.game.constant.URIConstants.INVENTORY_CHANGE_ITEMS_AMOUNT_IN_USE;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "shop-service", path = INVENTORY, fallback = InventoryClientImpl.class)
public interface InventoryClient {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Envelope<Inventory> findByUserId();

    @RequestMapping(path = "/" + INVENTORY_ADD_ITEMS + "/{userId}", method = RequestMethod.PUT)
    public Envelope<Inventory> addItems(@PathVariable("userId") String userId, @RequestBody List<InventoryItem> items);

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public Envelope<Inventory> removeItems(@RequestBody List<InventoryItem> items);

    @RequestMapping(path = "/" + INVENTORY_CHANGE_ITEMS_AMOUNT_IN_USE, method = RequestMethod.PUT)
    public Envelope<Inventory> changeItemsAmountInUse(@RequestBody List<InventoryItem> items);

}
