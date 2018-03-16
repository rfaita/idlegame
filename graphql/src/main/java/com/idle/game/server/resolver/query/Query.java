package com.idle.game.server.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.helper.HeroHelper;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.helper.cb.PlayerCircuitBreakerService;
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
public class Query implements GraphQLQueryResolver {

    @Autowired
    private PlayerCircuitBreakerService playerCircuitBreakerService;

    @Autowired
    private HeroHelper heroHelper;

    @Autowired
    private HeroTypeHelper heroTypeHelper;

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public List<Hero> findHeroes(String name, String quality) {

        String token = manualTokenHelper.getToken();

        Player player = playerCircuitBreakerService.getPlayerByName(name, token);
        if (player == null) {
            return null;
        } else {
            if (quality == null) {
                return heroHelper.getHeroesByPlayerId(player.getId(), token);
            } else {
                return heroHelper.getHeroesByPlayerIdAndQuality(player.getId(), HeroQuality.valueOf(quality), token);
            }
        }
    }

    public List<HeroType> findHeroesType(String faction, String quality) {
        String token = manualTokenHelper.getToken();

        if (faction == null) {
            if (quality == null) {
                return heroTypeHelper.getAllHeroType(token);
            } else {
                return heroTypeHelper.getHeroTypeByQuality(HeroTypeQuality.valueOf(quality), token);
            }
        } else {
            if (quality == null) {
                return heroTypeHelper.getHeroTypeByFaction(HeroTypeFaction.valueOf(faction), token);
            } else {
                return heroTypeHelper.getHeroTypeByFactionAndQuality(HeroTypeFaction.valueOf(faction), HeroTypeQuality.valueOf(quality), token);
            }
        }
    }
}
