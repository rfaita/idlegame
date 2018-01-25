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

    Friend findById(String id);

    Friend findByUserAndIdAndAccepted(String user, String id, Boolean accepted);

    Friend findByUserFriendAndIdAndAccepted(String userFriend, String id, Boolean accepted);

    Friend findByUserAndUserFriend(String user, String userFriend);

    List<Friend> findAllByUser(String user);

    List<Friend> findAllByUserAndAccepted(String user, Boolean accepted);

}
