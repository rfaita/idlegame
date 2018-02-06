package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.mongo.PlayerResource;
import com.idle.game.model.mongo.Resource;
import com.idle.game.server.service.PlayerResourceService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import static com.idle.game.constant.URIConstants.PLAYERRESOURCE__USE_RESOURCES;
import static com.idle.game.constant.URIConstants.PLAYERRESOURCE__COMPUTE_RESOURCES;

@RestController
@RequestMapping("/playerResource")
public class PlayerResourceRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private PlayerResourceService playerResourceService;


    @RequestMapping(path = "/" + PLAYERRESOURCE__COMPUTE_RESOURCES, method = RequestMethod.GET)
    public @ResponseBody
    Envelope<PlayerResource> computeResources() {

        Envelope<PlayerResource> ret = new Envelope<>();
        ret.setData(playerResourceService.computeResources(tokenHelper.getUser()));

        return ret;

    }

    @RequestMapping(path = "/" + PLAYERRESOURCE__USE_RESOURCES, method = RequestMethod.POST)
    public @ResponseBody
    Envelope<PlayerResource> useResources(@RequestBody List<Resource> resources) {

        Envelope<PlayerResource> ret = new Envelope<>();
        ret.setData(playerResourceService.useResources(tokenHelper.getUser(), resources));

        return ret;

    }

}
