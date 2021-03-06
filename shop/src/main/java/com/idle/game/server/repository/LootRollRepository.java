package com.idle.game.server.repository;

import com.idle.game.model.shop.LootRoll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface LootRollRepository extends MongoRepository<LootRoll, String> {

}
