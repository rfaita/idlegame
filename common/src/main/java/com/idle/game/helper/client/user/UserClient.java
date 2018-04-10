package com.idle.game.helper.client.user;

import static com.idle.game.constant.URIConstants.USER__FIND_BY_NICK_NAME;
import com.idle.game.model.User;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "player-service", path = "/user", fallback = UserClientImpl.class)
public interface UserClient {

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public Envelope<User> findById(@PathVariable("userId") String userId);

    @RequestMapping(path = "/" + USER__FIND_BY_NICK_NAME + "/{nickName}", method = RequestMethod.GET)
    public Envelope<User> findByNickName(@PathVariable("nickName") String nickName);
}
