package com.idle.game.helper.client.shop;

import static com.idle.game.constant.URIConstants.INVENTORY;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
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

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public Envelope<Inventory> addItem(@RequestBody InventoryItem item);

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public Envelope<Inventory> removeItem(@RequestBody InventoryItem item);

}
