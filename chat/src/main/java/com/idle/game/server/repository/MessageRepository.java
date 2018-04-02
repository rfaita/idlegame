package com.idle.game.server.repository;

import com.idle.game.model.mongo.Message;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    public List<Message> findAllByChatRoomId(String chatRoom);

    public List<Message> findAllByToUserIdOrFromUserIdAndChatRoomIdIsNull(String toUser, String fromUser);

}
