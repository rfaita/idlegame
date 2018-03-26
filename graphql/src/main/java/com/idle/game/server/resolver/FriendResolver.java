package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.cb.PlayerCircuitBreakerService;
import com.idle.game.model.Friend;
import com.idle.game.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class FriendResolver implements GraphQLResolver<Friend> {

    @Autowired
    private PlayerCircuitBreakerService playerCircuitBreakerService;

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public Player getPlayerFriend(Friend friend) {
        return playerCircuitBreakerService.getPlayerByLinkedUser(friend.getUserFriend(), manualTokenHelper.getToken());
    }

}
