package com.idle.game.helper.client.hero;

import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.HERO_TYPE_FIND_BY_NAME;
import com.idle.game.model.HeroType;
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
public class HeroTypeClientImpl implements HeroTypeClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<List<HeroType>> findAllByQuality(String quality) {
        return new Envelope();
    }

    @Override
    public Envelope<List<HeroType>> findAllByFaction(String faction) {
        return new Envelope();
    }

    @Override
    public Envelope<List<HeroType>> findAllByFactionAndQuality(String faction, String quality) {
        return new Envelope();
    }

    @Override
    public Envelope<List<HeroType>> findAll() {
        return new Envelope();
    }

    @Override
    public Envelope<HeroType> findByName(String name) {
        HeroType ret = (HeroType) redisTemplate.boundValueOps(HERO_TYPE_FIND_BY_NAME + name).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((HeroType) null);
        }
    }

    @Override
    public Envelope<HeroType> findById(String id) {
        HeroType ret = (HeroType) redisTemplate.boundValueOps(HERO_TYPE_FIND_BY_ID + id).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((HeroType) null);
        }
    }

}
