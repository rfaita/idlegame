package com.idle.game.helper.client.shop;

import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class InventoryClientImpl implements InventoryClient {

    @Override
    public Envelope<Inventory> findByUserId() {
        return new Envelope();
    }

    @Override
    public Envelope<Inventory> changeItemsAmountInUse(List<InventoryItem> items) {
        return new Envelope();
    }

    @Override
    public Envelope<Inventory> removeItems(List<InventoryItem> items) {
        return new Envelope();
    }

    @Override
    public Envelope<Inventory> addItems(String userId, List<InventoryItem> items) {
        return new Envelope();
    }
}
