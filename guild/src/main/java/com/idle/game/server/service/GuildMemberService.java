package com.idle.game.server.service;

import com.idle.game.helper.GuildHelper;
import com.idle.game.helper.MailHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.Guild;
import com.idle.game.model.GuildMember;
import com.idle.game.model.GuildMemberType;
import com.idle.game.model.Mail;
import com.idle.game.model.Player;
import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.server.repository.GuildMemberRepository;

@Service
public class GuildMemberService {

    @Autowired
    private GuildMemberRepository guildMemberRepository;

    @Autowired
    private PlayerHelper playerHelper;

    @Autowired
    private GuildHelper guildHelper;

    @Autowired
    private MailHelper mailHelper;

    public List<GuildMember> getGuildMembers(String guild) {
        return guildMemberRepository.findAllByGuildAndAccepted(guild, Boolean.TRUE);
    }

    public void createAdmin(String user) {

        Guild guild = guildHelper.getGuildByUserOwner(user);
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        Player player = playerHelper.getPlayerByLinkedUser(user);
        if (player == null) {
            throw new ValidationException("player.not.found");
        }

        GuildMember guildMemberAlready = guildMemberRepository.findByGuildAndUserMember(guild.getId(), user);

        if (guildMemberAlready != null) {
            throw new ValidationException("guild.member.admin.already.created");
        }

        GuildMember member = new GuildMember(guild.getId(), user, player.getName());

        member.setAccepted(Boolean.TRUE);
        member.setType(GuildMemberType.ADMIN);
        member.setSince(new Date());

        guildMemberRepository.save(member);
    }

    public void sendGuildMemberRequest(String guildId, String user) {

        Guild guild = guildHelper.getGuildById(guildId);
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember guildMemberAlready = guildMemberRepository.findByGuildAndUserMember(guildId, user);

        if (guildMemberAlready != null) {
            throw new ValidationException("guild.member.already");
        }

        Player player = playerHelper.getPlayerByLinkedUser(user);
        if (player == null) {
            throw new ValidationException("player.not.found");
        }

        GuildMember friend = new GuildMember(guildId, user, player.getName());

        guildMemberRepository.save(friend);

        Mail mail = new Mail();
        mail.setToUser(guild.getUserOwner());
        mail.setText("guild.request");

        mailHelper.sendPrivateMail(mail);

    }

    public void acceptGuildMemberRequest(String user, String memberRequestId) {

        Guild guild = guildHelper.getGuildByUserOwner(user);
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember request = guildMemberRepository.findByGuildAndIdAndAccepted(guild.getId(), memberRequestId, Boolean.FALSE);

        if (request != null) {

            Date acceptedDate = new Date();

            request.setAccepted(Boolean.TRUE);
            request.setSince(acceptedDate);

            guildMemberRepository.save(request);

            Mail mail = new Mail();
            mail.setToUser(user);
            mail.setText("guild.request.accepted");

            mailHelper.sendPrivateMail(mail);

        } else {
            throw new ValidationException("guild.member.request.not.found");
        }

    }

    public void removeGuildMember(String user, String memberId) {

        Guild guild = guildHelper.getGuildByUserOwner(user);
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember member = guildMemberRepository.findByGuildAndId(guild.getId(), memberId);

        if (member != null) {
            guildMemberRepository.delete(member);
        } else {
            throw new ValidationException("guild.member.not.found");
        }

    }

}
