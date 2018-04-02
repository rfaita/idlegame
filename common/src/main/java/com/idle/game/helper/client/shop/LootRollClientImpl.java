package com.idle.game.helper.client.shop;

import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.dto.Envelope;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class LootRollClientImpl implements LootRollClient {

    @Override
    public Envelope<LootRoll> buyById(String id) {
        return new Envelope("loot.roll.service.is.down");
    }

}
