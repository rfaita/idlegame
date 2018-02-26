package com.idle.game.tests;

import com.idle.game.helper.MailHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.Friend;
import com.idle.game.model.mongo.Mail;
import com.idle.game.server.repository.FriendRepository;
import com.idle.game.server.service.FriendService;
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

/**
 *
 * @author rafael
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendServiceTest {

    @MockBean
    private FriendRepository friendRepository;

    @MockBean
    private PlayerHelper playerHelper;

    @MockBean
    private MailHelper mailHelper;

    @Autowired
    private FriendService friendService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testYouCanNotBeYourFriend() {

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("you.can.not.be.your.friend");

        friendService.sendFriendRequest("1", "1");

    }

    @Test
    public void testAlreadyFriends() {

        when(friendRepository.findByUserAndUserFriend("1", "2")).thenReturn(new Friend("1", "2", "teste"));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("user.already.your.friend");

        friendService.sendFriendRequest("1", "2");

    }

    @Test
    public void testSuccessFriends() {

        when(friendRepository.findByUserAndUserFriend("1", "2")).thenReturn(null);

        when(friendRepository.save(any(Friend.class))).thenAnswer(createFriendAnswerForSomeInput());

        when(playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));
        when(playerHelper.getPlayerByLinkedUser("2")).thenReturn(createPlayer("2"));

        doNothing().when(mailHelper).sendPrivateMail(any(Mail.class));

        friendService.sendFriendRequest("1", "2");

    }

    @Test
    public void testAcceptFriendRequestRequestNotFound() {

        when(friendRepository.findByUserAndIdAndAcceptedAndReverse("1", "1000", Boolean.FALSE, Boolean.TRUE)).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("friend.request.not.found");

        friendService.acceptFriendRequest("1", "1000");

    }

    @Test
    public void testAcceptFriendRequest() {

        when(friendRepository.findByUserAndIdAndAcceptedAndReverse("1", "1000", Boolean.FALSE, Boolean.TRUE)).thenReturn(new Friend("1", "2", "teste"));
        when(friendRepository.findByUserAndUserFriend("2", "1")).thenReturn(new Friend("2", "1", "teste2"));

        when(friendRepository.save(any(Friend.class))).thenAnswer(createFriendAnswerForSomeInput());

        friendService.acceptFriendRequest("1", "1000");

    }

    @Test
    public void testRemoveFriendFriendNotFound() {

        when(friendRepository.findByUserAndId("1", "2")).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("friend.not.found");

        friendService.removeFriend("1", "2");

    }

    @Test
    public void testRemoveFriend() {

        when(friendRepository.findByUserAndId("1", "2")).thenReturn(new Friend("1", "2", "teste"));

        doNothing().when(friendRepository).delete(any(Friend.class));

        friendService.removeFriend("1", "2");

    }

}
