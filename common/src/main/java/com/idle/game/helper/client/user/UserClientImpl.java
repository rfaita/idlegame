package com.idle.game.helper.client.user;

import static com.idle.game.constant.CacheConstants.USER_FIND_BY_ID;
import com.idle.game.model.User;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class UserClientImpl implements UserClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Envelope<User> findById(String userId) {
        User ret = (User) redisTemplate.boundValueOps(USER_FIND_BY_ID + userId).get();
        if (ret != null) {
            return new Envelope(ret);
        } else {
            return new Envelope(null);
        }
    }

}
