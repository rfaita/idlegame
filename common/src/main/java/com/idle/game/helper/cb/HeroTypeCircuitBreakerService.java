package com.idle.game.helper.cb;

import com.idle.game.helper.*;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_NAME;
import com.idle.game.constant.URIConstants;
import com.idle.game.model.HeroType;
import com.idle.game.server.dto.Envelope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.idle.game.util.EnvelopeUtil;
import javax.validation.ValidationException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author rafael
 */
@Service
public class HeroTypeCircuitBreakerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.heroType}")
    private String urlHeroType;

    @HystrixCommand(fallbackMethod = "getHeroTypeCacheById")
    public HeroType getHeroTypeById(String id, String token) {

        URI uri = URI.create(urlHeroType + "/" + id);
        try {
            ResponseEntity<Envelope<HeroType>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<HeroType>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<HeroType> data = ret.getBody();
                if (data.getErrors() == null || data.getErrors().isEmpty()) {
                    return data.getData();
                } else {
                    return null;
                }
            } else {
                return null;
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

    public HeroType getHeroTypeCacheById(String id, String token) {
        HeroType ret = (HeroType) redisTemplate.boundValueOps(HERO_TYPE_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    @HystrixCommand(fallbackMethod = "getHeroTypeCacheByName")
    public HeroType getHeroTypeByName(String name, String token) {

        URI uri = URI.create(urlHeroType + "/" + URIConstants.HEROTYPE__FIND_BY_NAME + "/" + name);
        try {
            ResponseEntity<Envelope<HeroType>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<HeroType>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<HeroType> data = ret.getBody();
                if (data.getErrors() == null || data.getErrors().isEmpty()) {
                    return data.getData();
                } else {
                    return null;
                }
            } else {
                return null;
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

    public HeroType getHeroTypeCacheByName(String name, String token) {
        HeroType ret = (HeroType) redisTemplate.boundValueOps(HERO_TYPE_FIND_BY_NAME + name).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

}
