package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.UserResource;
import com.idle.game.model.Resource;
import com.idle.game.server.service.UserResourceService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import static com.idle.game.constant.URIConstants.USERRESOURCE__COMPUTE_RESOURCES;
import static com.idle.game.constant.URIConstants.USERRESOURCE__USE_RESOURCES;
import static com.idle.game.constant.URIConstants.USERRESOURCE__ADD_RESOURCES;

@RestController
@RequestMapping("/userResource")
public class UserResourceRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private UserResourceService userResourceService;

    @RequestMapping(path = "/" + USERRESOURCE__COMPUTE_RESOURCES, method = RequestMethod.GET)
    public @ResponseBody
    Envelope<UserResource> computeResources() {

        Envelope<UserResource> ret = new Envelope<>();
        ret.setData(userResourceService.computeResources(tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "/" + USERRESOURCE__USE_RESOURCES, method = RequestMethod.POST)
    public @ResponseBody
    Envelope<UserResource> useResources(@RequestBody List<Resource> resources) {

        Envelope<UserResource> ret = new Envelope<>();
        ret.setData(userResourceService.useResources(tokenHelper.getUserId(), resources));

        return ret;

    }

    @RequestMapping(path = "/" + USERRESOURCE__ADD_RESOURCES, method = RequestMethod.POST)
    public @ResponseBody
    Envelope<UserResource> addResources(@RequestBody List<Resource> resources) {

        Envelope<UserResource> ret = new Envelope<>();
        ret.setData(userResourceService.addResources(tokenHelper.getUserId(), resources));

        return ret;

    }

}
