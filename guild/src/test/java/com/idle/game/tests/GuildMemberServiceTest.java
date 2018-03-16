package com.idle.game.tests;

import com.idle.game.helper.GuildHelper;
import com.idle.game.helper.MailHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.GuildMember;
import com.idle.game.model.Mail;
import com.idle.game.server.service.GuildMemberService;
import static com.idle.game.tests.helper.TestHelper.*;
import javax.validation.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.idle.game.server.repository.GuildMemberRepository;

/**
 *
 * @author rafael
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuildMemberServiceTest {

    @MockBean
    private GuildMemberRepository guildMemberRepository;

    @MockBean
    private PlayerHelper playerHelper;

    @MockBean
    private GuildHelper guildHelper;

    @MockBean
    private MailHelper mailHelper;

    @Autowired
    private GuildMemberService guildMemberService;

    @Rule
    public ExpectedException exceptionExpect = ExpectedException.none();

    @Test
    public void testSendGuildMemberRequestGuildNotFound() {

        when(guildHelper.getGuildById("123")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.sendGuildMemberRequest("123", "456");

    }

    @Test
    public void testSendGuildMemberRequestAlreadyMember() {

        when(guildHelper.getGuildById("123")).thenReturn(createGuild("123"));

        when(guildMemberRepository.findByUserMemberIdAndAccepted("456", Boolean.TRUE)).thenReturn(createGuildMember("123", "456", "test456"));

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.already");

        guildMemberService.sendGuildMemberRequest("123", "456");

    }

    @Test
    public void testSendGuildMemberRequestPlayerNotFound() {

        when(guildHelper.getGuildById("123")).thenReturn(createGuild("123"));

        when(guildMemberRepository.findByUserMemberIdAndAccepted("123", Boolean.TRUE)).thenReturn(null);

        when(playerHelper.getPlayerByLinkedUser("456")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("player.not.found");

        guildMemberService.sendGuildMemberRequest("123", "456");

    }

    @Test
    public void testSendGuildMemberRequestSuccess() {

        when(guildHelper.getGuildById("123")).thenReturn(createGuild("123"));

        when(guildMemberRepository.findByGuildIdAndUserMemberId("123", "456")).thenReturn(null);

        when(playerHelper.getPlayerByLinkedUser("456")).thenReturn(createPlayer("456"));

        when(guildMemberRepository.save(any(GuildMember.class))).thenAnswer(createGuildMemberAnswerForSomeInput());

        doNothing().when(mailHelper).sendPrivateMail(any(Mail.class));

        guildMemberService.sendGuildMemberRequest("123", "456");

    }

    @Test
    public void testAcceptGuildMemberRequestYouGuildNotFound() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("your.guild.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestCantAccept() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("you.don.not.have.permission.to.accept.new.members");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestGuildNotFound() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestGuildMemberRequestNotFound() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(createGuild("A"));

        when(guildMemberRepository.findByGuildIdAndIdAndAccepted("321", "789", Boolean.FALSE)).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.request.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestSuccess() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(createGuild("A"));

        when(guildMemberRepository.findByGuildIdAndIdAndAccepted("A", "789", Boolean.FALSE)).thenReturn(createGuildMember("A", "456", "test456"));

        when(guildMemberRepository.save(any(GuildMember.class))).thenAnswer(createGuildMemberAnswerForSomeInput());

        doNothing().when(mailHelper).sendPrivateMail(any(Mail.class));

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testRemoveGuildMemberRequestYouGuildNotFound() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("your.guild.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberRequestCantAccept() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("you.don.not.have.permission.to.kick.members");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberGuildNotFound() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberGuildMemberNotFound() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(createGuild("A"));

        when(guildMemberRepository.findByGuildIdAndId("321", "789")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberSuccess() {

        when(guildMemberRepository.findByUserMemberIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildHelper.getGuildById("A")).thenReturn(createGuild("A"));

        when(guildMemberRepository.findByGuildIdAndId("A", "789")).thenReturn(createGuildMember("A", "456", "test456"));

        doNothing().when(guildMemberRepository).delete(any(GuildMember.class));
        
        doNothing().when(mailHelper).sendPrivateMail(any(Mail.class));

        guildMemberService.removeGuildMember("321", "789");

    }

}
