package com.idle.game.server.service;

import com.idle.game.core.Action;
import com.idle.game.core.ActionEffect;
import com.idle.game.core.ActionType;
import com.idle.game.core.AtittudeType;
import com.idle.game.core.DamageType;
import com.idle.game.core.DistanceType;
import com.idle.game.core.HeroType;
import com.idle.game.core.TargetType;
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

        Action dmgAction = new Action();
        dmgAction.setMainActionEffect(new ActionEffect(ActionType.DMG, TargetType.LESS_LIFE, 100, 0, 0, DamageType.PHYSICAL, Boolean.FALSE));

        HeroType h = new HeroType();
        h.setDamageType(DamageType.PHYSICAL);
        h.setDistanceType(DistanceType.MELEE);
        h.setDefaultAction(dmgAction);
        h.setUUID(UUID.fromString("b6d04634-3a5d-11e7-a919-92ebcb67fe33"));

        cache.put(h.getUUID(), h);
        
        Action healAction = new Action();
        healAction.setMainActionEffect(new ActionEffect(ActionType.HEAL, TargetType.LESS_LIFE, 100, 0, 0, DamageType.MAGIC, Boolean.FALSE));

        HeroType h2 = new HeroType();
        h2.setDamageType(DamageType.MAGIC);
        h2.setDistanceType(DistanceType.RANGED);
        h2.setDefaultAction(healAction);
        h2.setUUID(UUID.fromString("ba47d020-3a67-11e7-a919-92ebcb67fe33"));

        cache.put(h2.getUUID(), h2);
    }

    public HeroType getHeroType(String uuid) {
        return cache.get(UUID.fromString(uuid));
    }

}
