package com.idle.game.server.repository;

import com.idle.game.model.Guild;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface GuildRepository extends MongoRepository<Guild, String> {

    Guild findByName(String name);

    Guild findByUserOwnerId(String userOwnerId);

}
