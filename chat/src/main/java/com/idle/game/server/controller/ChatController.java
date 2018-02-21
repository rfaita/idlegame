package com.idle.game.server.controller;

import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.ChatJoined;
import com.idle.game.model.mongo.ChatRoomUser;
import com.idle.game.model.mongo.Message;
import com.idle.game.model.mongo.Player;
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

    @Autowired
    private PlayerHelper playerHelper;

    @MessageMapping("/sendChatGlobalMessage")
    public void sendChatGlobalMessage(Principal principal, @Payload Message message) throws Exception {
        this.sendChatMessage("global", principal, message);
    }

    @MessageMapping("/sendChatMessage/{chatRoom}")
    public void sendChatMessage(@DestinationVariable("chatRoom") String chatRoom, Principal principal, @Payload Message message) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        message.setChatRoom(chatRoom);
        message.setFromUser(manualTokenHelper.getSubject());
        message.setFromNickName(manualTokenHelper.getNickName());
        message.setFromEmail(manualTokenHelper.getEmail());
        message.setToUser(null);
        message.setToNickName(null);
        message.setFromAdmin(Boolean.FALSE);

        if (!chatJoinedService.userCanJoinToChatRoom(manualTokenHelper.getSubject(), chatRoom)) {
            throw new ValidationException("chat.room.not.joined");
        }

        messageService.sendChatMessage(message);
    }

    @MessageMapping("/sendPrivateMessage/{user}")
    public void sendPrivateMessage(@DestinationVariable("user") String user, Principal principal, @Payload Message message) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        Player player = playerHelper.getPlayerByLinkedUser(user, manualTokenHelper.getToken());

        if (player == null) {
            throw new ValidationException("player.not.found");
        }

        message.setChatRoom(null);
        message.setFromUser(manualTokenHelper.getSubject());
        message.setFromNickName(manualTokenHelper.getNickName());
        message.setFromEmail(manualTokenHelper.getEmail());
        message.setToUser(user);

        message.setToNickName(player.getName());
        message.setFromAdmin(Boolean.FALSE);

        messageService.sendPrivateMessage(message);
    }

    @SubscribeMapping("/findAllChatsJoined")
    public List<ChatJoined> getChatsJoined() {
        return chatJoinedService.findAllByUser(manualTokenHelper.getSubject());
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

        return messageService.findAllByToUserOrFromUserAndChatRoomIsNull(manualTokenHelper.getSubject());
    }

    @MessageExceptionHandler
    public void handleException(Throwable exception) {

        Envelope<Void> ret = new Envelope<>();
        ret.setError(exception.getMessage());

        messageService.sendPrivateErrorMessage(manualTokenHelper.getSubject(), ret);
    }

}
