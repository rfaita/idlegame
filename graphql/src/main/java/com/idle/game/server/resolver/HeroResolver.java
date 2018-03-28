package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.client.hero.HeroTypeClient;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import com.idle.game.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class HeroResolver implements GraphQLResolver<Hero> {

    @Autowired
    private HeroTypeClient heroTypeClient;

    @Autowired
    private UserClient userClient;

    public HeroType getHeroType(Hero hero) {
        return heroTypeClient.findById(hero.getHeroTypeId()).getData();
    }

    public User getUser(Hero hero) {
        return userClient.findById(hero.getUserId()).getData();
    }

}
