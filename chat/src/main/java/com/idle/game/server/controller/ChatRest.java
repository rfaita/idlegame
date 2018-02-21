package com.idle.game.server.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael
 */
@RestController
@RequestMapping("/chat")
public class ChatRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatJoinedService chatJoinedService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Envelope<ChatRoom> create(@RequestBody ChatRoom chatRoom) throws Exception {
        chatRoom.removeAllUsers();
        return new Envelope<>(chatRoomService.create(chatRoom));
    }

    @RequestMapping(path = "/join/{chatRoom}", method = RequestMethod.POST)
    @ResponseBody
    public Envelope<Void> joinRoom(@PathVariable("chatRoom") String chatRoom) throws Exception {

        ChatRoomUser cru = new ChatRoomUser(tokenHelper.getSubject(),
                tokenHelper.getNickName(), tokenHelper.getEmail());

        chatRoomService.join(cru, chatRoom);

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setFromUser(tokenHelper.getSubject());
        message.setFromNickName(tokenHelper.getNickName());
        message.setFromEmail(tokenHelper.getEmail());
        message.setText(JOIN.getMessage());

        messageService.sendChatMessage(message);

        ChatJoined chatJoined = new ChatJoined();
        chatJoined.setUser(tokenHelper.getSubject());
        chatJoined.setChatRoom(chatRoom);

        chatJoinedService.save(chatJoined);
        
        return new Envelope<>();

    }

    @RequestMapping(path = "/leave/{chatRoom}", method = RequestMethod.DELETE)
    @ResponseBody
    public Envelope<Void> leaveRoom(@PathVariable("chatRoom") String chatRoom) throws Exception {

        ChatRoomUser cru = new ChatRoomUser(tokenHelper.getSubject(),
                tokenHelper.getNickName(), tokenHelper.getEmail());

        chatRoomService.leave(cru, chatRoom);

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setFromUser(tokenHelper.getSubject());
        message.setFromNickName(tokenHelper.getNickName());
        message.setFromEmail(tokenHelper.getEmail());
        message.setText(LEAVE.getMessage());

        messageService.sendChatMessage(message);

        chatJoinedService.delete(tokenHelper.getSubject(), chatRoom);
        
        return new Envelope<>();
    }

}
