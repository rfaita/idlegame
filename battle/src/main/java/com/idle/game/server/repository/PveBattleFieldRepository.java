package com.idle.game.server.repository;

import com.idle.game.model.battle.pve.PveBattleField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface PveBattleFieldRepository extends MongoRepository<PveBattleField, String> {

    PveBattleField findByUserId(String userId);

}
