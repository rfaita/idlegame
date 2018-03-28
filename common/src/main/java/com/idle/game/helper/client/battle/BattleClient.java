package com.idle.game.helper.client.battle;

import com.idle.game.core.battle.Battle;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@Primary
@Qualifier(value = "default")
@FeignClient(name = "battle-client", url = "${idle.url.battle}")
public interface BattleClient {

    @RequestMapping(path = "/{att}/{def}", method = RequestMethod.GET)
    public Envelope<Battle> doBattle(@PathVariable("att") String att, @PathVariable("def") String def);

}
