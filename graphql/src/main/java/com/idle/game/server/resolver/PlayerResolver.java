package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.helper.HeroHelper;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import com.idle.game.model.Player;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class PlayerResolver implements GraphQLResolver<Player> {

    @Autowired
    private HeroHelper heroHelper;
    @Autowired
    private HeroTypeHelper heroTypeHelper;
    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public List<Hero> getHeroes(Player player, String heroTypeName, String quality) {

        String token = manualTokenHelper.getToken();

        if (heroTypeName != null) {
            HeroType heroType = heroTypeHelper.getHeroTypeByName(heroTypeName, token);
            if (heroType != null) {
                if (quality != null) {
                    return heroHelper.getHeroesByPlayerIdAndHeroTypeIdAndQuality(player.getId(), heroType.getId(), HeroQuality.valueOf(quality), token);
                } else {
                    return heroHelper.getHeroesByPlayerIdAndHeroTypeId(player.getId(), heroType.getId(), token);
                }
            } else {
                return null;
            }

        } else {
            return heroHelper.getHeroesByPlayerId(player.getId(), token);
        }
    }

}
