package com.idle.game.server.rest;

import com.idle.game.core.formation.Formation;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.dto.PvpRollRetorno;
import com.idle.game.server.rest.util.annotations.GZIP;
import com.idle.game.server.service.FormationService;
import com.idle.game.server.service.HeroTypeService;
import com.idle.game.server.service.PlayerService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author rafael
 */
@Path("/formation")
public class FormationEndpoint {

    @Inject
    private FormationService formationService;
    @Inject
    private HeroTypeService heroTypeService;
    @Inject
    private PlayerService playerService;

    @GET
    @Path("/all")
    @Produces("application/json")
    @GZIP
    public Envelope<List<Formation>> doGetAll() throws Exception {

        Envelope<List<Formation>> ret = new Envelope<>();

        List<Formation> data = new ArrayList<>();

        for (com.idle.game.server.model.Formation hs : formationService.findByLoggedLinkedUser()) {
            data.add(hs.toFormation(heroTypeService.getHeroTypes()));
        }

        ret.setData(data);

        return ret;

    }

    @GET
    @Path("/pvpRoll")
    @Produces("application/json")
    @GZIP
    public Envelope<PvpRollRetorno> findPvpOpponents() throws Exception {

        Envelope<PvpRollRetorno> ret = new Envelope<>();
        ret.setData(formationService.pvpRoll().toPvpRollRetorno(heroTypeService.getHeroTypes()));

        return ret;

    }

    @GET
    @Path("/pvpRoll/paid")
    @Produces("application/json")
    @GZIP
    public Envelope<PvpRollRetorno> findPvpOpponentsPaid() throws Exception {

        Envelope<PvpRollRetorno> ret = new Envelope<>();
        ret.setData(formationService.pvpRoll(Boolean.TRUE).toPvpRollRetorno(heroTypeService.getHeroTypes()));

        return ret;

    }

    @GET
    @Path("/nextLevelFormationPve")
    @Produces("application/json")
    @GZIP
    public Envelope<Formation> nextLevelFormationPve() throws Exception {

        Envelope<Formation> ret = new Envelope<>();
        ret.setData(playerService.findByLoggedLinkedUser().getNextLevelFormationPve().toFormation(heroTypeService.getHeroTypes()));

        return ret;

    }

    @GET
    @Path("/nextLevelFormationDungeon")
    @Produces("application/json")
    @GZIP
    public Envelope<Formation> nextLevelFormationDungeon() throws Exception {

        Envelope<Formation> ret = new Envelope<>();
        ret.setData(playerService.findByLoggedLinkedUser().getNextLevelFormationDungeon().toFormation(heroTypeService.getHeroTypes()));

        return ret;

    }

    @GET
    @Path("/allocation/{formationAllocation}")
    @Produces("application/json")
    @GZIP
    public Envelope<Formation> doGet(@PathParam("formationAllocation") FormationAllocation formationAllocation) throws Exception {

        Envelope<Formation> ret = new Envelope<>();
        ret.setData(formationService.findByLoggedLinkedUserAndAllocation(formationAllocation).toFormation(heroTypeService.getHeroTypes()));

        return ret;

    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @GZIP
    public Envelope<Formation> doGet(@PathParam("id") Long id) throws Exception {

        Envelope<Formation> ret = new Envelope<>();
        ret.setData(formationService.findById(id).toFormation(heroTypeService.getHeroTypes()));

        return ret;

    }

    @PUT
    @Produces("application/json")
    @GZIP
    public Envelope<Formation> doPut(com.idle.game.server.model.Formation f) throws Exception {

        Envelope<Formation> ret = new Envelope<>();

        f = formationService.save(f);

        ret.setData(formationService.findById(f.getId()).toFormation(heroTypeService.getHeroTypes()));

        return ret;

    }

    @POST
    @Produces("application/json")
    @GZIP
    public Envelope<Formation> doPost(com.idle.game.server.model.Formation f) throws Exception {

        Envelope<Formation> ret = new Envelope<>();

        f = formationService.save(f);

        ret.setData(formationService.findById(f.getId()).toFormation(heroTypeService.getHeroTypes()));

        return ret;

    }

}
