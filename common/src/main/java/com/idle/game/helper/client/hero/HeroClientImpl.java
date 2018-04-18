package com.idle.game.helper.client.hero;

import static com.idle.game.constant.CacheConstants.HERO_FIND_BY_ID;
import com.idle.game.model.Hero;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class HeroClientImpl implements HeroClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<List<Hero>> findAllByUserId(String userId) {
        return new Envelope();
    }

    @Override
    public Envelope<List<Hero>> findAllByUserIdAndHeroTypeId(String userId, String heroTypeId) {
        return new Envelope();
    }

    @Override
    public Envelope<List<Hero>> findAllByUserIdAndHeroTypeIdAndQuality(String userId, String quality, String heroTypeId) {
        return new Envelope();
    }

    @Override
    public Envelope<Hero> rollHero(String lootRollId) {
        return new Envelope();
    }

    @Override
    public Envelope<Hero> findById(String id) {
        Hero ret = (Hero) redisTemplate.boundValueOps(HERO_FIND_BY_ID + id).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((Hero) null);
        }
    }

}
