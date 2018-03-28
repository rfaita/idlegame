package com.idle.game.server.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idle.game.helper.client.hero.HeroTypeClient;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.HeroType;
import com.idle.game.model.User;
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
    private UserClient userClient;

    @Autowired
    private HeroTypeClient heroTypeClient;

    public User user(String userId) {

        return userClient.findById(userId).getData();
    }

    public List<HeroType> heroesType(String faction, String quality) {

        if (faction == null) {
            if (quality == null) {
                return heroTypeClient.findAll().getData();
            } else {
                return heroTypeClient.findAllByQuality(quality).getData();
            }
        } else {
            if (quality == null) {
                return heroTypeClient.findAllByFaction(faction).getData();
            } else {
                return heroTypeClient.findAllByFactionAndQuality(faction, quality).getData();
            }
        }
    }
}
