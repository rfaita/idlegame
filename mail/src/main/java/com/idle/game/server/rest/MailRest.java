/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.MAIL__SEND_PRIVATE_MAIL;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.mongo.Mail;
import com.idle.game.model.mongo.Reward;
import com.idle.game.server.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(path = "/" + MAIL__SEND_PRIVATE_MAIL, method = RequestMethod.POST)
    public void sendPrivateMail(@RequestBody Mail mail) throws Exception {

        mail.setFromUser(tokenHelper.getUser());
        mail.setFromNickName(tokenHelper.getNickName());
        mail.setFromAdmin(Boolean.FALSE);
        mail.setReward(null);

        mailService.sendPrivateMail(mail);
    }

}
