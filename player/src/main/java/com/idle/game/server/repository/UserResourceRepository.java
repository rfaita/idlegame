package com.idle.game.server.repository;

import com.idle.game.model.UserResource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface UserResourceRepository extends MongoRepository<UserResource, String> {

    UserResource findByUserId(String userId);

}
