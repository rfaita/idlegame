package com.idle.game.server.repository;

import com.idle.game.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
