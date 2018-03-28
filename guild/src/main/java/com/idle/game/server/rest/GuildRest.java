package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<Guild> myGuild() {

        Envelope<Guild> ret = new Envelope<>();
        ret.setData(guildService.myGuild());

        return ret;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseBody
    public Envelope<Guild> create(@RequestBody Guild guild) throws Exception {

        guild.setId(null);
        guild.setOwnerUserId(tokenHelper.getUserId());
        guild.setLevel(1);

        return new Envelope<>(guildService.create(guild));
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

}
