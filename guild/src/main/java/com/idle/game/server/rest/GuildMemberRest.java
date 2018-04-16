package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.GUILD_MEMBER;
import static com.idle.game.constant.URIConstants.GUILD_MEMBER__CREATE_ADMIN;
import static com.idle.game.constant.URIConstants.GUILD_MEMBER__FIND_BY_USER_MEMBER_ID;
import static com.idle.game.constant.URIConstants.GUILD_MEMBER__REQUESTS;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.GuildMember;
import com.idle.game.server.service.GuildMemberService;
import java.util.List;

@RestController
@RequestMapping(GUILD_MEMBER)
public class GuildMemberRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private GuildMemberService guildMemberService;

    @RequestMapping(path = "/" + GUILD_MEMBER__FIND_BY_USER_MEMBER_ID + "/{userMemberId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<GuildMember> getGuildMember(@PathVariable("userMemberId") String userMemberId) {

        Envelope<GuildMember> ret = new Envelope<>();
        ret.setData(guildMemberService.findByUserId(userMemberId));

        return ret;

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<GuildMember> myGuildMember() {

        Envelope<GuildMember> ret = new Envelope<>();
        ret.setData(guildMemberService.findByUserId(tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "/{guildId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<GuildMember>> getGuildMembers(@PathVariable("guildId") String guildId) {

        Envelope<List<GuildMember>> ret = new Envelope<>();
        ret.setData(guildMemberService.getGuildMembers(guildId));

        return ret;

    }

    @RequestMapping(path = "/" + GUILD_MEMBER__REQUESTS + "/{guildId}", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<GuildMember>> getGuildMembersRequests(@PathVariable("guildId") String guildId) {

        Envelope<List<GuildMember>> ret = new Envelope<>();
        ret.setData(guildMemberService.getGuildMembersRequests(guildId));

        return ret;

    }

    @RequestMapping(path = "/" + GUILD_MEMBER__CREATE_ADMIN + "/{guildId}", method = RequestMethod.POST)
    public void createAdmin(@PathVariable("guildId") String guildId) {

        guildMemberService.createAdmin(tokenHelper.getUserId(), tokenHelper.getNickName(), guildId);
    }

    @RequestMapping(path = "/{guildId}", method = RequestMethod.POST)
    public void sendGuildMemberRequest(@PathVariable("guildId") String guildId) {

        guildMemberService.sendGuildMemberRequest(guildId, tokenHelper.getUserId(), tokenHelper.getNickName());
    }

    @RequestMapping(path = "/{memberId}", method = RequestMethod.DELETE)
    public void removeGuildMember(@PathVariable("memberId") String memberId) {

        guildMemberService.removeGuildMember(tokenHelper.getUserId(), memberId);
    }

    @RequestMapping(path = "/{memberRequestId}", method = RequestMethod.PUT)
    public void acceptGuildMemberRequest(@PathVariable("memberRequestId") String memberRequestId) {

        guildMemberService.acceptGuildMemberRequest(tokenHelper.getUserId(), memberRequestId);
    }

    @RequestMapping(path = "/promote/{memberId}", method = RequestMethod.PUT)
    public void promoteGuildMember(@PathVariable("memberId") String memberId) {

        guildMemberService.promoteGuildMember(tokenHelper.getUserId(), memberId);
    }

    @RequestMapping(path = "/demote/{memberId}", method = RequestMethod.PUT)
    public void demoteGuildMember(@PathVariable("memberId") String memberId) {

        guildMemberService.demoteGuildMember(tokenHelper.getUserId(), memberId);
    }

}
