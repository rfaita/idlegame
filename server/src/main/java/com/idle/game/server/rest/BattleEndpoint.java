package com.idle.game.server.rest;

import com.idle.game.core.Battle;
import com.idle.game.server.dto.BattleRetorno;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.rest.util.annotations.GZIP;
import com.idle.game.server.service.BattleService;
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
    

    @GET
    @Path("/{idAttackFormation}/{idDefenseFormation}")
    @Produces("application/json")
    @GZIP
    public Envelope<BattleRetorno> doGet(@PathParam("idAttackFormation") Long idAttackFormation,
            @PathParam("idDefenseFormation") Long idDefenseFormation) {

        Envelope<BattleRetorno> ret = new Envelope<>();
        try {

            Battle battle = battleService.doBattle(idAttackFormation, idDefenseFormation);

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
