package com.idle.game.helper.client.guild;

import com.idle.game.model.Guild;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rafael
 */
@Primary
@Qualifier(value = "default")
@FeignClient(name = "guild-client", url = "${idle.url.guild}", fallback = GuildClientImpl.class)
public interface GuildClient {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Envelope<Guild> findById(@PathVariable("id") String id);

}
