package com.idle.game.server.repository;

import com.idle.game.model.mongo.BattleHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface BattleHistoryRepository extends MongoRepository<BattleHistory, String> {

}
