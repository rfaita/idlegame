package com.idle.game.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 *
 * @author rafael
 */
@Configuration
public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                // users cannot send to these broker destinations, only the application can
                .simpMessageDestMatchers("/topic/**", "/queue/**").denyAll()
                .simpDestMatchers("/chat/**").authenticated()
                .simpSubscribeDestMatchers("/queue/**").access("@webSocketSecurity.checkUser(authentication, message)")
                .simpSubscribeDestMatchers("/topic/**").access("@webSocketSecurity.checkUserChatRoom(authentication, message)")
                .anyMessage().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        //disable CSRF for websockets for now...
        return true;
    }
}
