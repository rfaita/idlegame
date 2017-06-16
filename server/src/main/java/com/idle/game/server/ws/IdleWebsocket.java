package com.idle.game.server.ws;

import com.idle.game.server.dto.SocketMessage;
import com.idle.game.server.service.PlayerService;
import com.idle.game.server.util.JsonUtil;
import com.idle.game.server.ws.util.WebsocketSecurityInterceptor;
import com.idle.game.server.ws.util.WebsocketSecurityConfigurator;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author rafael
 */
@ServerEndpoint(value = "/idle/websocket", configurator = WebsocketSecurityConfigurator.class)
@Interceptors({WebsocketSecurityInterceptor.class})
public class IdleWebsocket {

    @Inject
    private PlayerService playerService;

    @OnMessage
    public void message(String message, Session s) throws Exception {

        SocketMessage socketMessage = (SocketMessage) JsonUtil.toObject(message, SocketMessage.class);

        switch (socketMessage.getAction()) {
            case "computeResources": {
                socketMessage.setObject(playerService.computeResources());
            }
        }
        
        s.getBasicRemote().sendText(JsonUtil.toJson(socketMessage));
    }

}
