package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.cb.PlayerCircuitBreakerService;
import com.idle.game.model.GuildMember;
import com.idle.game.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class GuildMemberResolver implements GraphQLResolver<GuildMember> {

    @Autowired
    private PlayerCircuitBreakerService playerCircuitBreakerService;
    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public Player getPlayer(GuildMember guildMember) {

        String token = manualTokenHelper.getToken();

        return playerCircuitBreakerService.getPlayerByLinkedUser(guildMember.getUserMemberId(), token);
    }

}
