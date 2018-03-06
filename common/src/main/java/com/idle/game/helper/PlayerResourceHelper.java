package com.idle.game.helper;

import com.idle.game.constant.URIConstants;
import com.idle.game.model.PlayerResource;
import com.idle.game.model.Resource;
import com.idle.game.server.dto.Envelope;
import com.idle.game.util.EnvelopeUtil;
import java.net.URI;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@Service
public class PlayerResourceHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.playerResource}")
    private String urlPlayerResource;

    public PlayerResource useResources(List<Resource> resources) {

        URI uri = URI.create(urlPlayerResource + "/" + URIConstants.PLAYERRESOURCE__USE_RESOURCES);

        try {

            ResponseEntity<Envelope<PlayerResource>> ret = restTemplate.exchange(uri,
                    HttpMethod.POST,
                    new HttpEntity(resources, HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                    new ParameterizedTypeReference<Envelope<PlayerResource>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<PlayerResource> data = ret.getBody();
                if (data.getErrors() == null || data.getErrors().isEmpty()) {
                    return data.getData();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }

    }

    public PlayerResource addResources(List<Resource> resources) {
        return addResources(resources, tokenHelper.getToken());
    }

    public PlayerResource addResources(List<Resource> resources, String token) {

        URI uri = URI.create(urlPlayerResource + "/" + URIConstants.PLAYERRESOURCE__ADD_RESOURCES);

        try {

            ResponseEntity<Envelope<PlayerResource>> ret = restTemplate.exchange(uri,
                    HttpMethod.POST,
                    new HttpEntity(resources, HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<PlayerResource>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<PlayerResource> data = ret.getBody();
                if (data.getErrors() == null || data.getErrors().isEmpty()) {
                    return data.getData();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }

    }
}
