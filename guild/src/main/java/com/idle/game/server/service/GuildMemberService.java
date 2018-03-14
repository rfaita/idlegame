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

        GuildMember guildMemberAlready = guildMemberRepository.findByUserMemberAndAccepted(user, Boolean.TRUE);

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
    
    public void promoteGuildMember(String user, String memberId, GuildMemberType type) {

        GuildMember myGuildMember = guildMemberRepository.findByUserMemberAndAccepted(user, Boolean.TRUE);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!myGuildMember.getType().canPromote()) {
            throw new ValidationException("you.don.not.have.permission.to.promote.members");
        }

        Guild guild = guildHelper.getGuildById(myGuildMember.getGuild());
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        if (type.getLevel() >= myGuildMember.getType().getLevel()) {
            throw new ValidationException("you.don.not.have.permission.to.promote.to.this.type");
        }

        GuildMember member = guildMemberRepository.findByGuildAndId(guild.getId(), memberId);

        if (member != null) {
            member.setType(type);
            guildMemberRepository.save(member);
        } else {
            throw new ValidationException("guild.member.not.found");
        }
    }

    public void acceptGuildMemberRequest(String user, String memberRequestId) {

        GuildMember myGuildMember = guildMemberRepository.findByUserMemberAndAccepted(user, Boolean.TRUE);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!myGuildMember.getType().canAccept()) {
            throw new ValidationException("you.don.not.have.permission.to.accept.new.members");
        }

        Guild guild = guildHelper.getGuildById(myGuildMember.getGuild());
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember request = guildMemberRepository.findByGuildAndIdAndAccepted(guild.getId(), memberRequestId, Boolean.FALSE);

        if (request != null) {

            GuildMember guildMemberAlready = guildMemberRepository.findByUserMemberAndAccepted(request.getUserMember(), Boolean.TRUE);

            if (guildMemberAlready != null) {
                throw new ValidationException("user.already.in.another.guild");
            }

            Date acceptedDate = new Date();

            request.setAccepted(Boolean.TRUE);
            request.setSince(acceptedDate);

            guildMemberRepository.save(request);

            Mail mail = new Mail();
            mail.setToUser(request.getUserMember());
            mail.setText("guild.request.accepted");

            mailHelper.sendPrivateMail(mail);

        } else {
            throw new ValidationException("guild.member.request.not.found");
        }

    }

    public void removeGuildMember(String user, String memberId) {

        GuildMember myGuildMember = guildMemberRepository.findByUserMemberAndAccepted(user, Boolean.TRUE);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!myGuildMember.getType().canKick()) {
            throw new ValidationException("you.don.not.have.permission.to.kick.members");
        }

        Guild guild = guildHelper.getGuildById(myGuildMember.getGuild());
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember member = guildMemberRepository.findByGuildAndId(guild.getId(), memberId);

        if (member != null) {
            guildMemberRepository.delete(member);

            Mail mail = new Mail();
            mail.setToUser(member.getUserMember());
            mail.setText("guild.kick.you");

            mailHelper.sendPrivateMail(mail);

        } else {
            throw new ValidationException("guild.member.not.found");
        }

    }

}
