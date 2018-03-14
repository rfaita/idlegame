package com.idle.game.tests.helper;

import com.idle.game.model.Guild;
import com.idle.game.model.GuildMember;
import com.idle.game.model.GuildMemberType;
import com.idle.game.model.Player;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static Guild createGuild(String id) {
        Guild ret = new Guild();
        ret.setId(id);
        return ret;
    }

    public static GuildMember createMemberGuildMember(String guildId, String userMember, String userName) {
        GuildMember ret = createGuildMember(guildId, userMember, userName);
        ret.setType(GuildMemberType.MEMBER);
        return ret;
    }

    public static GuildMember createAdminGuildMember(String guildId, String userMember, String userName) {
        GuildMember ret = createGuildMember(guildId, userMember, userName);
        ret.setType(GuildMemberType.ADMIN);
        return ret;
    }

    public static GuildMember createGuildMember(String guildId, String userMember, String userName) {
        return new GuildMember(guildId, userMember, userMember);
    }

    public static Player createPlayer(String id) {
        Player p1 = new Player();
        p1.setId(id);
        p1.setLevel(1);
        p1.setLinkedUser(id);
        p1.setName("test" + id);

        return p1;
    }

    public static Answer<GuildMember> createGuildMemberAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (GuildMember) invocation.getArguments()[0];
        };
    }

}
