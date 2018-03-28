package com.idle.game.server.service;

import com.idle.game.helper.client.guild.GuildClient;
import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.model.Guild;
import com.idle.game.model.GuildMember;
import com.idle.game.model.GuildMemberType;
import com.idle.game.model.Mail;
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
    private GuildClient guildClient;

    @Autowired
    private MailClient mailClient;

    public List<GuildMember> getGuildMembers(String guildId) {
        return guildMemberRepository.findAllByGuildIdAndAccepted(guildId, Boolean.TRUE);
    }

    public List<GuildMember> getGuildMembersRequests(String guildId) {
        return guildMemberRepository.findAllByGuildIdAndAccepted(guildId, Boolean.FALSE);
    }

    public void createAdmin(String userId, String userNickName, String guildId) {

        Guild guild = guildClient.findById(guildId).getData();
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        if (!userId.equals(guild.getOwnerUserId())) {
            throw new ValidationException("you.can.not.do.that");
        }

        GuildMember guildMemberAlready = guildMemberRepository.findByGuildIdAndUserId(guild.getId(), userId);

        if (guildMemberAlready != null) {
            throw new ValidationException("guild.member.admin.already.created");
        }

        GuildMember member = new GuildMember(guild.getId(), userId, userNickName);

        member.setAccepted(Boolean.TRUE);
        member.setType(GuildMemberType.ADMIN);
        member.setSince(new Date());

        guildMemberRepository.save(member);
    }

    public void sendGuildMemberRequest(String guildId, String userId, String userNickName) {

        Guild guild = guildClient.findById(guildId).getData();
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember guildMemberAlready = findByUserId(userId);

        if (guildMemberAlready != null) {
            throw new ValidationException("guild.member.already");
        }

        guildMemberAlready = guildMemberRepository.findByGuildIdAndUserId(guild.getId(), userId);

        if (guildMemberAlready != null) {
            throw new ValidationException("you.already.send.a.request.to.this.guild");
        }

        GuildMember friend = new GuildMember(guildId, userId, userNickName);

        guildMemberRepository.save(friend);

        Mail mail = new Mail();
        mail.setToUserId(guild.getOwnerUserId());
        mail.setText("guild.request");

        mailClient.sendPrivateMail(mail);

    }

    public GuildMember findByUserId(String userId) {
        return guildMemberRepository.findByUserIdAndAccepted(userId, Boolean.TRUE);
    }

    public void promoteGuildMember(String userId, String memberId) {
        changeGuildMemberType(userId, memberId, Boolean.TRUE);
    }

    public void demoteGuildMember(String userId, String memberId) {
        changeGuildMemberType(userId, memberId, Boolean.FALSE);
    }

    private void changeGuildMemberType(String userId, String memberId, Boolean promote) {

        GuildMember myGuildMember = findByUserId(userId);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!myGuildMember.getType().canPromote()) {
            throw new ValidationException("you.don.not.have.permission.to.promote.members");
        }

        Guild guild = guildClient.findById(myGuildMember.getGuildId()).getData();
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

        GuildMember myGuildMember = findByUserId(user);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!myGuildMember.getType().canAccept()) {
            throw new ValidationException("you.don.not.have.permission.to.accept.new.members");
        }

        Guild guild = guildClient.findById(myGuildMember.getGuildId()).getData();
        if (guild == null) {
            throw new ValidationException("guild.not.found");
        }

        GuildMember request = guildMemberRepository.findByGuildIdAndIdAndAccepted(guild.getId(), memberRequestId, Boolean.FALSE);

        if (request != null) {

            GuildMember guildMemberAlready = guildMemberRepository.findByUserIdAndAccepted(request.getUserId(), Boolean.TRUE);

            if (guildMemberAlready != null) {
                throw new ValidationException("user.already.in.another.guild");
            }

            Date acceptedDate = new Date();

            request.setAccepted(Boolean.TRUE);
            request.setSince(acceptedDate);

            guildMemberRepository.save(request);

            Mail mail = new Mail();
            mail.setToUserId(request.getUserId());
            mail.setText("guild.request.accepted");

            mailClient.sendPrivateMail(mail);

        } else {
            throw new ValidationException("guild.member.request.not.found");
        }

    }

    public void removeGuildMember(String userId, String memberId) {

        GuildMember myGuildMember = findByUserId(userId);

        if (myGuildMember == null) {
            throw new ValidationException("your.guild.not.found");
        }

        if (!memberId.equals(myGuildMember.getId()) && !myGuildMember.getType().canKick()) {
            throw new ValidationException("you.don.not.have.permission.to.kick.members");
        }

        Guild guild = guildClient.findById(myGuildMember.getGuildId()).getData();
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
            mail.setToUserId(member.getUserId());
            if (memberId.equals(myGuildMember.getId())) {
                mail.setText("you.leave.from.your.guild");
            } else if (member.getAccepted()) {
                mail.setText("guild.kick.you");
            } else {
                mail.setText("guild.reject.you");
            }

            mailClient.sendPrivateMail(mail);

        } else {
            throw new ValidationException("guild.member.not.found");
        }

    }

}
