package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.idle.game.constant.URIConstants.*;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.Guild;
import com.idle.game.server.service.GuildService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/guild")
public class GuildRest {

    @Autowired
    private GuildService guildService;

    @Autowired
    private TokenHelper tokenHelper;

    @RequestMapping(path = "/" + GUILD__FIND_BY_USER_OWNER + "/{user}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Guild> findByUserOwner(@PathVariable("user") String user) {

        Envelope<Guild> ret = new Envelope<>();
        ret.setData(guildService.findByUserOwner(user));

        return ret;

    }
    @RequestMapping(path = "/findByName/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Guild> findByName(@PathVariable("name") String name) {

        Envelope<Guild> ret = new Envelope<>();
        ret.setData(guildService.findByName(name));

        return ret;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Guild> findById(@PathVariable("id") String id) {

        Envelope<Guild> ret = new Envelope<>();
        ret.setData(guildService.findById(id));

        return ret;

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseBody
    public Envelope<Guild> create(@RequestBody Guild guild) throws Exception {

        guild.setId(null);
        guild.setUserOwner(tokenHelper.getSubject());
        guild.setLevel(1);

        return new Envelope<>(guildService.create(guild));
    }

}
