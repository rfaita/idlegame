package com.idle.game.tests;

import com.idle.game.helper.GuildHelper;
import com.idle.game.helper.MailHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.Friend;
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

        when(guildMemberRepository.findByGuildAndUserMember("123", "456")).thenReturn(createGuildMember("123", "456", "test456"));

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.already");

        guildMemberService.sendGuildMemberRequest("123", "456");

    }

    @Test
    public void testSendGuildMemberRequestPlayerNotFound() {

        when(guildHelper.getGuildById("123")).thenReturn(createGuild("123"));

        when(guildMemberRepository.findByGuildAndUserMember("123", "456")).thenReturn(null);

        when(playerHelper.getPlayerByLinkedUser("456")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("player.not.found");

        guildMemberService.sendGuildMemberRequest("123", "456");

    }

    @Test
    public void testSendGuildMemberRequestSuccess() {

        when(guildHelper.getGuildById("123")).thenReturn(createGuild("123"));

        when(guildMemberRepository.findByGuildAndUserMember("123", "456")).thenReturn(null);

        when(playerHelper.getPlayerByLinkedUser("456")).thenReturn(createPlayer("456"));

        when(guildMemberRepository.save(any(GuildMember.class))).thenAnswer(createGuildMemberAnswerForSomeInput());

        doNothing().when(mailHelper).sendPrivateMail(any(Mail.class));

        guildMemberService.sendGuildMemberRequest("123", "456");

    }

    @Test
    public void testAcceptGuildMemberRequestGuildNotFound() {

        when(guildHelper.getGuildByUserOwner("321")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestGuildMemberRequestNotFound() {

        when(guildHelper.getGuildByUserOwner("321")).thenReturn(createGuild("321"));

        when(guildMemberRepository.findByGuildAndIdAndAccepted("321", "789", Boolean.FALSE)).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.request.not.found");

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testAcceptGuildMemberRequestSuccess() {

        when(guildHelper.getGuildByUserOwner("321")).thenReturn(createGuild("321"));

        when(guildMemberRepository.findByGuildAndIdAndAccepted("321", "789", Boolean.FALSE)).thenReturn(createGuildMember("321", "456", "test456"));

        when(guildMemberRepository.save(any(GuildMember.class))).thenAnswer(createGuildMemberAnswerForSomeInput());

        doNothing().when(mailHelper).sendPrivateMail(any(Mail.class));

        guildMemberService.acceptGuildMemberRequest("321", "789");

    }

    @Test
    public void testRemoveGuildMemberGuildNotFound() {

        when(guildHelper.getGuildByUserOwner("321")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberGuildMemberNotFound() {

        when(guildHelper.getGuildByUserOwner("321")).thenReturn(createGuild("321"));

        when(guildMemberRepository.findByGuildAndId("321", "789")).thenReturn(null);

        exceptionExpect.expect(ValidationException.class);
        exceptionExpect.expectMessage("guild.member.not.found");

        guildMemberService.removeGuildMember("321", "789");

    }

    @Test
    public void testRemoveGuildMemberSuccess() {

        when(guildHelper.getGuildByUserOwner("321")).thenReturn(createGuild("321"));

        when(guildMemberRepository.findByGuildAndId("321", "789")).thenReturn(createGuildMember("321", "456", "test456"));

        doNothing().when(guildMemberRepository).delete(any(GuildMember.class));

        guildMemberService.removeGuildMember("321", "789");

    }

}
