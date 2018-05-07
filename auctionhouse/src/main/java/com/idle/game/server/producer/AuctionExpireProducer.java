package com.idle.game.server.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.game.model.actionhouse.Auction;
import com.idle.game.util.DateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static org.xnio._private.Messages.msg;

/**
 *
 * @author rafael
 */
@Component
public class AuctionExpireProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${idle.config.auction.exchange}")
    private String auctionExchange;
    @Value("${idle.config.auction.expireQueue}")
    private String expireQueue;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Auction auction) {

        try {
            MessageProperties messageProperties
                    = MessagePropertiesBuilder.newInstance()
                            .setHeader("x-delay", DateUtil.secondsFrom(auction.getExpireAt()) * -1).build();

            Message m
                    = MessageBuilder
                            .withBody(objectMapper.writeValueAsBytes(auction))
                            .andProperties(messageProperties).build();

            rabbitTemplate.convertAndSend(auctionExchange, expireQueue, m);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(AuctionExpireProducer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
