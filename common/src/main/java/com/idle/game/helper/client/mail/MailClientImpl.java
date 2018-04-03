package com.idle.game.helper.client.mail;

import com.idle.game.model.Mail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class MailClientImpl implements MailClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${idle.config.mail.exchange}")
    private String mailExchange;
    @Value("${idle.config.mail.sendQueue}")
    private String sendMailQueue;

    @Override
    public void sendPrivateMail(Mail mail) {
        rabbitTemplate.convertAndSend(mailExchange, sendMailQueue, mail);
    }

    @Override
    public void sendPrivateInternalMail(Mail mail) {
        rabbitTemplate.convertAndSend(mailExchange, sendMailQueue, mail);
    }

}
