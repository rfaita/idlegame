package com.idle.game.server.service;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import static com.idle.game.core.constant.HeroTypeInstances.*;
import com.idle.game.core.type.ActionType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.type.HeroType;
import com.idle.game.core.type.TargetType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author rafael
 */
@Singleton
public class HeroTypeService {

    private Map<UUID, HeroType> cache = new HashMap<>();

    @PostConstruct
    public void init() {

        cache.put(ARCHER.getUUID(), ARCHER);
        cache.put(BERZERKER.getUUID(), BERZERKER);
        cache.put(CHAMPION.getUUID(), CHAMPION);
        cache.put(GHOST.getUUID(), GHOST);
        cache.put(HIGHLANDER.getUUID(), HIGHLANDER);
        
    }

    public HeroType getHeroType(String uuid) {
        return cache.get(UUID.fromString(uuid));
    }

}
