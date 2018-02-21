package com.idle.game.server.repository;

import com.idle.game.model.mongo.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    ChatRoom findById(String id);

    ChatRoom findByName(String name);
}
