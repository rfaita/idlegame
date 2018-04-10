package com.idle.game.helper.client.hero;

import static com.idle.game.constant.URIConstants.HERO__FIND_ALL_BY_USER_ID;
import static com.idle.game.constant.URIConstants.HERO__ROLL;
import com.idle.game.model.Hero;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "hero-client", url = "${idle.url.hero}", fallback = HeroClientImpl.class)
public interface HeroClient {

    @RequestMapping(path = "/" + HERO__FIND_ALL_BY_USER_ID + "/{userId}", method = RequestMethod.GET)
    public Envelope<List<Hero>> findAllByUserId(@PathVariable("userId") String userId);

    @RequestMapping(path = "/" + HERO__FIND_ALL_BY_USER_ID + "/{userId}/{heroTypeId}", method = RequestMethod.GET)
    public Envelope<List<Hero>> findAllByUserIdAndHeroTypeId(@PathVariable("userId") String userId, @PathVariable("heroTypeId") String heroTypeId);

    @RequestMapping(path = "/" + HERO__FIND_ALL_BY_USER_ID + "/{userId}/{heroTypeId}/{quality}", method = RequestMethod.GET)
    public Envelope<List<Hero>> findAllByUserIdAndHeroTypeIdAndQuality(@PathVariable("userId") String userId,
            @PathVariable("quality") String quality,
            @PathVariable("heroTypeId") String heroTypeId);

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<Hero> findById(@PathVariable("id") String id);

    @RequestMapping(path = "/" + HERO__ROLL + "/{lootRollId}", method = RequestMethod.GET)
    public Envelope<Hero> rollHero(@PathVariable("lootRollId") String lootRollId);

}
