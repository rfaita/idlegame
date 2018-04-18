package com.idle.game.util;

import com.idle.game.server.dto.Envelope;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author rafael
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    public ResponseEntity<Envelope<Void>> exceptionHandler(ValidationException e) {

        Envelope<Void> ret = new Envelope<>();
        ret.setError(e.getMessage());

        return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = HystrixBadRequestException.class)
    @ResponseBody
    public ResponseEntity<Envelope<Void>> exceptionHandler(HystrixBadRequestException e) {

        Envelope<Void> ret = new Envelope<>();
        ret.setError(e.getMessage());

        return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
    }
}
