package com.idle.game.server.service;

import com.idle.game.helper.client.resource.UserResourceClient;
import com.idle.game.helper.client.shop.LootRollClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.model.shop.LootRoll;
import static com.idle.game.model.shop.LootRollType.ITEM;
import com.idle.game.model.shop.Roll;
import com.idle.game.server.repository.InventoryRepository;
import javax.validation.ValidationException;

/**
 *
 * @author rafael
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private UserResourceClient userResourceClient;

    @Autowired
    private LootRollClient lootRollClient;

    public Inventory findByUserId(String userId) {
        Inventory ret = inventoryRepository.findByUserId(userId);

        if (ret == null) {
            return create(userId);
        } else {
            return ret;
        }

    }

    private Inventory create(String userId) {

        Inventory inventory = new Inventory();
        inventory.setUserId(userId);

        return inventoryRepository.save(inventory);

    }

    private Inventory addItem(String userId, InventoryItem inventoryItem) {
        Inventory inventory = findByUserId(userId);

        inventory.addItem(inventoryItem);

        return inventoryRepository.save(inventory);

    }

    public Inventory removeItem(String userId, InventoryItem inventoryItem) {

        Inventory inventory = findByUserId(userId);

        inventory.removeItem(inventoryItem);

        return inventoryRepository.save(inventory);

    }

    public Inventory rollItem(String userId, InventoryItem inventoryItem) {

        Inventory inventory = findByUserId(userId);

        inventoryItem = inventory.getItem(inventoryItem);

        if (inventoryItem == null) {
            throw new ValidationException("item.not.found");
        }

        if (!LootRoll.class.getName().equals(inventoryItem.getItemClassName())) {
            throw new ValidationException("to.roll.a.item.must.be.a.loot.roll");
        }

        inventoryItem.setAmount(1L);
        removeItem(userId, inventoryItem);

        return buyItem(userId, inventoryItem.getItemId());

    }

    public Inventory buyItem(String userId, String lootRollId) {

        LootRoll lootRoll = lootRollClient.findById(lootRollId).getData();

        if (lootRoll != null) {

            if (lootRoll.getType().equals(ITEM)) {

                userResourceClient.useResources(lootRoll.getCost());
                
                InventoryItem inventoryItem = new InventoryItem();

                Roll ret = lootRoll.roll();

                inventoryItem.setItemClassName(ret.getRollType());
                inventoryItem.setItemId(ret.getValue());
                inventoryItem.setAmount(1L);
                
                return addItem(userId, inventoryItem);

            } else {
                throw new ValidationException("loot.roll.type.must.be.item");
            }
        } else {
            throw new ValidationException("loot.roll.not.found");
        }

    }

}
