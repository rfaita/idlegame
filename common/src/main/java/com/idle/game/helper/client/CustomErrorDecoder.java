package com.idle.game.helper.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.game.server.dto.Envelope;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import javax.validation.ValidationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class CustomErrorDecoder implements ErrorDecoder {
    
    private final ErrorDecoder delegate = new ErrorDecoder.Default();
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public Exception decode(String methodKey, Response response) {
        
        Envelope<?> data;
        try {
            data = mapper.readValue(response.body().asInputStream(), Envelope.class);
        } catch (IOException e) {
            throw new ValidationException("can.not.parse.response", e);
        }
        
        if (!data.getErrors().isEmpty()) {
            return new HystrixBadRequestException(data.getErrors().get(0));
        }
        
        return delegate.decode(methodKey, response);
    }
}
