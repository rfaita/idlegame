package com.idle.game.helper;

import com.idle.game.helper.cb.MailCircuitBreakerService;
import com.idle.game.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class MailHelper {

    @Autowired
    private MailCircuitBreakerService mailHelperCircuitBreaker;

    @Autowired
    private TokenHelper tokenHelper;

    public void sendPrivateMail(Mail mail) {
        mail.setFromUser(tokenHelper.getSubject());
        mail.setFromNickName(tokenHelper.getNickName());
        mail.setFromAdmin(Boolean.FALSE);
        mail.setReward(null);
        mailHelperCircuitBreaker.sendPrivateMail(mail, tokenHelper.getToken());
    }

}
