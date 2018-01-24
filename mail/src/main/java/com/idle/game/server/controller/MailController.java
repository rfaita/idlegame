package com.idle.game.server.controller;

import com.idle.game.constant.SystemConstants;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.mongo.Mail;
import com.idle.game.model.mongo.Reward;
import com.idle.game.server.service.MailService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rafael
 */
@Controller
public class MailController {

    @Autowired
    private ManualTokenHelper manualTokenHelper;
    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private MailService mailService;

    @RequestMapping(path = "/loggedUser", method = RequestMethod.GET)
    @ResponseBody
    public String getUser() throws Exception {
        return tokenHelper.getUser();
    }

    @RequestMapping(path = "/collectReward/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Reward collectReward(@PathVariable("id") String id) throws Exception {
        return mailService.collectReward(tokenHelper.getUser(), id);
    }

    @MessageMapping("/sendPrivateMailSystem/{user}")
    public void sendPrivateMailSystem(@DestinationVariable("user") String user, @Payload Mail mail) throws Exception {

        mail.setFromUser(SystemConstants.SYSTEM_USER);
        mail.setFromNickName(SystemConstants.SYSTEM_USER);
        mail.setToUser(user);
        mail.setToNickName(null);
        mail.setFromAdmin(Boolean.TRUE);

        mailService.sendPrivateMail(mail);
    }

    @MessageMapping("/sendPrivateMail/{user}")
    public void sendPrivateMail(@DestinationVariable("user") String user, Principal principal, @Payload Mail mail) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        mail.setFromUser(manualTokenHelper.getUser());
        mail.setFromNickName(manualTokenHelper.getNickName());
        mail.setToUser(user);
        mail.setToNickName(null);
        mail.setFromAdmin(Boolean.FALSE);
        mail.setReward(null);

        mailService.sendPrivateMail(mail);
    }

    @MessageMapping("/deletePrivateMail/{id}")
    public void deletePrivateMail(@DestinationVariable("id") String id, Principal principal) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        mailService.deletePrivateMail(manualTokenHelper.getUser(), id);
    }

    @MessageMapping("/markAsReadPrivateMail/{id}")
    public void markAsReadPrivateMail(@DestinationVariable("id") String id, Principal principal) throws Exception {
        manualTokenHelper.createAccessToken(principal);

        mailService.markAsReadPrivateMail(manualTokenHelper.getUser(), id);
    }

    @SubscribeMapping("/mail.old.messages")
    public List<Mail> findAllByToUser(Principal principal) {
        manualTokenHelper.createAccessToken(principal);

        return mailService.findAllByToUser(manualTokenHelper.getUser());
    }

}
