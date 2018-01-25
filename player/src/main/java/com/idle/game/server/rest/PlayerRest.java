package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.idle.game.constant.URIConstants.PLAYER__COMPUTE_RESOURCES;
import static com.idle.game.constant.URIConstants.PLAYER__FIND_BY_LINKED_USER;
import static com.idle.game.constant.URIConstants.PLAYER__USE_RESOURCES;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.Resource;
import com.idle.game.server.service.PlayerService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/player")
public class PlayerRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private PlayerService playerService;

    @RequestMapping(path = "/" + PLAYER__FIND_BY_LINKED_USER + "/{linkedUser}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Player> findByLinkedUser(@PathVariable("linkedUser") String linkedUser) {

        Envelope<Player> ret = new Envelope<>();
        ret.setData(playerService.findByLinkedUser(linkedUser));

        return ret;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Player> findById(@PathVariable("id") String id) {

        Envelope<Player> ret = new Envelope<>();
        ret.setData(playerService.findById(id));

        return ret;

    }

    @RequestMapping(path = "/" + PLAYER__COMPUTE_RESOURCES, method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Player> computeResources() {

        Envelope<Player> ret = new Envelope<>();
        ret.setData(playerService.computeResources(tokenHelper.getUser()));

        return ret;

    }

    @RequestMapping(path = "/" + PLAYER__USE_RESOURCES, method = RequestMethod.POST)
    public @ResponseBody
    Envelope<Player> useResources(@RequestBody List<Resource> resources) {

        Envelope<Player> ret = new Envelope<>();
        ret.setData(playerService.useResources(tokenHelper.getUser(), resources));

        return ret;

    }

}
