package com.idle.game.server.repository;

import com.idle.game.model.PlayerResource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface PlayerResourceRepository extends MongoRepository<PlayerResource, String> {

    PlayerResource findByPlayer(String player);

}
