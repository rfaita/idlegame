package com.idle.game.server.consumer;

import com.idle.game.model.Resource;
import com.idle.game.server.service.UserResourceService;
import java.util.List;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class UserResourceConsumer {

    @Autowired
    private UserResourceService userResourceService;

    @RabbitListener(queues = "${idle.config.useResource.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void sendMail(final List<Resource> resources) throws Exception {
        try {
            //TODO - userResourceService.useResources(resources);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
