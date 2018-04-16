package com.idle.game.helper.client.battle;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.battle.pve.PveBattleFieldConfig;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class PveBattleFieldConfigClientImpl implements PveBattleFieldConfigClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Override
    public Envelope<PveBattleFieldConfig> findByUserId() {
        PveBattleFieldConfig ret = (PveBattleFieldConfig) redisTemplate.boundValueOps(PVE_BATTLE_FIELD_CONFIG_FIND_BY_USER_ID + tokenHelper.getUserId()).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((PveBattleFieldConfig) null);
        }
    }

    @Override
    public Envelope<PveBattleFieldConfig> create() {
        return new Envelope("battle.field.config.service.is.down");
    }

    @Override
    public Envelope<PveBattleFieldConfig> upgrade() {
        return new Envelope("battle.field.config.service.is.down");
    }

}
