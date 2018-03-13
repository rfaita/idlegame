package com.idle.game.helper;

import static com.idle.game.constant.URIConstants.SHOP__BUY_LOOT_ROLL;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.dto.Envelope;
import com.idle.game.util.EnvelopeUtil;
import java.net.URI;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
public class LootRollHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.shop}")
    private String urlShop;

    public LootRoll buyLootRoll(String id) {

        URI uri = URI.create(urlShop + "/" + SHOP__BUY_LOOT_ROLL + "/" + id);
        try {
            ResponseEntity<Envelope<LootRoll>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                    new ParameterizedTypeReference<Envelope<LootRoll>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<LootRoll> data = ret.getBody();
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
