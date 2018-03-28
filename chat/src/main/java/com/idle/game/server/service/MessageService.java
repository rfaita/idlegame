package com.idle.game.server.service;

import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.User;
import com.idle.game.model.mongo.Message;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.server.repository.MessageRepository;
import com.idle.game.server.util.Destination;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 *
 * @author rafael
 */
@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserClient userClient;

    public List<Message> findAllByChatRoom(String chatRoom) {
        return messageRepository.findAllByChatRoom(chatRoom);
    }

    public List<Message> findAllByToUserOrFromUserAndChatRoomIsNull(String user) {
        return messageRepository.findAllByToUserOrFromUserAndChatRoomIsNull(user, user);
    }

    public void sendChatMessage(Message chatMessage) {
        webSocketMessagingTemplate.convertAndSend(
                Destination.publicMessages(chatMessage.getChatRoom()),
                chatMessage);

        messageRepository.save(chatMessage);
    }

    public void sendPrivateMessage(Message message) {

        User user = userClient.findById(message.getToUserId()).getData();

        message.setToUserNickName(user.getNickName());

        webSocketMessagingTemplate.convertAndSend(
                Destination.privateMessages(message.getToUserId()),
                message);

        webSocketMessagingTemplate.convertAndSend(
                Destination.privateMessages(message.getFromUserId()),
                message);

        messageRepository.save(message);
    }

    public void sendPrivateErrorMessage(String user, Envelope<?> env) {
        webSocketMessagingTemplate.convertAndSend(
                Destination.privateErrorMessages(user), env);

    }

}
