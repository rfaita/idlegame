package com.idle.game.server.repository;

import com.idle.game.model.mongo.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    Player findById(String id);

}