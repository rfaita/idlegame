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

        UserResource playerResource = computeResources(userId);

        playerResource.addResources(resources);

        webSocketMessagingTemplate.convertAndSend(
                Destination.resourceRefresh(userId), playerResource);

        return playerRepositoryRepository.save(playerResource);

    }

    public UserResource useResources(String userId, List<Resource> resources) {

        UserResource userResource = computeResources(userId);

        userResource.useResources(resources);

        webSocketMessagingTemplate.convertAndSend(
                Destination.resourceRefresh(userId), userResource);

        return playerRepositoryRepository.save(userResource);

    }

    public UserResource computeResources(String userId) {

        UserResource userResource = playerRepositoryRepository.findByUserId(userId);

        long seconds;
        long secondsHour;
        long secondsDay;
        if (userResource == null) {
            userResource = new UserResource(userId, defaultResources());
            seconds = 5;
            secondsHour = 0;
            secondsDay = 0;
        } else {
            seconds = DateUtil.secondsFrom(userResource.getLastTimeResourcesCollected());
            secondsHour = DateUtil.secondsFrom(userResource.getLastTimeResourcesCollectedHour());
            secondsDay = DateUtil.secondsFrom(userResource.getLastTimeResourcesCollectedDay());
        }

        if (seconds >= 5) {
            if (seconds > SECONDS_IN_HOUR * 8) {
                seconds = SECONDS_IN_HOUR * 8;
            }
            userResource.setLastTimeResourcesCollected(new Date());
            userResource.computeResoucers(seconds);
            userResource = playerRepositoryRepository.save(userResource);
        }

        if (secondsHour >= SECONDS_IN_HOUR) {
            if (secondsHour > SECONDS_IN_HOUR * 8) {
                secondsHour = SECONDS_IN_HOUR * 8;
            }
            userResource.setLastTimeResourcesCollectedHour(new Date());
            userResource.computeHourResoucers((Long) secondsHour / SECONDS_IN_HOUR);
            userResource = playerRepositoryRepository.save(userResource);
        }

        if (secondsDay >= SECONDS_IN_DAY) {
            if (secondsDay > SECONDS_IN_DAY * 7) {
                secondsDay = SECONDS_IN_DAY * 7;
            }
            userResource.setLastTimeResourcesCollectedDay(new Date());
            userResource.computeDayResoucers((Long) secondsDay / SECONDS_IN_DAY);
            userResource = playerRepositoryRepository.save(userResource);
        }

        return userResource;

    }

}
