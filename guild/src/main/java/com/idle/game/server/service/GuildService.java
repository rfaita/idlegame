package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.helper.client.guild.GuildMemberClient;
import com.idle.game.model.Guild;
import com.idle.game.model.GuildMember;
import com.idle.game.server.repository.GuildRepository;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GuildService {

    @Autowired
    private GuildRepository guildRepository;

    @Autowired
    private GuildMemberClient guildMemberClient;

    public Guild findByName(String name) {
        return guildRepository.findByName(name);
    }

    @Cacheable(value = GUILD_FIND_BY_ID, key = "'" + GUILD_FIND_BY_ID + "' + #id")
    public Guild findById(String id) {
        Optional<Guild> ret = guildRepository.findById(id);

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    public Guild myGuild() {

        GuildMember myGuildMember = guildMemberClient.myGuildMember().getData();

        if (myGuildMember == null) {
            return null;
        } else {
            return findById(myGuildMember.getGuildId());
        }

    }

    public Guild create(Guild guild) {

        Guild guildExist = guildRepository.findByOwnerUserId(guild.getOwnerUserId());
        if (guildExist != null) {
            throw new ValidationException("user.already.have.a.guild");
        }

        guildExist = findByName(guild.getName());
        if (guildExist != null) {
            throw new ValidationException("guild.name.already.rexist");
        }

        guild = this.guildRepository.save(guild);

        guildMemberClient.createAdmin(guild.getId());

        return guild;
    }

}
