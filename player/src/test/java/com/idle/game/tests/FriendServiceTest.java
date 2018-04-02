package com.idle.game.tests;

import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.Friend;
import com.idle.game.model.Mail;
import com.idle.game.server.dto.Envelope;
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
    private MailClient mailClient;
    
    @MockBean
    private UserClient userClient;
    
    @Autowired
    private FriendService friendService;
    
    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();
    
    @Test
    public void testYouCanNotBeYourFriend() {
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("you.can.not.be.your.friend");
        
        friendService.sendFriendRequest("1", "test", "1");
        
    }
    
    @Test
    public void testAlreadyFriends() {
        
        when(friendRepository.findByUserIdAndFriendUserId("1", "2")).thenReturn(new Friend("1", "2", "teste"));
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("user.already.your.friend");
        
        friendService.sendFriendRequest("1", "test", "2");
        
    }
    
    @Test
    public void testSuccessFriends() {
        
        when(friendRepository.findByUserIdAndFriendUserId("1", "2")).thenReturn(null);
        
        when(friendRepository.save(any(Friend.class))).thenAnswer(createFriendAnswerForSomeInput());
        
        when(userClient.findById("2")).thenReturn(new Envelope(createUser("2")));
        
        doNothing().when(mailClient).sendPrivateMail(any(Mail.class));
        
        friendService.sendFriendRequest("1", "test", "2");
        
    }
    
    @Test
    public void testAcceptFriendRequestRequestNotFound() {
        
        when(friendRepository.findByUserIdAndIdAndAcceptedAndReverse("1", "1000", Boolean.FALSE, Boolean.TRUE)).thenReturn(null);
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("friend.request.not.found");
        
        friendService.acceptFriendRequest("1", "1000");
        
    }
    
    @Test
    public void testAcceptFriendRequest() {
        
        when(friendRepository.findByUserIdAndIdAndAcceptedAndReverse("1", "1000", Boolean.FALSE, Boolean.TRUE)).thenReturn(new Friend("1", "2", "teste"));
        when(friendRepository.findByUserIdAndFriendUserId("2", "1")).thenReturn(new Friend("2", "1", "teste2"));
        
        when(friendRepository.save(any(Friend.class))).thenAnswer(createFriendAnswerForSomeInput());
        
        doNothing().when(mailClient).sendPrivateMail(any(Mail.class));
        
        friendService.acceptFriendRequest("1", "1000");
        
    }
    
    @Test
    public void testRemoveFriendFriendNotFound() {
        
        when(friendRepository.findByUserIdAndId("1", "2")).thenReturn(null);
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("friend.not.found");
        
        friendService.removeFriend("1", "2");
        
    }
    
    @Test
    public void testRemoveFriend() {
        
        when(friendRepository.findByUserIdAndId("1", "2")).thenReturn(new Friend("1", "2", "teste"));
        
        doNothing().when(friendRepository).delete(any(Friend.class));
        
        friendService.removeFriend("1", "2");
        
    }
    
}
