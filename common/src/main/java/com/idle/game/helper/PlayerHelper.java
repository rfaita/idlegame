package com.idle.game.helper;

import com.idle.game.constant.URIConstants;
import static com.idle.game.constant.URIConstants.PLAYER__FIND_BY_LINKED_USER;
import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.Resource;
import com.idle.game.server.dto.Envelope;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
public class PlayerHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Value("${idle.url.player}")
    private String urlPlayer;

    public Player getPlayerById(String id) {

        URI uri = URI.create(urlPlayer + "/" + id);

        ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
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
    }

    public Player getPlayerByLinkedUser(String linkedUser) {

        URI uri = URI.create(urlPlayer + "/" + PLAYER__FIND_BY_LINKED_USER + "/" + linkedUser);

        ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
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
    }

    public Player useResources(List<Resource> resources) {

        URI uri = URI.create(urlPlayer + "/" + URIConstants.PLAYER__USE_RESOURCES);

        ResponseEntity<Envelope<Player>> ret = restTemplate.exchange(uri,
                HttpMethod.POST,
                new HttpEntity(resources, HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
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
    }
}
