package com.idle.game.server.rest;

import com.idle.game.core.battle.Battle;
import com.idle.game.type.FormationAllocation;
import com.idle.game.server.dto.BattleRetorno;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.rest.util.annotations.GZIP;
import com.idle.game.server.service.BattleService;
import javax.inject.Inject;
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
    public Envelope<BattleRetorno> doBattle(@PathParam("idAttackFormation") Long idAttackFormation,
            @PathParam("idDefenseFormation") Long idDefenseFormation) throws Exception {

        Envelope<BattleRetorno> ret = new Envelope<>();

        Battle battle = battleService.doBattle(idAttackFormation, idDefenseFormation);

        ret.setData(new BattleRetorno(battle.getBattleLog(), battle.getWinner().getFormationType()));

        return ret;

    }

    @GET
    @Path("/pvp/{linkedUserDefense}")
    @Produces("application/json")
    @GZIP
    public Envelope<BattleRetorno> doBattlePvp(@PathParam("linkedUserDefense") String linkedUserDefense) throws Exception {

        Envelope<BattleRetorno> ret = new Envelope<>();

        Battle battle = battleService.doBattle(linkedUserDefense, FormationAllocation.PVP);

        ret.setData(new BattleRetorno(battle.getBattleLog(), battle.getWinner().getFormationType()));

        return ret;

    }

    @GET
    @Path("/pve")
    @Produces("application/json")
    @GZIP
    public Envelope<BattleRetorno> doPveBattle() throws Exception {

        Envelope<BattleRetorno> ret = new Envelope<>();

        Battle battle = battleService.doPveBattle();

        ret.setData(new BattleRetorno(battle.getBattleLog(), battle.getWinner().getFormationType()));

        return ret;

    }

    @GET
    @Path("/dungeon")
    @Produces("application/json")
    @GZIP
    public Envelope<BattleRetorno> doDungeonBattle() throws Exception {

        Envelope<BattleRetorno> ret = new Envelope<>();

        Battle battle = battleService.doDungeonBattle();

        ret.setData(new BattleRetorno(battle.getBattleLog(), battle.getWinner().getFormationType()));

        return ret;

    }

}
