package com.idle.game.helper.client.battle;

import com.idle.game.core.hero.BattleHero;
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
@FeignClient(name = "battle-hero-client", url = "${idle.url.battleHero}", fallback = BattleHeroClientImpl.class)
public interface BattleHeroClient {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<BattleHero> getBattleHero(@PathVariable("id") String id);

}
