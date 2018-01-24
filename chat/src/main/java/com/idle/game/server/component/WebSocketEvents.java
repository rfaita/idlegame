package com.idle.game.server.component;

import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.model.mongo.ChatJoined;
import com.idle.game.model.mongo.ChatRoomUser;
import com.idle.game.model.mongo.Message;
import com.idle.game.server.service.ChatJoinedService;
import com.idle.game.server.service.ChatRoomService;
import com.idle.game.server.service.MessageService;
import static com.idle.game.server.util.SystemMessage.JOIN;
import static com.idle.game.server.util.SystemMessage.LEAVE;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 *
 * @author rafael
 */
@Component
public class WebSocketEvents {

    @Autowired
    private ChatJoinedService chatJoinedService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ManualTokenHelper manualTokenHelper;

    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {

        manualTokenHelper.createAccessToken(event.getUser());

        List<ChatJoined> chatsJoined = chatJoinedService.findAllByUser(manualTokenHelper.getUser());

        chatsJoined.forEach((chatJoined) -> {
            ChatRoomUser cru = new ChatRoomUser(manualTokenHelper.getUser(), manualTokenHelper.getNickName());
            chatRoomService.join(cru, chatJoined.getChatRoom());

            Message message = new Message();
            message.setChatRoom(chatJoined.getChatRoom());
            message.setFromUser(manualTokenHelper.getUser());
            message.setFromNickName(manualTokenHelper.getNickName());
            message.setText(JOIN.getMessage());

            messageService.sendChatMessage(message);

        });

    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {

        manualTokenHelper.createAccessToken(event.getUser());

        List<ChatJoined> chatsJoined = chatJoinedService.findAllByUser(manualTokenHelper.getUser());

        chatsJoined.forEach((chatLeaved) -> {
            ChatRoomUser cru = new ChatRoomUser(manualTokenHelper.getUser(), manualTokenHelper.getNickName());
            chatRoomService.leave(cru, chatLeaved.getChatRoom());

            Message message = new Message();
            message.setChatRoom(chatLeaved.getChatRoom());
            message.setFromUser(manualTokenHelper.getUser());
            message.setFromNickName(manualTokenHelper.getNickName());
            message.setText(LEAVE.getMessage());

            messageService.sendChatMessage(message);

        });

    }
}
