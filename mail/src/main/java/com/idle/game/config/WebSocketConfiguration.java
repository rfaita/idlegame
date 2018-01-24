package com.idle.game.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author rafael
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Value("${idle.config.mail.relay.hostname}")
    private String hostRelay;
    @Value("${idle.config.mail.relay.port}")
    private Integer portRelay;
    @Value("${idle.config.mail.relay.username}")
    private String userRelay;
    @Value("${idle.config.mail.relay.password}")
    private String passRelay;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/mail");
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
