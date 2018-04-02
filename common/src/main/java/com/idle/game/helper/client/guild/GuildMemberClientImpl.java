package com.idle.game.helper.client.guild;

import com.idle.game.model.GuildMember;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class GuildMemberClientImpl implements GuildMemberClient {

    @Override
    public void createAdmin(String guildId) {
        throw new ValidationException("guild.member.service.is.down");
    }

    @Override
    public Envelope<GuildMember> myGuildMember() {
        return new Envelope("guild.member.service.is.down");
    }

    @Override
    public Envelope<GuildMember> getGuildMember(String userMemberId) {
        return new Envelope("guild.member.service.is.down");
    }

    @Override
    public Envelope<List<GuildMember>> getGuildMembers(String guildId) {
        return new Envelope("guild.member.service.is.down");
    }

    @Override
    public Envelope<List<GuildMember>> getGuildMembersRequests(String guildId) {
        return new Envelope("guild.member.service.is.down");
    }

}
