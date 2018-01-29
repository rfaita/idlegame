package com.idle.game.helper;

import com.idle.game.core.battle.Battle;
import com.idle.game.server.dto.Envelope;
import java.net.URI;
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
public class BattleHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Value("${idle.url.battle}")
    private String urlBattle;

    public Battle doBattle(String attForm, String defForm) {

        URI uri = URI.create(urlBattle + "/" + attForm + "/" + defForm);

        ResponseEntity<Envelope<Battle>> ret = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                new ParameterizedTypeReference<Envelope<Battle>>() {
        });

        if (ret.getStatusCode() == HttpStatus.OK) {
            Envelope<Battle> data = ret.getBody();
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
