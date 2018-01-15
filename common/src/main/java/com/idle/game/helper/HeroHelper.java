package com.idle.game.helper;

import com.idle.game.model.mongo.Hero;
import com.idle.game.server.dto.Envelope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import org.keycloak.representations.AccessToken;
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
    private AccessToken accessToken;

    @Value("${idle.url.hero}")
    private String urlHero;

    @HystrixCommand(fallbackMethod = "getHeroCacheById")
    public Hero getHeroById(String id) {

        URI uri = URI.create(urlHero + "/" + id);

        ResponseEntity<Envelope<Hero>> ret = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity(TokenHelper.getAuthHeaders(accessToken.getAccessTokenHash())),
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

    public Hero getHeroCacheById(String id) {
        Hero ret = (Hero) redisTemplate.boundValueOps(id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

}
