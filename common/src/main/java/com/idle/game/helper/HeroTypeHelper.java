package com.idle.game.helper;

import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_ID;
import com.idle.game.core.type.HeroTypeQuality;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.server.dto.Envelope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.idle.game.constant.URIConstants.HERO__FIND_ALL_BY_HERO_TYPE_QUALITY;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @Autowired
    private TokenHelper tokenHelper;

    @Value("${idle.url.heroType}")
    private String urlHeroType;

    //Have to do that, because @HytrixCommand run in a new thread and that can not access TokenHelper correct
    public HeroType getHeroTypeById(String id) {
        return getHeroTypeById(id, tokenHelper.getToken());
    }
    
    @HystrixCommand(fallbackMethod = "getHeroTypeCacheById")
    public HeroType getHeroTypeById(String id, String token) {

        URI uri = URI.create(urlHeroType + "/" + id);

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
    }

    public HeroType getHeroTypeCacheById(String id) {
        HeroType ret = (HeroType) redisTemplate.boundValueOps(HERO_TYPE_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    public List<HeroType> getHeroTypeByHeroTypeQuality(HeroTypeQuality quality) {

        URI uri = URI.create(urlHeroType + "/" + HERO__FIND_ALL_BY_HERO_TYPE_QUALITY + "/" + quality.toString());

        ResponseEntity<Envelope<List<HeroType>>> ret = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                new ParameterizedTypeReference<Envelope<List<HeroType>>>() {
        });

        if (ret.getStatusCode() == HttpStatus.OK) {
            Envelope<List<HeroType>> data = ret.getBody();
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
