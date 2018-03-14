package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.*;
import com.idle.game.helper.GuildMemberHelper;
import com.idle.game.model.Guild;
import com.idle.game.server.repository.GuildRepository;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GuildService {

    @Autowired
    private GuildRepository guildRepository;
    
    @Autowired
    private GuildMemberHelper guildMemberHelper;

    public Guild findByName(String name) {
        return guildRepository.findByName(name);
    }

    @Cacheable(value = GUILD_FIND_BY_ID, key = "'" + GUILD_FIND_BY_ID + "' + #id")
    public Guild findById(String id) {
        return guildRepository.findById(id);
    }

    public Guild create(Guild guild) {
        
        Guild guildExist = findByUserOwner(guild.getUserOwner());
        if (guildExist!=null) {
            throw new ValidationException("user.already.have.a.guild");
        }
                
        guildExist = findByName(guild.getName());
        if (guildExist!=null) {
            throw new ValidationException("guild.name.already.rexist");
        }
        
        guild = this.guildRepository.save(guild);
        
        guildMemberHelper.createAdmin();
        
        return guild;
    }

    @Cacheable(value = GUILD_FIND_BY_USER_OWNER, key = "'" + GUILD_FIND_BY_USER_OWNER + "' + #player")
    public Guild findByUserOwner(String player) {
        return guildRepository.findByUserOwner(player);
    }

}
