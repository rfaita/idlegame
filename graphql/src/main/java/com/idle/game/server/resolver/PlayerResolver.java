package com.idle.game.server.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.helper.FormationHelper;
import com.idle.game.helper.FriendHelper;
import com.idle.game.helper.GuildMemberHelper;
import com.idle.game.helper.HeroHelper;
import com.idle.game.helper.HeroTypeHelper;
import com.idle.game.helper.ManualTokenHelper;
import com.idle.game.helper.cb.GuildCircuitBreakerService;
import com.idle.game.model.Formation;
import com.idle.game.model.Friend;
import com.idle.game.model.Guild;
import com.idle.game.model.GuildMember;
import com.idle.game.model.Hero;
import com.idle.game.model.HeroType;
import com.idle.game.model.Player;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class PlayerResolver implements GraphQLResolver<Player> {

    @Autowired
    private HeroHelper heroHelper;
    @Autowired
    private HeroTypeHelper heroTypeHelper;
    @Autowired
    private GuildCircuitBreakerService guildCircuitBreakerService;
    @Autowired
    private GuildMemberHelper guildMemberHelper;
    @Autowired
    private FriendHelper friendHelper;
    @Autowired
    private FormationHelper formationHelper;
    @Autowired
    private ManualTokenHelper manualTokenHelper;

    public Guild getGuild(Player player) {
        String token = manualTokenHelper.getToken();
        GuildMember myGuildMember = guildMemberHelper.getMyGuildMember(token);

        return guildCircuitBreakerService.getGuildById(myGuildMember.getGuildId(), token);
    }

    public List<Friend> getFriends(Player player) {
        String token = manualTokenHelper.getToken();
        return friendHelper.getFriends(token);
    }

    public Formation getFormation(Player player, String formationAllocation) {
        return formationHelper.getFormationByPlayerAndFormationAllocation(player.getId(), formationAllocation);
    }

    public List<Hero> getHeroes(Player player, String heroTypeName, String quality) {

        String token = manualTokenHelper.getToken();

        if (heroTypeName != null) {
            HeroType heroType = heroTypeHelper.getHeroTypeByName(heroTypeName, token);
            if (heroType != null) {
                if (quality != null) {
                    return heroHelper.getHeroesByPlayerIdAndHeroTypeIdAndQuality(player.getId(), heroType.getId(), HeroQuality.valueOf(quality), token);
                } else {
                    return heroHelper.getHeroesByPlayerIdAndHeroTypeId(player.getId(), heroType.getId(), token);
                }
            } else {
                return null;
            }

        } else {
            return heroHelper.getHeroesByPlayerId(player.getId(), token);
        }
    }

}
