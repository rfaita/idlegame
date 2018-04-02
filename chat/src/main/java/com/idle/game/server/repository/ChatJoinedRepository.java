package com.idle.game.server.repository;

import com.idle.game.model.mongo.ChatJoined;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface ChatJoinedRepository extends MongoRepository<ChatJoined, String> {

    ChatJoined findByUserIdAndChatRoomId(String id, String chatRoom);

    List<ChatJoined> findAllByUserId(String id);
}
