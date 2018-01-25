package com.idle.game.helper;

import static com.idle.game.constant.URIConstants.MAIL__SEND_PRIVATE_MAIL;
import com.idle.game.model.mongo.Mail;
import com.idle.game.model.mongo.Player;
import com.idle.game.server.dto.Envelope;
import java.net.URI;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@Service
public class MailHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Value("${idle.url.mail}")
    private String urlMail;

    public void sendPrivateMail(Mail mail) {

        URI uri = URI.create(urlMail + "/" + MAIL__SEND_PRIVATE_MAIL);

        ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                HttpMethod.POST,
                new HttpEntity(mail, HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                new ParameterizedTypeReference<Envelope<Player>>() {
        });

        if (ret.getStatusCode() != HttpStatus.OK) {
            throw new ValidationException("mail.not.send");
        }
    }

}
