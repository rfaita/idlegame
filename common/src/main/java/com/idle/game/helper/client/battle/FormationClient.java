package com.idle.game.helper.client.battle;

import com.idle.game.model.Formation;
import com.idle.game.server.dto.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@FeignClient(name = "battle-service", path = "/formation", fallback = FormationClientImpl.class)
public interface FormationClient {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<Formation> findById(@PathVariable("id") String id);

    @RequestMapping(path = "/{userId}/{formationAllocation}", method = RequestMethod.GET)
    public Envelope<Formation> findByUserIdAndFormationAllocation(@PathVariable("userId") String userId, @PathVariable("formationAllocation") String fa);
}
