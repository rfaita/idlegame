package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.client.guild.GuildMemberClient;
import com.idle.game.model.Guild;
import com.idle.game.model.GuildMember;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class GuildResolver implements GraphQLResolver<Guild> {

    @Autowired
    private GuildMemberClient guildMemberClient;

    public List<GuildMember> getMembers(Guild guild) {

        return guildMemberClient.getGuildMembers(guild.getId()).getData();
    }

    public List<GuildMember> getRequests(Guild guild) {

        return guildMemberClient.getGuildMembersRequests(guild.getId()).getData();
    }
}
