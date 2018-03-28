package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.helper.client.battle.FormationClient;
import com.idle.game.helper.client.friend.FriendClient;
import com.idle.game.helper.client.guild.GuildClient;
import com.idle.game.helper.client.guild.GuildMemberClient;
import com.idle.game.helper.client.hero.HeroClient;
import com.idle.game.helper.client.hero.HeroTypeClient;
import com.idle.game.model.Formation;
import com.idle.game.model.Friend;
import com.idle.game.model.Guild;
import com.idle.game.model.GuildMember;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import com.idle.game.model.User;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class UserResolver implements GraphQLResolver<User> {

    @Autowired
    private HeroClient heroClient;
    @Autowired
    private HeroTypeClient heroTypeClient;
    @Autowired
    private GuildClient guildClient;
    @Autowired
    private GuildMemberClient guildMemberClient;
    @Autowired
    private FriendClient friendClient;
    @Autowired
    private FormationClient formationClient;

    public Guild getGuild(User user) {
        Envelope<GuildMember> guildMember = guildMemberClient.getGuildMember(user.getId());

        if (guildMember != null && guildMember.getData() != null) {

            return guildClient.findById(guildMember.getData().getGuildId()).getData();
        } else {
            return null;
        }
    }

    public List<Friend> getFriends(User user) {
        return friendClient.getFriends(user.getId()).getData();
    }

    public Formation getFormation(User user, String formationAllocation) {
        return formationClient.findByUserIdAndFormationAllocation(user.getId(), formationAllocation).getData();
    }

    public List<Hero> getHeroes(User user, String heroTypeName, String quality) {

        if (heroTypeName != null) {
            HeroType heroType = heroTypeClient.findByName(heroTypeName).getData();
            if (heroType != null) {
                if (quality != null) {
                    return heroClient.findAllByUserIdAndHeroTypeIdAndQuality(user.getId(), heroType.getId(), quality).getData();
                } else {
                    return heroClient.findAllByUserIdAndHeroTypeId(user.getId(), heroType.getId()).getData();
                }
            } else {
                return null;
            }

        } else {
            return heroClient.findAllByUserId(user.getId()).getData();
        }
    }

}
