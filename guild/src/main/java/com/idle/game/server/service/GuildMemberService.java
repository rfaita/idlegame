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

    public List<GuildMember> getGuildMembers(String guildId) {
        return guildMemberRepository.findAllByGuildIdAndAccepted(guildId, Boolean.TRUE);
    }

    public List<GuildMember> getGuildMembersRequests(String guildId) {
        return guildMemberRepository.findAllByGuildIdAndAccepted(guildId, Boolean.FALSE);
    }

    public void createAdmin(String userId, String guildId) {

        Guild guild = guildHelper.getGuildById(guildId);
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        if (!userId.equals(guild.getUserOwnerId())) {
            throw new ValidationException("you.can.not.do.that");
        }

        Player player = playerHelper.getPlayerByLinkedUser(userId);
        if (player == null) {
            throw new ValidationException("player.not.found");
        }

        GuildMember guildMemberAlready = guildMemberRepository.findByGuildIdAndUserMemberId(guild.getId(), userId);

        if (guildMemberAlready != null) {
            throw new ValidationException("guild.member.admin.already.created");
        }

        GuildMember member = new GuildMember(guild.getId(), userId, player.getName());

        member.setAccepted(Boolean.TRUE);
        member.setType(GuildMemberType.ADMIN);
        member.setSince(new Date());

        guildMemberRepository.save(member);
    }

    public void sendGuildMemberRequest(String guildId, String userId) {

        Guild guild = guildHelper.getGuildById(guildId);
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember guildMemberAlready = findByUserMemberId(userId);

        if (guildMemberAlready != null) {
            throw new ValidationException("guild.member.already");
        }

        guildMemberAlready = guildMemberRepository.findByGuildIdAndUserMemberId(guild.getId(), userId);

        if (guildMemberAlready != null) {
            throw new ValidationException("you.already.send.a.request.to.this.guild");
        }

        Player player = playerHelper.getPlayerByLinkedUser(userId);
        if (player == null) {
            throw new ValidationException("player.not.found");
        }

        GuildMember friend = new GuildMember(guildId, userId, player.getName());

        guildMemberRepository.save(friend);

        Mail mail = new Mail();
        mail.setToUser(guild.getUserOwnerId());
        mail.setText("guild.request");

        mailHelper.sendPrivateMail(mail);

    }

    public GuildMember findByUserMemberId(String userId) {
        return guildMemberRepository.findByUserMemberIdAndAccepted(userId, Boolean.TRUE);
    }

    public void promoteGuildMember(String userId, String memberId) {
        changeGuildMemberType(userId, memberId, Boolean.TRUE);
    }

    public void demoteGuildMember(String userId, String memberId) {
        changeGuildMemberType(userId, memberId, Boolean.FALSE);
    }

    private void changeGuildMemberType(String userId, String memberId, Boolean promote) {

        GuildMember myGuildMember = findByUserMemberId(userId);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!myGuildMember.getType().canPromote()) {
            throw new ValidationException("you.don.not.have.permission.to.promote.members");
        }

        Guild guild = guildHelper.getGuildById(myGuildMember.getGuildId());
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember member = guildMemberRepository.findByGuildIdAndId(guild.getId(), memberId);
        if (member != null) {

            GuildMemberType type;
            if (promote) {
                type = member.getType().getNextLevel();
                if (type.getLevel() >= myGuildMember.getType().getLevel()) {
                    throw new ValidationException("you.don.not.have.permission.to.promote.to.this.type");
                }
            } else {
                type = member.getType().getLowerLevel();
                if (type.getLevel() >= myGuildMember.getType().getLevel()) {
                    throw new ValidationException("you.don.not.have.permission.to.demote.to.this.type");
                }
            }

            member.setType(type);
            guildMemberRepository.save(member);
        } else {
            throw new ValidationException("guild.member.not.found");
        }
    }

    public void acceptGuildMemberRequest(String user, String memberRequestId) {

        GuildMember myGuildMember = findByUserMemberId(user);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!myGuildMember.getType().canAccept()) {
            throw new ValidationException("you.don.not.have.permission.to.accept.new.members");
        }

        Guild guild = guildHelper.getGuildById(myGuildMember.getGuildId());
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember request = guildMemberRepository.findByGuildIdAndIdAndAccepted(guild.getId(), memberRequestId, Boolean.FALSE);

        if (request != null) {

            GuildMember guildMemberAlready = guildMemberRepository.findByUserMemberIdAndAccepted(request.getUserMemberId(), Boolean.TRUE);

            if (guildMemberAlready != null) {
                throw new ValidationException("user.already.in.another.guild");
            }

            Date acceptedDate = new Date();

            request.setAccepted(Boolean.TRUE);
            request.setSince(acceptedDate);

            guildMemberRepository.save(request);

            Mail mail = new Mail();
            mail.setToUser(request.getUserMemberId());
            mail.setText("guild.request.accepted");

            mailHelper.sendPrivateMail(mail);

        } else {
            throw new ValidationException("guild.member.request.not.found");
        }

    }

    public void removeGuildMember(String userId, String memberId) {

        GuildMember myGuildMember = findByUserMemberId(userId);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!memberId.equals(myGuildMember.getId()) && !myGuildMember.getType().canKick()) {
            throw new ValidationException("you.don.not.have.permission.to.kick.members");
        }

        Guild guild = guildHelper.getGuildById(myGuildMember.getGuildId());
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember member = guildMemberRepository.findByGuildIdAndId(guild.getId(), memberId);

        if (member != null) {

            if (!memberId.equals(myGuildMember.getId()) && myGuildMember.getType().getLevel() <= member.getType().getLevel()) {
                throw new ValidationException("you.don.not.have.permission.to.kick.this.member");
            }

            guildMemberRepository.delete(member);

            Mail mail = new Mail();
            mail.setToUser(member.getUserMemberId());
            if (memberId.equals(myGuildMember.getId())) {
                mail.setText("you.leave.from.your.guild");
            } else if (member.getAccepted()) {
                mail.setText("guild.kick.you");
            } else {
                mail.setText("guild.reject.you");
            }

            mailHelper.sendPrivateMail(mail);

        } else {
            throw new ValidationException("guild.member.not.found");
        }

    }

}
