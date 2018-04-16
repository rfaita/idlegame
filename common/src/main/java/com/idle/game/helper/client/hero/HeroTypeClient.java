package com.idle.game.helper.client.hero;

import com.idle.game.constant.URIConstants;
import static com.idle.game.constant.URIConstants.HEROTYPE__FIND_ALL_BY_QUALITY;
import static com.idle.game.constant.URIConstants.HEROTYPE__FIND_BY_NAME;
import static com.idle.game.constant.URIConstants.HERO_TYPE;
import com.idle.game.model.HeroType;
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
@FeignClient(name = "hero-service", path = HERO_TYPE, fallback = HeroTypeClientImpl.class)
public interface HeroTypeClient {

    @RequestMapping(path = "/" + HEROTYPE__FIND_ALL_BY_QUALITY + "/{quality}", method = RequestMethod.GET)
    public Envelope<List<HeroType>> findAllByQuality(@PathVariable("quality") String quality);

    @RequestMapping(path = "/" + URIConstants.HEROTYPE__FIND_ALL_BY_FACTION + "/{faction}", method = RequestMethod.GET)
    public Envelope<List<HeroType>> findAllByFaction(@PathVariable("faction") String faction);

    @RequestMapping(path = "/" + URIConstants.HEROTYPE__FIND_ALL_BY_FACTION_AND_QUALITY + "/{faction}/{quality}", method = RequestMethod.GET)
    public Envelope<List<HeroType>> findAllByFactionAndQuality(@PathVariable("faction") String faction, @PathVariable("quality") String quality);

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Envelope<List<HeroType>> findAll();

    @RequestMapping(path = "/" + HEROTYPE__FIND_BY_NAME + "/{name}", method = RequestMethod.GET)
    public Envelope<HeroType> findByName(@PathVariable("name") String name);

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<HeroType> findById(@PathVariable("id") String id);
}
