package com.idle.game.server.repository;

import com.idle.game.model.battle.BattleFieldConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface BattleFieldConfigRepository extends MongoRepository<BattleFieldConfig, String> {

    BattleFieldConfig findByGuildId(String guildId);

    BattleFieldConfig findByUserId(String userId);

}
