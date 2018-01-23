package com.idle.game.config;

import com.idle.game.model.mongo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Value("${idle.config.chat.relay.hostname}")
    private String hostRelay;
    @Value("${idle.config.chat.relay.port}")
    private Integer portRelay;
    @Value("${idle.config.chat.relay.username}")
    private String userRelay;
    @Value("${idle.config.chat.relay.password}")
    private String passRelay;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/chat");
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

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 10000)
    public void ping() {
        Message m = new Message();
        m.setText("PING");
        m.setFromUser("SYSTEM");
        m.setFromAdmin(Boolean.TRUE);
        simpMessagingTemplate.convertAndSend("/topic/ping", m);
    }

}
