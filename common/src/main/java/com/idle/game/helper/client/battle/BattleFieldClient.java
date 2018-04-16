package com.idle.game.helper.client.battle;

import static com.idle.game.constant.URIConstants.BATTLE_FIELD;
import com.idle.game.model.battle.BattleField;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "battle-service", path = BATTLE_FIELD, fallback = BattleFieldClientImpl.class)
public interface BattleFieldClient {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<BattleField> findById(@PathVariable("id") String id);

    @RequestMapping(path = "", method = RequestMethod.POST)
    Envelope<BattleField> update(@RequestBody BattleField battleField);

}
