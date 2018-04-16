package com.idle.game.server.repository;

import com.idle.game.model.battle.pve.PveBattleFieldConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface PveBattleFieldConfigRepository extends MongoRepository<PveBattleFieldConfig, String> {

    PveBattleFieldConfig findByUserId(String userId);

}
