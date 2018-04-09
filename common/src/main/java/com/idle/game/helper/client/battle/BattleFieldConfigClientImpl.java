package com.idle.game.helper.client.battle;

import ch.qos.logback.core.subst.Token;
import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.battle.BattleFieldConfig;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class BattleFieldConfigClientImpl implements BattleFieldConfigClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private TokenHelper tokenHelper;

    @Override
    public Envelope<BattleFieldConfig> findByUserId() {
        BattleFieldConfig ret = (BattleFieldConfig) redisTemplate.boundValueOps(BATTLE_FIELD_CONFIG_FIND_BY_USER_ID + tokenHelper.getUserId()).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((BattleFieldConfig) null);
        }
    }

    @Override
    public Envelope<BattleFieldConfig> findByGuildId(String guildId) {
        BattleFieldConfig ret = (BattleFieldConfig) redisTemplate.boundValueOps(BATTLE_FIELD_CONFIG_FIND_BY_GUILD_ID + guildId).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope((BattleFieldConfig) null);
        }
    }

    @Override
    public Envelope<BattleFieldConfig> createByGuildId(String guildId) {
        return new Envelope("battle.field.config.service.is.down");
    }

    @Override
    public Envelope<BattleFieldConfig> upgradeMaxLayerSize(String guildId) {
        return new Envelope("battle.field.config.service.is.down");
    }

    @Override
    public Envelope<BattleFieldConfig> createByUserId() {
        return new Envelope("battle.field.config.service.is.down");
    }

    @Override
    public Envelope<BattleFieldConfig> upgradeMaxLayerSizeByUserId() {
        return new Envelope("battle.field.config.service.is.down");
    }

}
