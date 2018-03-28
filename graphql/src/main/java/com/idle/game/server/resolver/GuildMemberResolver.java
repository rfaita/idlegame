package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.GuildMember;
import com.idle.game.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class GuildMemberResolver implements GraphQLResolver<GuildMember> {

    @Autowired
    private UserClient userClient;

    public User getUser(GuildMember guildMember) {
        return userClient.findById(guildMember.getUserId()).getData();
    }
}
