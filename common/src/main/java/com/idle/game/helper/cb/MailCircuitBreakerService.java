package com.idle.game.helper.cb;

import com.idle.game.helper.HeaderUtil;
import static com.idle.game.constant.URIConstants.MAIL__SEND_PRIVATE_MAIL;
import com.idle.game.model.Mail;
import com.idle.game.model.Player;
import com.idle.game.server.dto.Envelope;
import com.idle.game.util.EnvelopeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import javax.validation.ValidationException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@Service
public class MailCircuitBreakerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${idle.config.mail.exchange}")
    private String mailExchange;
    @Value("${idle.config.mail.sendQueue}")
    private String sendMailQueue;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.mail}")
    private String urlMail;

    @HystrixCommand(fallbackMethod = "sendPrivateMailDirect")
    public void sendPrivateMail(Mail mail, String token) {

        URI uri = URI.create(urlMail + "/" + MAIL__SEND_PRIVATE_MAIL);
        try {
            ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                    HttpMethod.POST,
                    new HttpEntity(mail, HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Player>>() {
            });

            if (ret.getStatusCode() != HttpStatus.OK) {
                throw new ValidationException("mail.not.send");
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
                throw new ValidationException((String) ret.getErrors().get(0));
            } else if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                throw new ValidationException("internal.server.error");
            }
            throw new ValidationException("unmapped.server.error");
        } catch (RestClientException e) {
            throw new ValidationException(e);
        }
    }

    public void sendPrivateMailDirect(Mail mail, String token) {
        rabbitTemplate.convertAndSend(mailExchange, sendMailQueue, mail);
    }

}
