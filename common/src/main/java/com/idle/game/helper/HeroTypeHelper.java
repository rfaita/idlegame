package com.idle.game.helper;

import com.idle.game.constant.URIConstants;
import static com.idle.game.constant.URIConstants.HEROTYPE__FIND_ALL_BY_FACTION_AND_QUALITY;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.model.HeroType;
import com.idle.game.server.dto.Envelope;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.idle.game.core.hero.type.HeroTypeFaction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static com.idle.game.constant.URIConstants.HEROTYPE__FIND_ALL_BY_QUALITY;
import com.idle.game.helper.cb.HeroTypeCircuitBreakerService;
import com.idle.game.util.EnvelopeUtil;
import javax.validation.ValidationException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author rafael
 */
@Service
public class HeroTypeHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private HeroTypeCircuitBreakerService heroTypeCircuitBreakerService;

    @Value("${idle.url.heroType}")
    private String urlHeroType;

    public HeroType getHeroTypeByName(String name) {
        return getHeroTypeByName(name, tokenHelper.getToken());
    }

    public HeroType getHeroTypeByName(String name, String token) {
        return heroTypeCircuitBreakerService.getHeroTypeByName(name, token);
    }

    public HeroType getHeroTypeById(String id) {
        return heroTypeCircuitBreakerService.getHeroTypeById(id, tokenHelper.getToken());
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
