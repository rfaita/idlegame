package com.idle.game.server.service;

import com.idle.game.model.mongo.ChatJoined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.server.repository.ChatJoinedRepository;
import java.util.List;

/**
 *
 * @author rafael
 */
@Service
public class ChatJoinedService {

    @Autowired
    private ChatJoinedRepository chatJoinedRepository;

    public boolean userCanJoinToChatRoom(String user, String chatRoom) {
        return chatJoinedRepository.findByUserIdAndChatRoomId(user, chatRoom) != null;
    }

    public ChatJoined save(ChatJoined chatJoined) {
        return chatJoinedRepository.save(chatJoined);
    }

    public void delete(String user, String chatRoom) {
        chatJoinedRepository.delete(findByUserIdAndChatRoomId(user, chatRoom));
    }

    public ChatJoined findByUserIdAndChatRoomId(String user, String chatRoom) {
        return chatJoinedRepository.findByUserIdAndChatRoomId(user, chatRoom);
    }

    public List<ChatJoined> findAllByUserId(String user) {
        return chatJoinedRepository.findAllByUserId(user);
    }
}
