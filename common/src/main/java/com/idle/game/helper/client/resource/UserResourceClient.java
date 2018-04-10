package com.idle.game.helper.client.resource;

import com.idle.game.model.UserResource;
import com.idle.game.model.Resource;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static com.idle.game.constant.URIConstants.USERRESOURCE__USE_RESOURCES;
import static com.idle.game.constant.URIConstants.USERRESOURCE__ADD_RESOURCES;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author rafael
 */
@FeignClient(name = "player-service", path = "/userResource", fallback = UserResourceClientImpl.class)
public interface UserResourceClient {

    @RequestMapping(path = "/" + USERRESOURCE__USE_RESOURCES, method = RequestMethod.POST)
    public Envelope<UserResource> useResources(@RequestBody List<Resource> resources);

    @RequestMapping(path = "/" + USERRESOURCE__ADD_RESOURCES, method = RequestMethod.POST)
    public Envelope<UserResource> addResources(@RequestBody List<Resource> resources);

}
