package com.idle.game.helper;

import static com.idle.game.constant.URIConstants.GUILD_MEMBER__CREATE_ADMIN;
import com.idle.game.server.dto.Envelope;
import com.idle.game.util.EnvelopeUtil;
import java.net.URI;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@Service
public class GuildMemberHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.guildMember}")
    private String urlGuildMember;

    public void createAdmin() {

        URI uri = URI.create(urlGuildMember + "/" + GUILD_MEMBER__CREATE_ADMIN);
        try {
            restTemplate.exchange(uri,
                    HttpMethod.POST,
                    new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                    new ParameterizedTypeReference<Envelope<Void>>() {
            });

        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

}
