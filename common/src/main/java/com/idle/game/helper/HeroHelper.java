package com.idle.game.helper;

import static com.idle.game.constant.CacheConstants.HERO_FIND_BY_ID;
import static com.idle.game.constant.URIConstants.HERO__FIND_ALL_BY_PLAYER;
import static com.idle.game.constant.URIConstants.HERO__ROLL;
import com.idle.game.model.mongo.Hero;
import com.idle.game.server.dto.Envelope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
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
public class HeroHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Value("${idle.url.hero}")
    private String urlHero;

    public Hero getHeroById(String id) {
        return getHeroById(id, tokenHelper.getToken());
    }

    @HystrixCommand(fallbackMethod = "getHeroCacheById")
    public Hero getHeroById(String id, String token) {

        URI uri = URI.create(urlHero + "/" + id);

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
    }

    public Hero getHeroCacheById(String id, String token) {
        Hero ret = (Hero) redisTemplate.boundValueOps(HERO_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    public List<Hero> getHeroesByPlayer(String player) {
        URI uri = URI.create(urlHero + "/" + HERO__FIND_ALL_BY_PLAYER + "/" + player);

        ResponseEntity<Envelope<List<Hero>>> ret = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                new ParameterizedTypeReference<Envelope<List<Hero>>>() {
        });

        if (ret.getStatusCode() == HttpStatus.OK) {
            Envelope<List<Hero>> data = ret.getBody();
            if (data.getErrors() == null || data.getErrors().isEmpty()) {
                return data.getData();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Hero roll(String player) {
        URI uri = URI.create(urlHero + "/" + HERO__ROLL + "/" + player);

        ResponseEntity<Envelope<Hero>> ret = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
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
    }

}
