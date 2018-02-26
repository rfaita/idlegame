package com.idle.game.server.repository;

import com.idle.game.model.mongo.Friend;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface FriendRepository extends MongoRepository<Friend, String> {

    Friend findByUserAndId(String user, String id);

    Friend findByUserAndIdAndAcceptedAndReverse(String user, String id, Boolean accepted, Boolean reverse);

    Friend findByUserAndUserFriend(String user, String userFriend);

    List<Friend> findAllByUser(String user);

}
