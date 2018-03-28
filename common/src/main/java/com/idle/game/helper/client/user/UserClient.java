package com.idle.game.helper.client.user;

import com.idle.game.model.User;
import com.idle.game.server.dto.Envelope;
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
@FeignClient(name = "user-client", url = "${idle.url.user}", fallback = UserClientImpl.class)
public interface UserClient {

    @RequestMapping(path = "/{userId}", method = RequestMethod.POST)
    public Envelope<User> findById(@PathVariable("userId") String userId);

}
