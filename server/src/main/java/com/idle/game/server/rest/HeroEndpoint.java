package com.idle.game.server.rest;

import com.idle.game.core.Hero;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.rest.util.annotations.GZIP;
import com.idle.game.server.service.HeroService;
import com.idle.game.server.service.HeroTypeService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/hero")
public class HeroEndpoint {

    @Inject
    private HeroService heroService;
    @Inject
    private HeroTypeService heroTypeService;

    @GET
    @Produces("application/json")
    @Path("/all")
    @GZIP
    public Envelope<List<Hero>> doGetAll() throws Exception {

        Envelope<List<Hero>> ret = new Envelope<>();

        List<Hero> data = new ArrayList<>();

        for (com.idle.game.server.model.Hero hs : heroService.findByLoggedLinkedUser()) {
            data.add(hs.toHero(heroTypeService.getHeroTypes()));
        }

        ret.setData(data);

        return ret;

    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @GZIP
    public Envelope<Hero> doGet(@PathParam("id") Long id) throws Exception {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.findById(id).toHero(heroTypeService.getHeroTypes()));

        return ret;

    }

    @GET
    @Path("/generate")
    @Produces("application/json")
    @GZIP
    public Envelope<Hero> generateHero() throws Exception {

        Envelope<Hero> ret = new Envelope<>();
        ret.setData(heroService.generateHero().toHero(heroTypeService.getHeroTypes()));

        return ret;

    }

    @PUT
    @Path("/{id}/levelUp")
    @Produces("application/json")
    @GZIP
    public Envelope<Hero> doLevelUp(@PathParam("id") Long id) throws Exception {

        Envelope<Hero> ret = new Envelope<>();

        ret.setData(heroService.levelUp(id).toHero(heroTypeService.getHeroTypes()));

        return ret;

    }
}
