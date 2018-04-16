package com.idle.game.helper.client.friend;

import static com.idle.game.constant.URIConstants.FRIEND;
import com.idle.game.model.Friend;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "player-service", path = FRIEND, fallback = FriendClientImpl.class)
public interface FriendClient {

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public Envelope<List<Friend>> getFriends(@PathVariable("userId") String userId);

}
