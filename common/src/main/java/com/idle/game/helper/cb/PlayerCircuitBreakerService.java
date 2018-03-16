package com.idle.game.helper.cb;

import com.idle.game.helper.*;
import static com.idle.game.constant.CacheConstants.PLAYER_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.PLAYER_FIND_BY_LINKED_USER;
import static com.idle.game.constant.CacheConstants.PLAYER_FIND_BY_NAME;
import static com.idle.game.constant.URIConstants.PLAYER__FIND_BY_LINKED_USER;
import static com.idle.game.constant.URIConstants.PLAYER__FIND_BY_NAME;
import com.idle.game.model.Player;
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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@Service
public class PlayerCircuitBreakerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Value("${idle.url.player}")
    private String urlPlayer;

    @HystrixCommand(fallbackMethod = "getPlayerCacheById")
    public Player getPlayerById(String id, String token) {

        URI uri = URI.create(urlPlayer + "/" + id);
        try {
            ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Player>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Player> data = ret.getBody();
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

    public Player getPlayerCacheById(String id, String token) {
        Player ret = (Player) redisTemplate.boundValueOps(PLAYER_FIND_BY_ID + id).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    @HystrixCommand(fallbackMethod = "getPlayerCacheByName")
    public Player getPlayerByName(String name, String token) {

        URI uri = URI.create(urlPlayer + "/" + PLAYER__FIND_BY_NAME + "/" + name);
        try {
            ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Player>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Player> data = ret.getBody();
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

    public Player getPlayerCacheByName(String name, String token) {
        Player ret = (Player) redisTemplate.boundValueOps(PLAYER_FIND_BY_NAME + name).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

    @HystrixCommand(fallbackMethod = "getPlayerCacheByLinkedUser")
    public Player getPlayerByLinkedUser(String linkedUser, String token) {

        URI uri = URI.create(urlPlayer + "/" + PLAYER__FIND_BY_LINKED_USER + "/" + linkedUser);
        try {
            ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<Player>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<Player> data = ret.getBody();
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

    public Player getPlayerCacheByLinkedUser(String linkedUser, String token) {
        Player ret = (Player) redisTemplate.boundValueOps(PLAYER_FIND_BY_LINKED_USER + linkedUser).get();
        if (ret != null) {
            return ret;
        } else {
            return null;
        }
    }

}
