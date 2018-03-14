package com.idle.game.helper;

import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_ID;
import com.idle.game.constant.URIConstants;
import static com.idle.game.constant.URIConstants.HEROTYPE__FIND_ALL_BY_FACTION_AND_QUALITY;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.model.HeroType;
import com.idle.game.server.dto.Envelope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.idle.game.core.hero.type.HeroTypeFaction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static com.idle.game.constant.URIConstants.HEROTYPE__FIND_ALL_BY_QUALITY;
import com.idle.game.util.EnvelopeUtil;
import javax.validation.ValidationException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

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
    private EnvelopeUtil envelopeUtil;

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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
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

    public List<HeroType> getAllHeroType() {
        return getAllHeroType(tokenHelper.getToken());
    }

    public List<HeroType> getAllHeroType(String token) {

        URI uri = URI.create(urlHeroType);

        try {
            ResponseEntity<Envelope<List<HeroType>>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

    public List<HeroType> getHeroTypeByQuality(HeroTypeQuality quality) {
        return getAllHeroType(tokenHelper.getToken());
    }

    public List<HeroType> getHeroTypeByQuality(HeroTypeQuality quality, String token) {

        URI uri = URI.create(urlHeroType + "/" + HEROTYPE__FIND_ALL_BY_QUALITY + "/" + quality.toString());

        try {
            ResponseEntity<Envelope<List<HeroType>>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

    public List<HeroType> getHeroTypeByFaction(HeroTypeFaction faction) {
        return getAllHeroType(tokenHelper.getToken());
    }

    public List<HeroType> getHeroTypeByFaction(HeroTypeFaction faction, String token) {

        URI uri = URI.create(urlHeroType + "/" + URIConstants.HEROTYPE__FIND_ALL_BY_FACTION + "/" + faction.toString());
        try {
            ResponseEntity<Envelope<List<HeroType>>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

    public List<HeroType> getHeroTypeByFactionAndQuality(HeroTypeFaction faction, HeroTypeQuality quality) {
        return getAllHeroType(tokenHelper.getToken());
    }

    public List<HeroType> getHeroTypeByFactionAndQuality(HeroTypeFaction faction, HeroTypeQuality quality, String token) {

        URI uri = URI.create(
                urlHeroType + "/" + HEROTYPE__FIND_ALL_BY_FACTION_AND_QUALITY + "/" + faction.toString() + "/" + quality.toString()
        );
        try {
            ResponseEntity<Envelope<List<HeroType>>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
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
        } catch (HttpClientErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        } catch (HttpServerErrorException e) {
            Envelope<Void> ret = envelopeUtil.getEnvelopeError(e);
            throw new ValidationException((String) ret.getErrors().get(0));
        }
    }

}
