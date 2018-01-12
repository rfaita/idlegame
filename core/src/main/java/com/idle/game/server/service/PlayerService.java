package com.idle.game.server.service;

import com.idle.game.model.mongo.Player;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Envelope<Player> doGet(@PathVariable("id") String id) {

        Envelope<Player> ret = new Envelope<>();
        ret.setData(playerRepository.findById(id));

        return ret;

    }

}
