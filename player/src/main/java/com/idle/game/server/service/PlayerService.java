package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.PLAYER_FIND_BY_LINKED_USER;
import com.idle.game.model.mongo.Player;
import com.idle.game.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player findByName(String name) {
        return playerRepository.findByName(name);
    }

    public Player findById(String id) {
        return playerRepository.findById(id);
    }

    @Cacheable(value = PLAYER_FIND_BY_LINKED_USER, key = "'" + PLAYER_FIND_BY_LINKED_USER + "' + #linkedUser")
    public Player findByLinkedUser(String linkedUser) {
        return playerRepository.findByLinkedUser(linkedUser);
    }

}
