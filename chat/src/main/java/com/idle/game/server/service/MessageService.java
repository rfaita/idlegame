package com.idle.game.server.service;

/**
 *
 * @author rafael
 */
import com.idle.game.model.mongo.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.server.repository.MessageRepository;
import com.idle.game.server.util.Destination;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAllByChatRoom(String chatRoom) {
        return messageRepository.findAllByChatRoom(chatRoom);
    }

    public List<Message> findAllByToUser(String toUser) {
        return messageRepository.findAllByToUser(toUser);
    }

    public void sendChatMessage(Message chatMessage) {
        webSocketMessagingTemplate.convertAndSend(
                Destination.publicMessages(chatMessage.getChatRoom()),
                chatMessage);

        messageRepository.save(chatMessage);
    }

    public void sendPrivateMessage(Message message) {
        webSocketMessagingTemplate.convertAndSend(
                Destination.privateMessages(message.getToUser()),
                message);

        webSocketMessagingTemplate.convertAndSend(
                Destination.privateMessages(message.getFromUser()),
                message);

        messageRepository.save(message);
    }

}
