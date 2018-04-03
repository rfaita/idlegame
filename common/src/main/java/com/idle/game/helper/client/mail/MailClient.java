package com.idle.game.helper.client.mail;

import static com.idle.game.constant.URIConstants.MAIL__SEND_PRIVATE_INTERNAL_MAIL;
import static com.idle.game.constant.URIConstants.MAIL__SEND_PRIVATE_MAIL;
import com.idle.game.model.Mail;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "mail-client", url = "${idle.url.mail}", fallback = MailClientImpl.class)
public interface MailClient {

    @RequestMapping(path = "/" + MAIL__SEND_PRIVATE_MAIL, method = RequestMethod.POST)
    public void sendPrivateMail(@RequestBody Mail mail);

    @RequestMapping(path = "/" + MAIL__SEND_PRIVATE_INTERNAL_MAIL, method = RequestMethod.POST)
    public void sendPrivateInternalMail(@RequestBody Mail mail);

}
