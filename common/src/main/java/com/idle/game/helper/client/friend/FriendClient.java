package com.idle.game.helper.client.friend;

import com.idle.game.model.Friend;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@Primary
@Qualifier(value = "default")
@FeignClient(name = "friend-client", url = "${idle.url.friend}")
public interface FriendClient {

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public Envelope<List<Friend>> getFriends(@PathVariable("userId") String userId);

}
