package com.idle.game.server.service;

import com.idle.game.helper.MailHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.Friend;
import com.idle.game.model.Mail;
import com.idle.game.model.Player;
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
    private PlayerHelper playerHelper;

    @Autowired
    private MailHelper mailHelper;

    public List<Friend> getFriends(String user) {
        return friendRepository.findAllByUser(user);
    }

    public void sendFriendRequest(String user, String userFriend) {

        if (user.equals(userFriend)) {
            throw new ValidationException("you.can.not.be.your.friend");
        }

        Friend friendAlready = friendRepository.findByUserAndUserFriend(user, userFriend);

        if (friendAlready != null) {
            throw new ValidationException("user.already.your.friend");
        }

        Player player = playerHelper.getPlayerByLinkedUser(userFriend);
        if (player == null) {
            throw new ValidationException("player.not.found");
        }

        Friend friend = new Friend(user, userFriend, player.getName());

        friendRepository.save(friend);

        player = playerHelper.getPlayerByLinkedUser(user);
        if (player == null) {
            throw new ValidationException("player.not.found");
        }

        Friend friendReverse = new Friend(userFriend, user, player.getName());
        friendReverse.setReverse(Boolean.TRUE);

        friendRepository.save(friendReverse);

        Mail mail = new Mail();
        mail.setToUser(userFriend);
        mail.setText("friend.request");

        mailHelper.sendPrivateMail(mail);

    }

    public void acceptFriendRequest(String user, String friendRequestId) {

        Friend request = friendRepository.findByUserAndIdAndAcceptedAndReverse(user, friendRequestId, Boolean.FALSE, Boolean.TRUE);

        if (request != null) {

            Date acceptedDate = new Date();

            request.setAccepted(Boolean.TRUE);
            request.setSince(acceptedDate);

            friendRepository.save(request);

            Friend reverse = friendRepository.findByUserAndUserFriend(request.getUserFriend(), user);
            reverse.setAccepted(Boolean.TRUE);
            reverse.setSince(acceptedDate);

            friendRepository.save(reverse);

            Mail mail = new Mail();
            mail.setToUser(request.getUserFriend());
            mail.setText("friend.request.accepted");

            mailHelper.sendPrivateMail(mail);

        } else {
            throw new ValidationException("friend.request.not.found");
        }

    }

    public void removeFriend(String user, String friendId) {

        Friend friend = friendRepository.findByUserAndId(user, friendId);

        if (friend != null) {

            Friend friendReverse = friendRepository.findByUserAndUserFriend(friend.getUserFriend(), user);

            friendRepository.delete(friend);
            friendRepository.delete(friendReverse);
        } else {
            throw new ValidationException("friend.not.found");
        }

    }

}
