package com.idle.game.tests;

import com.idle.game.helper.client.guild.GuildClient;
import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.model.GuildMember;
import com.idle.game.model.Mail;
import com.idle.game.server.dto.Envelope;
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

    @MockBean(name = "default")
    private GuildMemberRepository guildMemberRepository;

    @MockBean(name = "default")
    private GuildClient guildClient;

    @MockBean(name = "default")
    private MailClient mailClient;

    @Autowired
    private GuildMemberService guildMemberService;

    @Rule
    public ExpectedException exceptionExpect = ExpectedException.none();

    @Test
    public void testSendGuildMemberRequestGuildNotFound() {

        when(guildClient.findById("123")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.sendGuildMemberRequest("123", "456", "test1");

    }

    @Test
    public void testSendGuildMemberRequestAlreadyMember() {

        when(guildClient.findById("123")).thenReturn(new Envelope(createGuild("123")));

        when(guildMemberRepository.findByUserIdAndAccepted("456", Boolean.TRUE)).thenReturn(createGuildMember("123", "456", "test456"));

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.already");

        guildMemberService.sendGuildMemberRequest("123", "456", "test456");

    }

    @Test
    public void testSendGuildMemberRequestSuccess() {

        when(guildClient.findById("123")).thenReturn(new Envelope(createGuild("123")));

        when(guildMemberRepository.findByGuildIdAndUserId("123", "456")).thenReturn(null);

        when(guildMemberRepository.save(any(GuildMember.class))).thenAnswer(createGuildMemberAnswerForSomeInput());

        doNothing().when(mailClient).sendPrivateMail(any(Mail.class));

        guildMemberService.sendGuildMemberRequest("123", "456", "test");

    }

    @Test
    public void testAcceptGuildMemberRequestYouGuildNotFound() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("your.guild.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestCantAccept() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("you.don.not.have.permission.to.accept.new.members");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestGuildNotFound() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestGuildMemberRequestNotFound() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(new Envelope(createGuild("A")));

        when(guildMemberRepository.findByGuildIdAndIdAndAccepted("321", "789", Boolean.FALSE)).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.request.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestSuccess() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(new Envelope(createGuild("A")));

        when(guildMemberRepository.findByGuildIdAndIdAndAccepted("A", "789", Boolean.FALSE)).thenReturn(createGuildMember("A", "456", "test456"));

        when(guildMemberRepository.save(any(GuildMember.class))).thenAnswer(createGuildMemberAnswerForSomeInput());

        doNothing().when(mailClient).sendPrivateMail(any(Mail.class));

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testRemoveGuildMemberRequestYouGuildNotFound() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("your.guild.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberRequestCantAccept() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("you.don.not.have.permission.to.kick.members");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberGuildNotFound() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberGuildMemberNotFound() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(new Envelope(createGuild("A")));

        when(guildMemberRepository.findByGuildIdAndId("321", "789")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberSuccess() {

        when(guildMemberRepository.findByUserIdAndAccepted("321", Boolean.TRUE)).thenReturn(createAdminGuildMember("A", "321", "test321"));

        when(guildClient.findById("A")).thenReturn(new Envelope(createGuild("A")));

        when(guildMemberRepository.findByGuildIdAndId("A", "789")).thenReturn(createGuildMember("A", "456", "test456"));

        doNothing().when(guildMemberRepository).delete(any(GuildMember.class));

        doNothing().when(mailClient).sendPrivateMail(any(Mail.class));

        guildMemberService.removeGuildMember("321", "789");

    }

}
