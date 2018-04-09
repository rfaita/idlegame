package com.idle.game.server.service;

import static com.idle.game.constant.ResourceConstants.defaultResources;
import static com.idle.game.core.constant.IdleConstants.*;
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
        long secondsHour;
        long secondsDay;
        if (playerResource == null) {
            playerResource = new UserResource(userId, defaultResources());
            seconds = 5;
            secondsHour = 0;
            secondsDay = 0;
        } else {
            seconds = DateUtil.secondsFrom(playerResource.getLastTimeResourcesCollected());
            secondsHour = DateUtil.secondsFrom(playerResource.getLastTimeResourcesCollectedHour());
            secondsDay = DateUtil.secondsFrom(playerResource.getLastTimeResourcesCollectedDay());
        }

        if (seconds >= 5) {
            if (seconds > SECONDS_IN_HOUR * 8) {
                seconds = SECONDS_IN_HOUR * 8;
            }
            playerResource.setLastTimeResourcesCollected(new Date());
            playerResource.computeResoucers(seconds);
            playerResource = playerRepositoryRepository.save(playerResource);
        }

        if (secondsHour >= SECONDS_IN_HOUR) {
            if (secondsHour > SECONDS_IN_HOUR * 8) {
                secondsHour = SECONDS_IN_HOUR * 8;
            }
            playerResource.setLastTimeResourcesCollectedHour(new Date());
            playerResource.computeHourResoucers((Long) secondsHour / SECONDS_IN_HOUR);
            playerResource = playerRepositoryRepository.save(playerResource);
        }

        if (secondsDay >= SECONDS_IN_DAY) {
            if (secondsDay > SECONDS_IN_DAY * 7) {
                secondsDay = SECONDS_IN_DAY * 7;
            }
            playerResource.setLastTimeResourcesCollectedDay(new Date());
            playerResource.computeDayResoucers((Long) secondsDay / SECONDS_IN_DAY);
            playerResource = playerRepositoryRepository.save(playerResource);
        }

        return playerResource;

    }

}
