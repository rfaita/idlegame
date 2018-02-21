package com.idle.game.server.component;

import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.model.mongo.ChatJoined;
import com.idle.game.model.mongo.ChatRoomUser;
import com.idle.game.server.service.ChatJoinedService;
import com.idle.game.server.service.ChatRoomService;
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
    private ManualTokenHelper manualTokenHelper;

    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {

        manualTokenHelper.createAccessToken(event.getUser());

        List<ChatJoined> chatsJoined = chatJoinedService.findAllByUser(manualTokenHelper.getSubject());

        chatsJoined.forEach((chat) -> {
            ChatRoomUser cru = new ChatRoomUser(manualTokenHelper.getSubject()
                    , manualTokenHelper.getNickName(), manualTokenHelper.getEmail());
            chatRoomService.changeUserStatusToOnline(cru, chat.getChatRoom());
        });

    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {

        manualTokenHelper.createAccessToken(event.getUser());

        List<ChatJoined> chatsJoined = chatJoinedService.findAllByUser(manualTokenHelper.getSubject());

        chatsJoined.forEach((chat) -> {
           ChatRoomUser cru = new ChatRoomUser(manualTokenHelper.getSubject()
                    , manualTokenHelper.getNickName(), manualTokenHelper.getEmail());
            chatRoomService.changeUserStatusToOffline(cru, chat.getChatRoom());

        });

    }
}
