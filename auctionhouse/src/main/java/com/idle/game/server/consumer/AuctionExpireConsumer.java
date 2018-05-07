package com.idle.game.server.consumer;

import com.idle.game.model.actionhouse.Auction;
import com.idle.game.server.service.AuctionService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class AuctionExpireConsumer {

    @Autowired
    private AuctionService auctionService;

    @RabbitListener(queues = "${idle.config.auction.expireQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void expire(final Auction auction) throws Exception {
        try {
            auctionService.expire(auction.getId());

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
