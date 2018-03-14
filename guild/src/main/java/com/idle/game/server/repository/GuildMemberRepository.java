package com.idle.game.server.repository;

import com.idle.game.model.GuildMember;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface GuildMemberRepository extends MongoRepository<GuildMember, String> {

    List<GuildMember> findAllByGuildAndAccepted(String guild, Boolean accepted);

    GuildMember findByGuildAndUserMember(String guild, String userMember);
    
    GuildMember findByUserMemberAndAccepted(String userMember, Boolean accepted);

    GuildMember findByGuildAndIdAndAccepted(String guild, String id, Boolean accepted);

    GuildMember findByGuildAndId(String guild, String id);

}
