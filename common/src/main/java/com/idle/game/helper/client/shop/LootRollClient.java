package com.idle.game.helper.client.shop;

import static com.idle.game.constant.URIConstants.SHOP;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "shop-service", path = SHOP, fallback = LootRollClientImpl.class)
public interface LootRollClient {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<LootRoll> findById(@PathVariable("id") String id);

}
