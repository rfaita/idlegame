package com.idle.game.helper.cb;

import com.idle.game.helper.*;
import static com.idle.game.constant.CacheConstants.HERO_FIND_BY_ID;
import com.idle.game.model.Hero;
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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@Service
public class HeroCircuitBreakerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.hero}")
    private String urlHero;

    @HystrixCommand(fallbackMethod = "getHeroCacheById")
    public Hero getHeroById(String id, String token) {

        URI uri = URI.create(urlHero + "/" + id);
        try {
            ResponseEntity<Envelope<Hero>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Hero>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Hero> data = ret.getBody();
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

    public Hero getHeroCacheById(String id, String token) {
        Hero ret = (Hero) redisTemplate.boundValueOps(HERO_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

}
