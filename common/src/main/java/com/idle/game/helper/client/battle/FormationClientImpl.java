package com.idle.game.helper.client.battle;

import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION;
import com.idle.game.model.Formation;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class FormationClientImpl implements FormationClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<Formation> findById(String id) {
        Formation ret = (Formation) redisTemplate.boundValueOps(FORMATION_FIND_BY_ID + id).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope(null);
        }
    }

    @Override
    public Envelope<Formation> findByUserIdAndFormationAllocation(String userId, String fa) {
        Formation ret = (Formation) redisTemplate.boundValueOps(FORMATION_FIND_BY_USER_ID_AND_FORMATION_ALLOCATION + userId + fa).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope(null);
        }
    }

}
