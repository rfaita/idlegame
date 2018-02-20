package com.idle.game.server.controller;

import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.mongo.ChatJoined;
import com.idle.game.model.mongo.ChatRoom;
import com.idle.game.model.mongo.ChatRoomUser;
import com.idle.game.model.mongo.Message;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.service.ChatJoinedService;
import com.idle.game.server.service.ChatRoomService;
import com.idle.game.server.service.MessageService;
import static com.idle.game.server.util.SystemMessage.JOIN;
import static com.idle.game.server.util.SystemMessage.LEAVE;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rafael
 */
@Controller
public class ChatController {

    @Autowired
    private ManualTokenHelper manualTokenHelper;
    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatJoinedService chatJoinedService;

    @RequestMapping(path = "/loggedUser", method = RequestMethod.GET)
    @ResponseBody
    public String getUser() throws Exception {
        return tokenHelper.getUser();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ChatRoom create(@RequestBody ChatRoom chatRoom) throws Exception {
        return chatRoomService.create(chatRoom);
    }

    @MessageMapping("/join/{chatRoom}")
    public void joinRoom(@DestinationVariable("chatRoom") String chatRoom, Principal principal) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        ChatRoomUser cru = new ChatRoomUser(manualTokenHelper.getUser(), manualTokenHelper.getNickName());

        chatRoomService.join(cru, chatRoom);

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setFromUser(manualTokenHelper.getUser());
        message.setFromNickName(manualTokenHelper.getNickName());
        message.setText(JOIN.getMessage());

        messageService.sendChatMessage(message);

        ChatJoined chatJoined = new ChatJoined();
        chatJoined.setUser(manualTokenHelper.getUser());
        chatJoined.setChatRoom(chatRoom);

        chatJoinedService.save(chatJoined);

    }

    @MessageMapping("/leave/{chatRoom}")
    public void leaveRoom(@DestinationVariable("chatRoom") String chatRoom, Principal principal) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        ChatRoomUser cru = new ChatRoomUser(manualTokenHelper.getUser(), manualTokenHelper.getNickName());

        chatRoomService.leave(cru, chatRoom);

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setFromUser(manualTokenHelper.getUser());
        message.setFromNickName(manualTokenHelper.getNickName());
        message.setText(LEAVE.getMessage());

        messageService.sendChatMessage(message);

        chatJoinedService.delete(manualTokenHelper.getUser(), chatRoom);
    }

    @MessageMapping("/sendChatGlobalMessage")
    public void sendChatGlobalMessage(Principal principal, @Payload Message message) throws Exception {
        this.sendChatMessage("global", principal, message);
    }

    @MessageMapping("/sendChatMessage/{chatRoom}")
    public void sendChatMessage(@DestinationVariable("chatRoom") String chatRoom, Principal principal, @Payload Message message) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        message.setChatRoom(chatRoom);
        message.setFromUser(manualTokenHelper.getUser());
        message.setFromNickName(manualTokenHelper.getNickName());
        message.setToUser(null);
        message.setToNickName(null);
        message.setFromAdmin(Boolean.FALSE);

        if (!chatJoinedService.userCanJoinToChatRoom(manualTokenHelper.getUser(), chatRoom)) {
            throw new ValidationException("chat.room.not.joined");
        }

        messageService.sendChatMessage(message);
    }

    @MessageMapping("/sendPrivateMessage/{user}")
    public void sendPrivateMessage(@DestinationVariable("user") String user, Principal principal, @Payload Message message) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        message.setChatRoom(null);
        message.setFromUser(manualTokenHelper.getUser());
        message.setFromNickName(manualTokenHelper.getNickName());
        message.setToUser(user);
        message.setToNickName(null);
        message.setFromAdmin(Boolean.FALSE);

        messageService.sendPrivateMessage(message);
    }

    @SubscribeMapping("/findAllChatsJoined")
    public List<ChatJoined> getChatsJoined() {
        return chatJoinedService.findAllByUser(manualTokenHelper.getUser());
    }

    @SubscribeMapping("/findAllChatRoomUsers/{chatRoom}")
    public List<ChatRoomUser> getConnectedUsers(@DestinationVariable("chatRoom") String chatRoom) {
        return chatRoomService.findById(chatRoom).getConnectedUsers();
    }

    @SubscribeMapping("/findAllChatRoomMessages/{chatRoom}")
    public List<Message> findAllByChatRoom(@DestinationVariable("chatRoom") String chatRoom) {
        return messageService.findAllByChatRoom(chatRoom);
    }

    @SubscribeMapping("/findAllOldPrivateMessages")
    public List<Message> findAllByToUser(Principal principal) {
        manualTokenHelper.createAccessToken(principal);

        return messageService.findAllByToUser(manualTokenHelper.getUser());
    }

    @MessageExceptionHandler
    public void handleException(Throwable exception) {
        
        Envelope<Void> ret = new Envelope<>();
        ret.setError(exception.getMessage());
        
        messageService.sendPrivateErrorMessage(manualTokenHelper.getUser(), ret);
    }

}
