package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.MAIL__SEND_PRIVATE_MAIL;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.Mail;
import com.idle.game.server.service.MailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael
 */
@RestController
@RequestMapping("/mail")
public class MailRest {
    
    @Autowired
    private TokenHelper tokenHelper;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${idle.config.mail.exchange}")
    private String mailExchange;
    @Value("${idle.config.mail.sendQueue}")
    private String sendMailQueue;
    
    @RequestMapping(path = "/" + MAIL__SEND_PRIVATE_MAIL, method = RequestMethod.POST)
    public void sendPrivateMail(@RequestBody Mail mail) throws Exception {
        
        mail.setFromUser(tokenHelper.getSubject());
        mail.setFromNickName(tokenHelper.getNickName());
        mail.setFromAdmin(Boolean.FALSE);
        mail.setReward(null);
        
        rabbitTemplate.convertAndSend(mailExchange, sendMailQueue, mail);
    }
    
}
