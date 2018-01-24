package com.idle.game.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 *
 * @author rafael
 */
@Configuration
public class WebSockerSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                // users cannot send to these broker destinations, only the application can
                .simpDestMatchers("/mail/sendPrivateMailSystem/**").hasRole("ADMIN").anyMessage().permitAll()
                .simpDestMatchers("/mail/**").authenticated()
                .simpMessageDestMatchers("/topic/**", "/queue/**").denyAll()
                .simpSubscribeDestMatchers("/queue/**").access("@webSocketSecurity.checkUser(authentication, message)")
                .simpSubscribeDestMatchers("/topic/public").permitAll()
                .anyMessage().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        //disable CSRF for websockets for now...
        return true;
    }
}
