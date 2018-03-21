package com.idle.game.server.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.cb.PlayerCircuitBreakerService;
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
    private HeroTypeHelper heroTypeHelper;

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public Player player(String name) {

        String token = manualTokenHelper.getToken();

        return playerCircuitBreakerService.getPlayerByName(name, token);
    }

    public List<HeroType> heroesType(String faction, String quality) {
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
