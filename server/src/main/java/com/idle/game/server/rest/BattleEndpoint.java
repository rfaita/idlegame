package com.idle.game.server.rest;

import com.idle.game.core.Battle;
import com.idle.game.core.FormationType;
import com.idle.game.server.dto.BattleRetorno;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.model.Formation;
import com.idle.game.server.model.Hero;
import com.idle.game.server.service.BattleService;
import com.idle.game.server.service.FormationService;
import com.idle.game.server.service.HeroTypeService;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author rafael
 */
@Path("/battle")
public class BattleEndpoint {

    @Inject
    private BattleService battleService;
    @Inject
    private FormationService formationService;
    @Inject
    private HeroTypeService heroTypeService;

    @GET
    @Path("/{idAttackFormation}/{idDefenseFormation}")
    @Produces("application/json")
    public Envelope<BattleRetorno> doGet(@PathParam("idAttackFormation") Long idAttackFormation,
            @PathParam("idDefenseFormation") Long idDefenseFormation) {

        Envelope<BattleRetorno> ret = new Envelope<BattleRetorno>();
        try {

            Formation attFormation = formationService.findById(idAttackFormation);
            attFormation.getHeroes().forEach(ph -> {
                ph.getHero().setHeroType(heroTypeService.getHeroType(ph.getHero().getHeroTypeId()));
            });

            Formation defFormation = formationService.findById(idDefenseFormation);
            defFormation.getHeroes().forEach(ph -> {
                ph.getHero().setHeroType(heroTypeService.getHeroType(ph.getHero().getHeroTypeId()));
            });

            Battle battle = battleService.doBattle(attFormation, defFormation);

            ret.setData(new BattleRetorno(battle.getBattleLog(), battle.getWinner().getFormationType()));

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
