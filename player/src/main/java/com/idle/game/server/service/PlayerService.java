package com.idle.game.server.service;

import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.Resource;
import com.idle.game.server.repository.PlayerRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player findById(String id) {
        return playerRepository.findById(id);
    }

    public Player findByLinkedUser(String id) {
        return playerRepository.findByLinkedUser(id);
    }

    public Player useResources(String linkedUser, List<Resource> resources) {

        Player player = findByLinkedUser(linkedUser);

        player.userResources(resources);

        return playerRepository.save(player);

    }

    public Player computeResources(String linkedUser) {

        long seconds = 0l;

        Player player = findByLinkedUser(linkedUser);

        if (player != null) {

            if (player.getLastTimeResourcesCollected() != null) {
                LocalDateTime lastTime = LocalDateTime.ofInstant(player.getLastTimeResourcesCollected().toInstant(), ZoneId.systemDefault());

                seconds = lastTime.until(LocalDateTime.now(), ChronoUnit.SECONDS);
            }

            if (player.getLastTimeResourcesCollected() == null || seconds >= 5) {
                player.setLastTimeResourcesCollected(new Date());
                player.computeResoucers(seconds);
                return playerRepository.save(player);
            }

            return player;
        } else {
            throw new ValidationException("player.not.found");
        }

    }

}
