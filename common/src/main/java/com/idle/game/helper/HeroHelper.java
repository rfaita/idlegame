package com.idle.game.helper;

import static com.idle.game.constant.CacheConstants.HERO_FIND_BY_ID;
import static com.idle.game.constant.URIConstants.HERO__FIND_ALL_BY_PLAYER;
import static com.idle.game.constant.URIConstants.HERO__ROLL;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.model.Hero;
import com.idle.game.server.dto.Envelope;
import com.idle.game.util.EnvelopeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import java.util.List;
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
public class HeroHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EnvelopeUtil envelopeUtil;

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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
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

    public List<Hero> getHeroesByPlayerId(String player) {
        return getHeroesByPlayerId(player, tokenHelper.getToken());
    }

    public List<Hero> getHeroesByPlayerId(String player, String token) {
        URI uri = URI.create(urlHero + "/" + HERO__FIND_ALL_BY_PLAYER + "/" + player);
        try {
            ResponseEntity<Envelope<List<Hero>>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

    public List<Hero> getHeroesByPlayerIdAndQuality(String player, HeroQuality quality) {
        return getHeroesByPlayerId(player, tokenHelper.getToken());
    }

    public List<Hero> getHeroesByPlayerIdAndQuality(String player, HeroQuality quality, String token) {
        URI uri = URI.create(urlHero + "/" + HERO__FIND_ALL_BY_PLAYER + "/" + player + "/" + quality.toString());
        try {
            ResponseEntity<Envelope<List<Hero>>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

    public Hero roll(String lootRollId) {
        URI uri = URI.create(urlHero + "/" + HERO__ROLL + "/" + lootRollId);
        try {
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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

}
