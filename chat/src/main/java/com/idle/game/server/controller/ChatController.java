package com.idle.game.server.controller;

import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.model.mongo.ChatJoined;
import com.idle.game.model.mongo.ChatRoom;
import com.idle.game.model.mongo.ChatRoomUser;
import com.idle.game.model.mongo.Message;
import com.idle.game.server.service.ChatJoinedService;
import com.idle.game.server.service.ChatRoomService;
import com.idle.game.server.service.MessageService;
import java.security.Principal;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.annotation.Secured;
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
    private ManualTokenHelper tokenHelper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatService;

    @Autowired
    private ChatJoinedService chatJoinedService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ChatRoom create(@RequestBody ChatRoom chatRoom) throws Exception {
        return chatService.create(chatRoom);
    }

    @MessageMapping("/join/{chatRoom}")
    public void joinRoom(@DestinationVariable("chatRoom") String chatRoom, Principal principal) throws Exception {
        tokenHelper.createAccessToken(principal);

        ChatRoomUser cru = new ChatRoomUser(tokenHelper.getUser(), tokenHelper.getNickName());

        chatService.join(cru, chatRoom);

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setFromUser(tokenHelper.getUser());
        message.setFromNickName(tokenHelper.getNickName());
        message.setText("joined to channel.");

        messageService.sendChatMessage(message);

        ChatJoined chatJoined = new ChatJoined();
        chatJoined.setUser(tokenHelper.getUser());
        chatJoined.setChatRoom(chatRoom);

        chatJoinedService.save(chatJoined);

    }

    @MessageMapping("/leave/{chatRoom}")
    public void leaveRoom(@DestinationVariable("chatRoom") String chatRoom, Principal principal) throws Exception {
        tokenHelper.createAccessToken(principal);

        ChatRoomUser cru = new ChatRoomUser(tokenHelper.getUser(), tokenHelper.getNickName());

        chatService.leave(cru, chatRoom);

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setFromUser(tokenHelper.getUser());
        message.setFromNickName(tokenHelper.getNickName());
        message.setText("leaving to channel.");

        messageService.sendChatMessage(message);

        chatJoinedService.delete(tokenHelper.getUser(), chatRoom);
    }

    @MessageMapping("/sendChatGlobalMessage")
    public void sendChatGlobalMessage(Principal principal, @Payload Message message) throws Exception {
        this.sendChatMessage("global", principal, message);
    }

    @MessageMapping("/sendChatMessage/{chatRoom}")
    public void sendChatMessage(@DestinationVariable("chatRoom") String chatRoom, Principal principal, @Payload Message message) throws Exception {
        tokenHelper.createAccessToken(principal);

        message.setChatRoom(chatRoom);
        message.setFromUser(tokenHelper.getUser());
        message.setFromNickName(tokenHelper.getNickName());
        message.setToUser(null);
        message.setToNickName(null);
        message.setFromAdmin(Boolean.FALSE);

        if (!chatJoinedService.userCanJoinToChatRoom(tokenHelper.getUser(), chatRoom)) {
            throw new ValidationException("chat.room.not.joined");
        }

        messageService.sendChatMessage(message);
    }

    @MessageMapping("/sendPrivateMessage/{user}")
    public void sendPrivateMessage(@DestinationVariable("user") String user, Principal principal, @Payload Message message) throws Exception {
        tokenHelper.createAccessToken(principal);

        message.setChatRoom(null);
        message.setFromUser(tokenHelper.getUser());
        message.setFromNickName(tokenHelper.getNickName());
        message.setToUser(user);
        message.setToNickName(null);
        message.setFromAdmin(Boolean.FALSE);

        messageService.sendPrivateMessage(message);
    }

    @SubscribeMapping("/chats")
    public List<ChatJoined> getChatsJoined() {
        return chatJoinedService.findAllByUser(tokenHelper.getUser());
    }

    @SubscribeMapping("/chat.users/{chatRoom}")
    public List<ChatRoomUser> getConnectedUsers(@DestinationVariable("chatRoom") String chatRoom) {
        return chatService.findById(chatRoom).getConnectedUsers();
    }

    @SubscribeMapping("/chat.old.messages/{chatRoom}")
    public List<Message> findAllByChatRoom(@DestinationVariable("chatRoom") String chatRoom) {
        return messageService.findAllByChatRoom(chatRoom);
    }

    @SubscribeMapping("/pm.old.messages")
    public List<Message> findAllByToUser(Principal principal) {
        tokenHelper.createAccessToken(principal);

        return messageService.findAllByToUser(tokenHelper.getUser());
    }

}
