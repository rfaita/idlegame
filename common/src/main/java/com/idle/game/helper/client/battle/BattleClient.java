package com.idle.game.helper.client.battle;

import static com.idle.game.constant.URIConstants.BATTLE;
import com.idle.game.core.battle.Battle;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "battle-service", path = BATTLE, fallback = BattleClientImpl.class)
public interface BattleClient {

    @RequestMapping(path = "/{att}/{def}", method = RequestMethod.GET)
    public Envelope<Battle> doBattle(@PathVariable("att") String att, @PathVariable("def") String def);

}
