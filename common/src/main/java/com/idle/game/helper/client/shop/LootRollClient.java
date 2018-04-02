package com.idle.game.helper.client.shop;

import com.idle.game.helper.client.mail.*;
import static com.idle.game.constant.URIConstants.SHOP__BUY_LOOT_ROLL;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "loot-roll-client", url = "${idle.url.shop}", fallback = MailClientImpl.class)
public interface LootRollClient {

    @RequestMapping(path = "/" + SHOP__BUY_LOOT_ROLL + "/{id}", method = RequestMethod.GET)
    public Envelope<LootRoll> buyById(@PathVariable("id") String id);

}
