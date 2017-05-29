package com.idle.game.server.rest.util;

import com.idle.game.core.constant.IdleConstants;
import com.idle.game.server.dto.Envelope;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author rafael
 */
@Provider
public class CustomExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(IdleConstants.LOG_NAME_SERVER);

    @Override
    public Response toResponse(Exception ex) {

        LOG.log(Level.WARNING, ex.getMessage(), ex);

        Envelope<?> ret = new Envelope<>();

        Throwable e = ex;

        if (ex instanceof EJBException) {
            e = ex.getCause();
        }

        if (e instanceof ConstraintViolationException) {
            List<com.idle.game.server.dto.Error> erros = new ArrayList<>();
            ((ConstraintViolationException) ex).getConstraintViolations().forEach(v -> {
                erros.add(new com.idle.game.server.dto.Error(-1, v.getMessage()));
            });
            ret.setErrors(erros);

            return Response.status(Response.Status.BAD_REQUEST).entity(ret).build();
        } else {
            ret.setError(new com.idle.game.server.dto.Error(-1, ex.getMessage()));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ret).build();
        }

    }
}
