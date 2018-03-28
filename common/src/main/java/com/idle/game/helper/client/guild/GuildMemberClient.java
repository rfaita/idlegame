package com.idle.game.helper.client.guild;

import static com.idle.game.constant.URIConstants.GUILD_MEMBER__CREATE_ADMIN;
import static com.idle.game.constant.URIConstants.GUILD_MEMBER__FIND_BY_USER_MEMBER_ID;
import static com.idle.game.constant.URIConstants.GUILD_MEMBER__REQUESTS;
import com.idle.game.model.GuildMember;
import com.idle.game.server.dto.Envelope;
import java.util.List;
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
@FeignClient(name = "guildMember-client", url = "${idle.url.guildMember}")
public interface GuildMemberClient {

    @RequestMapping(path = "/" + GUILD_MEMBER__CREATE_ADMIN + "/{guildId}", method = RequestMethod.POST)
    public void createAdmin(@PathVariable("guildId") String guildId);

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Envelope<GuildMember> myGuildMember();

    @RequestMapping(path = "/" + GUILD_MEMBER__FIND_BY_USER_MEMBER_ID + "/{userMemberId}", method = RequestMethod.GET)
    public Envelope<GuildMember> getGuildMember(@PathVariable("userMemberId") String userMemberId);

    @RequestMapping(path = "/{guildId}", method = RequestMethod.GET)
    public Envelope<List<GuildMember>> getGuildMembers(@PathVariable("guildId") String guildId);

    @RequestMapping(path = "/" + GUILD_MEMBER__REQUESTS + "/{guildId}", method = RequestMethod.GET)
    public Envelope<List<GuildMember>> getGuildMembersRequests(@PathVariable("guildId") String guildId);
}
