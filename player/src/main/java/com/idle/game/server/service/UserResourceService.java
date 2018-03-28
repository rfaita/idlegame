package com.idle.game.server.service;

import static com.idle.game.constant.ResourceConstants.defaultResources;
import com.idle.game.model.UserResource;
import com.idle.game.model.Resource;
import com.idle.game.server.util.Destination;
import com.idle.game.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.idle.game.server.repository.UserResourceRepository;

@Service
public class UserResourceService {

    @Autowired
    private UserResourceRepository playerRepositoryRepository;

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    public UserResource addResources(String userId, List<Resource> resources) {

        UserResource playerResource = playerRepositoryRepository.findByUserId(userId);

        if (playerResource == null) {
            playerResource = new UserResource(userId, defaultResources());
        }

        playerResource.addResources(resources);

        webSocketMessagingTemplate.convertAndSend(
                Destination.resourceRefresh(userId), playerResource);

        return playerRepositoryRepository.save(playerResource);

    }

    public UserResource useResources(String userId, List<Resource> resources) {

        UserResource playerResource = playerRepositoryRepository.findByUserId(userId);

        if (playerResource == null) {
            playerResource = new UserResource(userId, defaultResources());
        }

        playerResource.useResources(resources);

        webSocketMessagingTemplate.convertAndSend(
                Destination.resourceRefresh(userId), playerResource);

        return playerRepositoryRepository.save(playerResource);

    }

    public UserResource computeResources(String userId) {

        UserResource playerResource = playerRepositoryRepository.findByUserId(userId);

        long seconds;
        if (playerResource == null) {
            playerResource = new UserResource(userId, defaultResources());
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

    }

}
