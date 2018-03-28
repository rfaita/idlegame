package com.idle.game.server.component;

import com.idle.game.helper.ManualTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class WebSocketSecurity {

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public boolean checkUser(Authentication authentication, Message<?> message) {

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);

        String user = sha.getDestination().replaceAll("\\/queue\\/(.*)#.*", "$1");

        manualTokenHelper.createAccessToken(authentication);

        return manualTokenHelper.getUserId().equalsIgnoreCase(user);
    }

}
