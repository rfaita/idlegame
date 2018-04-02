package com.idle.game.server.service;

import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.Friend;
import com.idle.game.model.Mail;
import com.idle.game.model.User;
import com.idle.game.server.repository.FriendRepository;
import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private MailClient mailClient;

    public List<Friend> getFriends(String userId) {
        return friendRepository.findAllByUserId(userId);
    }

    public void sendFriendRequest(String userId, String userNickName, String friendUserId) {

        if (userId.equals(friendUserId)) {
            throw new ValidationException("you.can.not.be.your.friend");
        }

        Friend friendAlready = friendRepository.findByUserIdAndFriendUserId(userId, friendUserId);

        if (friendAlready != null) {
            throw new ValidationException("user.already.your.friend");
        }

        User user = userClient.findById(friendUserId).getData();

        if (user == null) {
            throw new ValidationException("user.not.found");
        }

        Friend friend = new Friend(userId, friendUserId, user.getNickName());

        friendRepository.save(friend);

        Friend friendReverse = new Friend(friendUserId, userId, userNickName);
        friendReverse.setReverse(Boolean.TRUE);

        friendRepository.save(friendReverse);

        Mail mail = new Mail();
        mail.setToUserId(friendUserId);
        mail.setText("friend.request");

        mailClient.sendPrivateMail(mail);

    }

    public void acceptFriendRequest(String userId, String friendRequestId) {

        Friend request = friendRepository.findByUserIdAndIdAndAcceptedAndReverse(userId, friendRequestId, Boolean.FALSE, Boolean.TRUE);

        if (request != null) {

            Date acceptedDate = new Date();

            request.setAccepted(Boolean.TRUE);
            request.setSince(acceptedDate);

            friendRepository.save(request);

            Friend reverse = friendRepository.findByUserIdAndFriendUserId(request.getFriendUserId(), userId);
            reverse.setAccepted(Boolean.TRUE);
            reverse.setSince(acceptedDate);

            friendRepository.save(reverse);

            Mail mail = new Mail();
            mail.setToUserId(request.getFriendUserId());
            mail.setText("friend.request.accepted");

            mailClient.sendPrivateMail(mail);

        } else {
            throw new ValidationException("friend.request.not.found");
        }

    }

    public void removeFriend(String userId, String friendId) {

        Friend friend = friendRepository.findByUserIdAndId(userId, friendId);

        if (friend != null) {

            Friend friendReverse = friendRepository.findByUserIdAndFriendUserId(friend.getFriendUserId(), userId);

            friendRepository.delete(friend);
            friendRepository.delete(friendReverse);
        } else {
            throw new ValidationException("friend.not.found");
        }

    }

}
