package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import com.idle.game.server.model.Hero;
import com.idle.game.server.service.HeroService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/hero")
public class HeroEndpoint {

    @Inject
    private HeroService heroService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Envelope<Hero> doGet(@PathParam("id") Long id) {

        Envelope<Hero> ret = new Envelope<Hero>();
        try {
            ret.setData(heroService.findById(id));

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
