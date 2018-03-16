package com.idle.game.helper;

import static com.idle.game.constant.CacheConstants.PLAYER_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.PLAYER_FIND_BY_LINKED_USER;
import static com.idle.game.constant.CacheConstants.PLAYER_FIND_BY_NAME;
import static com.idle.game.constant.URIConstants.PLAYER__FIND_BY_LINKED_USER;
import static com.idle.game.constant.URIConstants.PLAYER__FIND_BY_NAME;
import com.idle.game.helper.cb.PlayerCircuitBreakerService;
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
public class PlayerHelper {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private PlayerCircuitBreakerService playerCircuitBreakerService;

    public Player getPlayerById(String id) {
        return playerCircuitBreakerService.getPlayerById(id, tokenHelper.getToken());
    }

    public Player getPlayerByName(String name) {
        return playerCircuitBreakerService.getPlayerByName(name, tokenHelper.getToken());
    }

    public Player getPlayerByLinkedUser(String linkedUser) {
        return playerCircuitBreakerService.getPlayerByLinkedUser(linkedUser, tokenHelper.getToken());
    }

}
