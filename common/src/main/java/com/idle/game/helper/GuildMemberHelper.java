package com.idle.game.helper;

import static com.idle.game.constant.URIConstants.GUILD_MEMBER__CREATE_ADMIN;
import static com.idle.game.constant.URIConstants.GUILD_MEMBER__REQUESTS;
import com.idle.game.model.GuildMember;
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
public class GuildMemberHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private EnvelopeUtil envelopeUtil;

    @Value("${idle.url.guildMember}")
    private String urlGuildMember;

    public void createAdmin(String guildId) {

        URI uri = URI.create(urlGuildMember + "/" + GUILD_MEMBER__CREATE_ADMIN + "/" + guildId);
        try {
            restTemplate.exchange(uri,
                    HttpMethod.POST,
                    new HttpEntity(HeaderUtil.getAuthHeaders(tokenHelper.getToken())),
                    new ParameterizedTypeReference<Envelope<Void>>() {
            });

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

    public GuildMember getMyGuildMember() {
        return this.getMyGuildMember(tokenHelper.getToken());
    }

    public GuildMember getMyGuildMember(String token) {

        URI uri = URI.create(urlGuildMember);
        try {
            ResponseEntity<Envelope<GuildMember>> ret = restTemplate.exchange(uri,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<GuildMember>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<GuildMember> data = ret.getBody();
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

    public List<GuildMember> getGuildMembers(String guildId) {
        return this.getGuildMembers(guildId, tokenHelper.getToken());
    }

    public List<GuildMember> getGuildMembers(String guildId, String token) {

        URI uri = URI.create(urlGuildMember);
        try {
            ResponseEntity<Envelope<List<GuildMember>>> ret = restTemplate.exchange(uri + "/" + guildId,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<List<GuildMember>>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<List<GuildMember>> data = ret.getBody();
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

    public List<GuildMember> getGuildMembersRequests(String guildId) {
        return this.getGuildMembers(guildId, tokenHelper.getToken());
    }

    public List<GuildMember> getGuildMembersRequests(String guildId, String token) {

        URI uri = URI.create(urlGuildMember);
        try {
            ResponseEntity<Envelope<List<GuildMember>>> ret = restTemplate.exchange(uri + "/" + GUILD_MEMBER__REQUESTS + "/" + guildId,
                    HttpMethod.GET,
                    new HttpEntity(HeaderUtil.getAuthHeaders(token)),
                    new ParameterizedTypeReference<Envelope<List<GuildMember>>>() {
            });

            if (ret.getStatusCode() == HttpStatus.OK) {
                Envelope<List<GuildMember>> data = ret.getBody();
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
