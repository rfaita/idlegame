package com.idle.game.server.controller;

import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.model.mongo.ChatJoined;
import com.idle.game.model.mongo.ChatRoomUser;
import com.idle.game.model.mongo.Message;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.service.ChatJoinedService;
import com.idle.game.server.service.ChatRoomService;
import com.idle.game.server.service.MessageService;
import java.security.Principal;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author rafael
 */
@Controller
public class ChatController {

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatJoinedService chatJoinedService;

    @MessageMapping("/sendChatGlobalMessage")
    public void sendChatGlobalMessage(Principal principal, @Payload Message message) throws Exception {
        this.sendChatMessage("global", principal, message);
    }

    @MessageMapping("/sendChatMessage/{chatRoom}")
    public void sendChatMessage(@DestinationVariable("chatRoom") String chatRoom, Principal principal, @Payload Message message) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        message.setChatRoom(chatRoom);
        message.setFromUserId(manualTokenHelper.getUserId());
        message.setFromUserNickName(manualTokenHelper.getNickName());
        message.setFromUserEmail(manualTokenHelper.getEmail());
        message.setToUserId(null);
        message.setToUserNickName(null);
        message.setFromAdmin(Boolean.FALSE);

        if (!chatJoinedService.userCanJoinToChatRoom(manualTokenHelper.getUserId(), chatRoom)) {
            throw new ValidationException("chat.room.not.joined");
        }

        messageService.sendChatMessage(message);
    }

    @MessageMapping("/sendPrivateMessage/{user}")
    public void sendPrivateMessage(@DestinationVariable("user") String user, Principal principal, @Payload Message message) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        message.setChatRoom(null);
        message.setFromUserId(manualTokenHelper.getUserId());
        message.setFromUserNickName(manualTokenHelper.getNickName());
        message.setFromUserEmail(manualTokenHelper.getEmail());
        message.setToUserId(user);

        message.setFromAdmin(Boolean.FALSE);

        messageService.sendPrivateMessage(message);
    }

    @SubscribeMapping("/findAllChatsJoined")
    public List<ChatJoined> getChatsJoined() {
        return chatJoinedService.findAllByUser(manualTokenHelper.getUserId());
    }

    @SubscribeMapping("/findAllChatRoomUsers/{chatRoom}")
    public List<ChatRoomUser> getConnectedUsers(@DestinationVariable("chatRoom") String chatRoom) {
        return chatRoomService.findByName(chatRoom).getConnectedUsers();
    }

    @SubscribeMapping("/findAllChatRoomMessages/{chatRoom}")
    public List<Message> findAllByChatRoom(@DestinationVariable("chatRoom") String chatRoom) {
        return messageService.findAllByChatRoom(chatRoom);
    }

    @SubscribeMapping("/findAllOldPrivateMessages")
    public List<Message> findAllByToUserOrFromUserAndChatRoomIsNull(Principal principal) {
        manualTokenHelper.createAccessToken(principal);

        return messageService.findAllByToUserOrFromUserAndChatRoomIsNull(manualTokenHelper.getUserId());
    }

    @MessageExceptionHandler
    public void handleException(Throwable exception) {

        Envelope<Void> ret = new Envelope<>();
        ret.setError(exception.getMessage());

        messageService.sendPrivateErrorMessage(manualTokenHelper.getUserId(), ret);
    }

}
