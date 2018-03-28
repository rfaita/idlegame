package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.Friend;
import com.idle.game.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class FriendResolver implements GraphQLResolver<Friend> {

    @Autowired
    private UserClient userClient;

    public User getUser(Friend friend) {
        return userClient.findById(friend.getFriendUserId()).getData();
    }
}
