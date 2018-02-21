package com.idle.game.server.service;

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

    public ChatRoom findByName(String chatRoom) {
        return chatRoomRepository.findByName(chatRoom);
    }

    public ChatRoom findById(String chatRoom) {
        return chatRoomRepository.findById(chatRoom);
    }

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom create(ChatRoom chatRoom) {
        ChatRoom chatRoomExist = findByName(chatRoom.getName());

        if (chatRoomExist != null) {
            throw new ValidationException("chat.room.already.created");
        }
        return chatRoomRepository.save(chatRoom);
    }

    public void refresh(String name) {
        ChatRoom chatRoom = findByName(name);

        if (chatRoom == null) {
            throw new ValidationException("chat.room.not.found");
        }

        updateConnectedUsersViaWebSocket(chatRoom);
    }

    public ChatRoom changeUserStatusToOnline(ChatRoomUser onlineUser, String name) {
        return changeUserStatus(onlineUser, name, Boolean.TRUE);
    }

    public ChatRoom changeUserStatusToOffline(ChatRoomUser onlineUser, String name) {
        return changeUserStatus(onlineUser, name, Boolean.FALSE);
    }

    private ChatRoom changeUserStatus(ChatRoomUser onlineUser, String name, Boolean online) {
        ChatRoom chatRoom = findByName(name);

        if (chatRoom == null) {
            throw new ValidationException("chat.room.not.found");
        }

        if (!chatRoom.getConnectedUsers().contains(onlineUser)) {
            throw new ValidationException("chat.room.not.joined");
        }

        chatRoom.removeUser(onlineUser);

        onlineUser.setOnline(online);

        chatRoom.addUser(onlineUser);

        chatRoomRepository.save(chatRoom);

        updateConnectedUsersViaWebSocket(chatRoom);
        return chatRoom;
    }

    public ChatRoom join(ChatRoomUser joiningUser, String name) {
        ChatRoom chatRoom = findByName(name);

        if (chatRoom == null) {
            throw new ValidationException("chat.room.not.found");
        }

        if (chatRoom.getConnectedUsers().contains(joiningUser)) {
            throw new ValidationException("chat.room.already.join");
        }

        joiningUser.setOnline(Boolean.TRUE);

        chatRoom.addUser(joiningUser);
        chatRoom = chatRoomRepository.save(chatRoom);

        updateConnectedUsersViaWebSocket(chatRoom);
        return chatRoom;
    }

    public ChatRoom leave(ChatRoomUser leavingUser, String name) {
        ChatRoom chatRoom = findByName(name);

        if (chatRoom == null) {
            throw new ValidationException("chat.room.not.found");
        }

        if (!chatRoom.getConnectedUsers().contains(leavingUser)) {
            throw new ValidationException("chat.room.already.leave");
        }

        chatRoom.removeUser(leavingUser);
        chatRoom = chatRoomRepository.save(chatRoom);

        updateConnectedUsersViaWebSocket(chatRoom);
        return chatRoom;
    }

    private void updateConnectedUsersViaWebSocket(ChatRoom chatRoom) {
        webSocketMessagingTemplate.convertAndSend(
                Destination.connectedUsers(chatRoom.getName()),
                chatRoom.getConnectedUsers());
    }

}
