package com.idle.game.helper.client.battle;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.model.battle.BattleField;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class BattleFieldClientImpl implements BattleFieldClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<BattleField> findById(String id) {
        BattleField ret = (BattleField) redisTemplate.boundValueOps(BATTLE_FIELD_FIND_BY_ID + id).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((BattleField) null);
        }
    }

    @Override
    public Envelope<BattleField> update(BattleField battleField) {
        return new Envelope();
    }

}
