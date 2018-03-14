package com.idle.game.server.service;

import static com.idle.game.constant.ResourceConstants.defaultResources;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.Player;
import com.idle.game.model.PlayerResource;
import com.idle.game.model.Resource;
import com.idle.game.server.repository.PlayerResourceRepository;
import com.idle.game.server.util.Destination;
import com.idle.game.util.DateUtil;
import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerResourceService {

    @Autowired
    private PlayerResourceRepository playerRepositoryRepository;

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private PlayerHelper playerHelper;

    public PlayerResource addResources(String linkedUser, List<Resource> resources) {

        Player player = playerHelper.getPlayerByLinkedUser(linkedUser);

        if (player != null) {

            PlayerResource playerResource = playerRepositoryRepository.findByPlayer(player.getId());

            if (playerResource == null) {
                playerResource = new PlayerResource(player.getId(), defaultResources());
            }

            playerResource.addResources(resources);

            webSocketMessagingTemplate.convertAndSend(
                    Destination.resourceRefresh(linkedUser), playerResource);

            return playerRepositoryRepository.save(playerResource);

        } else {
            throw new ValidationException("player.not.found");
        }

    }

    public PlayerResource useResources(String linkedUser, List<Resource> resources) {

        Player player = playerHelper.getPlayerByLinkedUser(linkedUser);

        if (player != null) {

            PlayerResource playerResource = playerRepositoryRepository.findByPlayer(player.getId());

            if (playerResource == null) {
                playerResource = new PlayerResource(player.getId(), defaultResources());
            }

            playerResource.useResources(resources);

            webSocketMessagingTemplate.convertAndSend(
                    Destination.resourceRefresh(linkedUser), playerResource);

            return playerRepositoryRepository.save(playerResource);

        } else {
            throw new ValidationException("player.not.found");
        }

    }

    public PlayerResource computeResources(String linkedUser) {

        Player player = playerHelper.getPlayerByLinkedUser(linkedUser);

        if (player != null) {

            PlayerResource playerResource = playerRepositoryRepository.findByPlayer(player.getId());

            long seconds;
            if (playerResource == null) {
                playerResource = new PlayerResource(player.getId(), defaultResources());
                seconds = 5;
            } else {
                seconds = DateUtil.secondsFrom(playerResource.getLastTimeResourcesCollected());
            }

            if (seconds >= 5) {
                if (seconds > 60 * 60 * 8) {
                    seconds = 60 * 60 * 8;
                }
                playerResource.setLastTimeResourcesCollected(new Date());
                playerResource.computeResoucers(seconds);
                return playerRepositoryRepository.save(playerResource);
            }

            return playerResource;
        } else {
            throw new ValidationException("player.not.found");
        }

    }

}
