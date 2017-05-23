package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import com.idle.game.server.model.Player;
import com.idle.game.server.rest.util.annotations.GZIP;
import com.idle.game.server.service.PlayerService;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/player")
public class PlayerEndpoint {

    @Inject
    private PlayerService playerService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @GZIP
    public Envelope<Player> doGet(@PathParam("id") Long id) {

        Envelope<Player> ret = new Envelope<>();
        try {
            ret.setData(playerService.findById(id));

        } catch (Exception ex) {
            if (ex.getCause() instanceof ValidationException) {
                ret.setError(new com.idle.game.server.dto.Error(-1, ex.getCause().getMessage()));
            } else {
                ret.setError(new com.idle.game.server.dto.Error(-1, ex.getMessage()));
            }
        }
        return ret;

    }
}
