package com.idle.game.helper;

import static com.idle.game.constant.CacheConstants.*;
import static com.idle.game.constant.URIConstants.GUILD__FIND_BY_USER_OWNER;
import com.idle.game.model.Guild;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@Service
public class GuildHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Value("${idle.url.guild}")
    private String urlGuild;

    public Guild getGuildById(String id) {
        return this.getGuildById(id, tokenHelper.getToken());
    }

    @HystrixCommand(fallbackMethod = "getGuildCacheById")
    public Guild getGuildById(String id, String token) {

        URI uri = URI.create(urlGuild + "/" + id);
        try {
            ResponseEntity<Envelope<Guild>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Guild>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Guild> data = ret.getBody();
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

    public Guild getGuildCacheById(String id, String token) {
        Guild ret = (Guild) redisTemplate.boundValueOps(GUILD_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    public Guild getGuildByUserOwner(String user) {
        return this.getGuildByUserOwner(user, tokenHelper.getToken());
    }

    @HystrixCommand(fallbackMethod = "getGuildCacheByUserOwner")
    public Guild getGuildByUserOwner(String user, String token) {

        URI uri = URI.create(urlGuild + "/" + GUILD__FIND_BY_USER_OWNER + "/" + user);
        try {
            ResponseEntity<Envelope<Guild>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Guild>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Guild> data = ret.getBody();
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

    public Guild getGuildCacheByUserOwner(String user, String token) {
        Guild ret = (Guild) redisTemplate.boundValueOps(GUILD_FIND_BY_USER_OWNER + user).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

}
