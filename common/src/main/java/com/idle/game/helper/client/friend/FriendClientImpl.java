package com.idle.game.helper.client.friend;

import com.idle.game.model.Friend;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */

@Component
public class FriendClientImpl implements FriendClient{

    @Override
    public Envelope<List<Friend>> getFriends(String userId) {
        return new Envelope("friend.service.is.down");
    }
    
}
