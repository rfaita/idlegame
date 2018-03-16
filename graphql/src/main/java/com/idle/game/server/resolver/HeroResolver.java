package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.cb.HeroTypeCircuitBreakerService;
import com.idle.game.helper.cb.PlayerCircuitBreakerService;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import com.idle.game.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class HeroResolver implements GraphQLResolver<Hero> {

    @Autowired
    private HeroTypeCircuitBreakerService heroTypeCircuitBreakerService;

    @Autowired
    private PlayerCircuitBreakerService playerCircuitBreakerService;

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public HeroType getHeroType(Hero hero) {
        return heroTypeCircuitBreakerService.getHeroTypeById(hero.getHeroTypeId(), manualTokenHelper.getToken());
    }

    public Player getPlayer(Hero hero) {
        return playerCircuitBreakerService.getPlayerById(hero.getPlayerId(), manualTokenHelper.getToken());
    }

}
