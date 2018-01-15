package com.idle.game.helper;

import com.idle.game.model.mongo.Hero;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.dto.HeroEnvelope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Value("${idle.url.hero}")
    private String urlHero;

    @HystrixCommand(fallbackMethod = "getHeroCacheById")
    public Hero getHeroById(String id) {

        URI uri = URI.create(urlHero + "/" + id);

        Envelope<Hero> ret = restTemplate.getForObject(uri, HeroEnvelope.class);

        if (ret.getErrors() == null || ret.getErrors().isEmpty()) {
            return ret.getData();
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
