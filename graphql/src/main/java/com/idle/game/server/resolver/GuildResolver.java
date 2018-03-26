package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.GuildMemberHelper;
import com.idle.game.helper.ManualTokenHelper;
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
    private GuildMemberHelper guildMemberHelper;
    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public List<GuildMember> getMembers(Guild guild) {

        String token = manualTokenHelper.getToken();

        return guildMemberHelper.getGuildMembers(guild.getId(), token);
    }

    public List<GuildMember> getRequests(Guild guild) {

        String token = manualTokenHelper.getToken();

        return guildMemberHelper.getGuildMembersRequests(guild.getId(), token);
    }
}
