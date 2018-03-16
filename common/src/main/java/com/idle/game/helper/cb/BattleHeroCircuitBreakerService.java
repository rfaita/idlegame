package com.idle.game.helper.cb;

import com.idle.game.helper.*;
import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import com.idle.game.core.hero.BattleHero;
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
public class BattleHeroCircuitBreakerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.battleHero}")
    private String urlBattleHero;

    @HystrixCommand(fallbackMethod = "getBattleHeroCacheById")
    public BattleHero getBattleHeroById(String id, String token) {

        URI uri = URI.create(urlBattleHero + "/" + id);
        try {
            ResponseEntity<Envelope<BattleHero>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<BattleHero>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<BattleHero> data = ret.getBody();
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

    public BattleHero getBattleHeroCacheById(String id) {
        BattleHero ret = (BattleHero) redisTemplate.boundValueOps(BATTLE_HERO_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

}
