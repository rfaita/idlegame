package com.idle.game.server.repository;

import com.idle.game.model.Friend;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface FriendRepository extends MongoRepository<Friend, String> {

    Friend findByUserIdAndId(String user, String id);

    Friend findByUserIdAndIdAndAcceptedAndReverse(String user, String id, Boolean accepted, Boolean reverse);

    Friend findByUserIdAndFriendUserId(String user, String userFriend);

    List<Friend> findAllByUserId(String user);

}
