package com.idle.game.helper;

import static com.idle.game.constant.URIConstants.HERO__FIND_ALL_BY_PLAYER;
import static com.idle.game.constant.URIConstants.HERO__ROLL;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.helper.cb.HeroCircuitBreakerService;
import com.idle.game.model.Hero;
import com.idle.game.server.dto.Envelope;
import com.idle.game.util.EnvelopeUtil;
import java.net.URI;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
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
    private EnvelopeUtil envelopeUtil;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private HeroCircuitBreakerService heroCircuitBreakerService;

    @Value("${idle.url.hero}")
    private String urlHero;

    public Hero getHeroById(String id) {
        return heroCircuitBreakerService.getHeroById(id, tokenHelper.getToken());
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

}
