package com.idle.game.server.consumer;

import com.idle.game.model.Mail;
import com.idle.game.server.service.MailService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class SendMailConsumer {

    @Autowired
    private MailService mailService;

    @RabbitListener(queues = "${idle.config.mail.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void sendMail(final Mail mail) throws Exception {
        try {
            mailService.sendPrivateMail(mail);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
