package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.GUILD_MEMBER__CREATE_ADMIN;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.GuildMember;
import com.idle.game.model.GuildMemberType;
import com.idle.game.server.service.GuildMemberService;
import java.util.List;

@RestController
@RequestMapping("/guildMember")
public class GuildMemberRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private GuildMemberService guildMemberService;

    @RequestMapping(path = "/{guildId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<GuildMember>> getGuildMembers(@PathVariable("guildId") String guildId) {

        Envelope<List<GuildMember>> ret = new Envelope<>();
        ret.setData(guildMemberService.getGuildMembers(guildId));

        return ret;

    }

    @RequestMapping(path = "/{guildId}", method = RequestMethod.POST)
    public void sendGuildMemberRequest(@PathVariable("guildId") String guildId) {

        guildMemberService.sendGuildMemberRequest(guildId, tokenHelper.getSubject());
    }

    @RequestMapping(path = "/" + GUILD_MEMBER__CREATE_ADMIN, method = RequestMethod.POST)
    public void createAdmin() {

        guildMemberService.createAdmin(tokenHelper.getSubject());
    }

    @RequestMapping(path = "/{memberId}", method = RequestMethod.DELETE)
    public void removeGuildMember(@PathVariable("memberId") String memberId) {

        guildMemberService.removeGuildMember(tokenHelper.getSubject(), memberId);
    }

    @RequestMapping(path = "/{memberRequestId}", method = RequestMethod.PUT)
    public void acceptGuildMemberRequest(@PathVariable("memberRequestId") String memberRequestId) {

        guildMemberService.acceptGuildMemberRequest(tokenHelper.getSubject(), memberRequestId);
    }

    @RequestMapping(path = "/{memberId}/{type}", method = RequestMethod.PUT)
    public void promoteGuildMember(@PathVariable("memberId") String memberId,
            @PathVariable("type") String type) {

        guildMemberService.promoteGuildMember(tokenHelper.getSubject(), memberId, GuildMemberType.valueOf(type));
    }

}
