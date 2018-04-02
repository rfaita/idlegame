package com.idle.game.helper.client.battle;

import static com.idle.game.constant.CacheConstants.BATTLE_HERO_FIND_BY_ID;
import com.idle.game.core.hero.BattleHero;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class BattleHeroClientImpl implements BattleHeroClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<BattleHero> getBattleHero(String id) {
        BattleHero ret = (BattleHero) redisTemplate.boundValueOps(BATTLE_HERO_FIND_BY_ID + id).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((BattleHero) null);
        }
    }

}
