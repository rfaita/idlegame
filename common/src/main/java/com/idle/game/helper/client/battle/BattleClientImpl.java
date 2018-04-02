package com.idle.game.helper.client.battle;

import com.idle.game.core.battle.Battle;
import com.idle.game.server.dto.Envelope;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class BattleClientImpl implements BattleClient {

    @Override
    public Envelope<Battle> doBattle(String att, String def) {
        return new Envelope("battle.service.is.down");
    }

}
