package com.idle.game.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.server.dto.Envelope;
import java.io.IOException;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 *
 * @author rafael
 */
@Service
public class EnvelopeUtil {

    @Autowired
    private ObjectMapper objectMapper;

    public Envelope<Void> getEnvelopeError(HttpClientErrorException e) {
        try {
            return objectMapper.readValue(e.getResponseBodyAsString(), Envelope.class);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public Envelope<Void> getEnvelopeError(HttpServerErrorException e) {
        try {
            return objectMapper.readValue(e.getResponseBodyAsString(), Envelope.class);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

}
