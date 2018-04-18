package com.idle.game.helper.client.resource;

import com.idle.game.model.Resource;
import com.idle.game.model.UserResource;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class UserResourceClientImpl implements UserResourceClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${idle.config.useResource.exchange}")
    private String useResourceExchange;
    @Value("${idle.config.useResource.sendQueue}")
    private String useResourcelQueue;
    @Value("${idle.config.addResource.exchange}")
    private String addResourceExchange;
    @Value("${idle.config.addResource.sendQueue}")
    private String addResourcelQueue;

    
    @Override
    public Envelope<UserResource> useResources(List<Resource> resources) {
        rabbitTemplate.convertAndSend(useResourceExchange, useResourcelQueue, resources);
        return new Envelope();
    }

    @Override
    public Envelope<UserResource> addResources(List<Resource> resources) {
        rabbitTemplate.convertAndSend(addResourceExchange, addResourcelQueue, resources);
        return new Envelope();
    }

}
