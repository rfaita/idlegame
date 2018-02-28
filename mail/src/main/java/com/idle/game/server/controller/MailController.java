package com.idle.game.server.controller;

import com.idle.game.constant.SystemConstants;
import static com.idle.game.constant.URIConstants.MAIL__SEND_PRIVATE_MAIL;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.model.mongo.Mail;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.service.MailService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author rafael
 */
@Controller
public class MailController {

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    @Autowired
    private MailService mailService;

    @MessageMapping("/sendPrivateMailSystem")
    public void sendPrivateMailSystem(@Payload Mail mail) throws Exception {

        mail.setFromUser(SystemConstants.SYSTEM_USER);
        mail.setFromNickName(SystemConstants.SYSTEM_USER);
        mail.setFromAdmin(Boolean.TRUE);

        mailService.sendPrivateMail(mail);
    }

    @MessageMapping("/" + MAIL__SEND_PRIVATE_MAIL)
    public void sendPrivateMail(Principal principal, @Payload Mail mail) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        mail.setFromUser(manualTokenHelper.getSubject());
        mail.setFromNickName(manualTokenHelper.getNickName());
        mail.setFromAdmin(Boolean.FALSE);
        mail.setReward(null);

        mailService.sendPrivateMail(mail);
    }

    @MessageMapping("/deletePrivateMail/{id}")
    public void deletePrivateMail(@DestinationVariable("id") String id, Principal principal) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        mailService.deletePrivateMail(manualTokenHelper.getSubject(), id);
    }

    @MessageMapping("/markAsReadPrivateMail/{id}")
    public void markAsReadPrivateMail(@DestinationVariable("id") String id, Principal principal) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        mailService.markAsReadPrivateMail(manualTokenHelper.getSubject(), id);
    }
    @MessageMapping("/collectReward/{id}")
    public void collectReward(@DestinationVariable("id") String id, Principal principal) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        mailService.collectReward(manualTokenHelper.getSubject(), id, manualTokenHelper.getToken());
    }

    @SubscribeMapping("/findAllOldMail")
    public List<Mail> findAllByToUser(Principal principal) {
        manualTokenHelper.createAccessToken(principal);

        return mailService.findAllByToUser(manualTokenHelper.getSubject());
    }
    
    @MessageExceptionHandler
    public void handleException(Throwable exception) {

        Envelope<Void> ret = new Envelope<>();
        ret.setError(exception.getMessage());

        mailService.sendPrivateMailError(manualTokenHelper.getSubject(), ret);
    }

}
