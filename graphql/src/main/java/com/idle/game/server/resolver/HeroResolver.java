package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.PlayerHelper;
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
    private HeroTypeHelper heroTypeHelper;

    @Autowired
    private PlayerHelper playerHelper;

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public HeroType getHeroType(Hero hero) {
        return heroTypeHelper.getHeroTypeById(hero.getHeroTypeId(), manualTokenHelper.getToken());
    }

    public Player getPlayer(Hero hero) {
        return playerHelper.getPlayerById(hero.getPlayerId(), manualTokenHelper.getToken());
    }

}
