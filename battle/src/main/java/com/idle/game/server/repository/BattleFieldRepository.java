package com.idle.game.server.repository;

import com.idle.game.model.battle.BattleField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface BattleFieldRepository extends MongoRepository<BattleField, String> {

}
