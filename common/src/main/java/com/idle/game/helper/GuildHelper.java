package com.idle.game.helper;

import com.idle.game.helper.cb.GuildCircuitBreakerService;
import com.idle.game.model.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class GuildHelper {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private GuildCircuitBreakerService guildCircuitBreakerService;

    public Guild getGuildById(String id) {
        return guildCircuitBreakerService.getGuildById(id, tokenHelper.getToken());
    }
}
