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

    List<GuildMember> findAllByGuildIdAndAccepted(String guild, Boolean accepted);

    GuildMember findByGuildIdAndUserMemberId(String guild, String userMember);
    
    GuildMember findByUserMemberIdAndAccepted(String userMember, Boolean accepted);

    GuildMember findByGuildIdAndIdAndAccepted(String guild, String id, Boolean accepted);

    GuildMember findByGuildIdAndId(String guild, String id);

}
