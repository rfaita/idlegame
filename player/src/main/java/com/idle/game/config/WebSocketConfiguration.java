package com.idle.game.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author rafael
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Value("${idle.config.relay.hostname}")
    private String hostRelay;
    @Value("${idle.config.relay.port}")
    private Integer portRelay;
    @Value("${idle.config.relay.username}")
    private String userRelay;
    @Value("${idle.config.relay.password}")
    private String passRelay;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/player");
        registry.enableStompBrokerRelay("/queue/", "/topic/")
                .setSystemLogin(userRelay)
                .setSystemPasscode(passRelay)
                .setClientLogin(userRelay)
                .setClientPasscode(passRelay)
                .setRelayHost(hostRelay)
                .setRelayPort(portRelay);

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*");

    }

}
