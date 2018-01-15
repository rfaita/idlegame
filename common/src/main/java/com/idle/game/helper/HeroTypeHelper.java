package com.idle.game.helper;

import static com.idle.game.constant.URIConstants.FIND_ALL_BY_HERO_TYPE_QUALITY;
import com.idle.game.core.type.HeroTypeQuality;
import com.idle.game.model.mongo.Hero;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.dto.HeroTypeEnvelope;
import com.idle.game.server.dto.ListHeroTypeEnvelope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import java.util.List;
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
public class HeroTypeHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Value("${idle.url.heroType}")
    private String urlHeroType;

    @HystrixCommand(fallbackMethod = "getHeroTypeCacheById")
    public HeroType getHeroTypeById(String id) {

        URI uri = URI.create(urlHeroType + "/" + id);

        Envelope<HeroType> ret = restTemplate.getForObject(uri, HeroTypeEnvelope.class);

        if (ret.getErrors() == null || ret.getErrors().isEmpty()) {
            return ret.getData();
        } else {
            return null;
        }
    }

    public HeroType getHeroTypeCacheById(String id) {
        HeroType ret = (HeroType) redisTemplate.boundValueOps(id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    public List<HeroType> getHeroTypeByHeroTypeQuality(HeroTypeQuality quality) {

        URI uri = URI.create(urlHeroType + "/" + FIND_ALL_BY_HERO_TYPE_QUALITY + "/" + quality.toString());

        Envelope<List<HeroType>> ret = restTemplate.getForObject(uri, ListHeroTypeEnvelope.class);

        if (ret.getErrors() == null || ret.getErrors().isEmpty()) {
            return ret.getData();
        } else {
            return null;
        }
    }

}
