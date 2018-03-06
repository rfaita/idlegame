package com.idle.game.helper;

import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION;
import com.idle.game.model.Formation;
import com.idle.game.server.dto.Envelope;
import com.idle.game.util.EnvelopeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
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
public class FormationHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Autowired
    private TokenHelper tokenHelper;

    @Value("${idle.url.formation}")
    private String urlFormation;

    public Formation getFormationById(String id) {
        return this.getFormationById(id, tokenHelper.getToken());
    }

    @HystrixCommand(fallbackMethod = "getFormationCacheById")
    public Formation getFormationById(String id, String token) {

        URI uri = URI.create(urlFormation + "/" + id);
        try {
            ResponseEntity<Envelope<Formation>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Formation>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Formation> data = ret.getBody();
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

    public Formation getFormationCacheById(String id, String token) {
        Formation ret = (Formation) redisTemplate.boundValueOps(FORMATION_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    public Formation getFormationByPlayerAndFormationAllocation(String id, String formationAllocation) {
        return this.getFormationByPlayerAndFormationAllocation(id, formationAllocation, tokenHelper.getToken());
    }

    @HystrixCommand(fallbackMethod = "getFormationCacheByPlayerAndFormationAllocation")
    public Formation getFormationByPlayerAndFormationAllocation(String id, String formationAllocation, String token) {

        URI uri = URI.create(urlFormation + "/" + id + "/" + formationAllocation);
        try {
            ResponseEntity<Envelope<Formation>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Formation>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Formation> data = ret.getBody();
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

    public Formation getFormationCacheByPlayerAndFormationAllocation(String id, String formationAllocation, String token) {
        Formation ret = (Formation) redisTemplate.boundValueOps(FORMATION_FIND_BY_PLAYER_AND_FORMATION_ALLOCATION + id + formationAllocation).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

}
