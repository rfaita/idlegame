package com.idle.game.server.service;

import com.idle.game.model.mongo.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.idle.game.model.mongo.ChatRoom;
import com.idle.game.model.mongo.ChatRoomUser;
import com.idle.game.server.repository.ChatRoomRepository;
import com.idle.game.server.util.Destination;
import javax.validation.ValidationException;

/**
 *
 * @author rafael
 */
@Service
public class ChatRoomService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findById(String chatRoom) {
        return chatRoomRepository.findById(chatRoom);
    }

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom create(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom join(ChatRoomUser joiningUser, String chatRoomId) {
        ChatRoom chatRoom = findById(chatRoomId);

        if (chatRoom == null) {
            throw new ValidationException("chat.room.not.found");
        }

        if (chatRoom.getConnectedUsers().contains(joiningUser)) {
            throw new ValidationException("chat.room.already.join");
        }

        chatRoom.addUser(joiningUser);
        chatRoomRepository.save(chatRoom);

        updateConnectedUsersViaWebSocket(chatRoom);
        return chatRoom;
    }

    public ChatRoom leave(ChatRoomUser leavingUser, String chatRoomId) {
        ChatRoom chatRoom = findById(chatRoomId);

        if (chatRoom == null) {
            throw new ValidationException("chat.room.not.found");
        }

        if (!chatRoom.getConnectedUsers().contains(leavingUser)) {
            throw new ValidationException("chat.room.already.leave");
        }

        chatRoom.removeUser(leavingUser);
        chatRoomRepository.save(chatRoom);

        updateConnectedUsersViaWebSocket(chatRoom);
        return chatRoom;
    }
    
    private void updateConnectedUsersViaWebSocket(ChatRoom chatRoom) {
        webSocketMessagingTemplate.convertAndSend(
                Destination.connectedUsers(chatRoom.getId()),
                chatRoom.getConnectedUsers());
    }

}
