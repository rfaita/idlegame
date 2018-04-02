package com.idle.game.helper.client.resource;

import com.idle.game.model.Resource;
import com.idle.game.model.UserResource;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class UserResourceClientImpl implements UserResourceClient {

    @Override
    public Envelope<UserResource> useResources(List<Resource> resources) {
        return new Envelope("user.resource.service.is.down");
    }

    @Override
    public Envelope<UserResource> addResources(List<Resource> resources) {
        return new Envelope("user.resource.service.is.down");
    }

}
