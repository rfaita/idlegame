package com.idle.game.server.repository;

import com.idle.game.model.shop.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {
    
    Inventory findByUserId(String userId);

}
