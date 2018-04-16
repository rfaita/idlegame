package com.idle.game.helper.client.shop;

import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.server.dto.Envelope;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class InventoryClientImpl implements InventoryClient {

    @Override
    public Envelope<Inventory> findByUserId() {
        return new Envelope("inventory.service.is.down");
    }

    @Override
    public Envelope<Inventory> addItem(InventoryItem item) {
        return new Envelope("inventory.service.is.down");
    }

    @Override
    public Envelope<Inventory> removeItem(InventoryItem item) {
        return new Envelope("inventory.service.is.down");
    }
}
