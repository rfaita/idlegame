package com.idle.game.helper.client.battle;

import com.idle.game.model.battle.BattleField;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "battle-field-client", url = "${idle.url.battleField}", fallback = BattleFieldClientImpl.class)
public interface BattleFieldClient {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<BattleField> findById(@PathVariable("id") String id);

}
