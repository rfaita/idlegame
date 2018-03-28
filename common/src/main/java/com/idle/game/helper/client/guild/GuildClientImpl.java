package com.idle.game.helper.client.guild;

import static com.idle.game.constant.CacheConstants.GUILD_FIND_BY_ID;
import com.idle.game.model.Guild;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class GuildClientImpl implements GuildClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<Guild> findById(String id) {
        Guild ret = (Guild) redisTemplate.boundValueOps(GUILD_FIND_BY_ID + id).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope(null);
        }
    }

}
