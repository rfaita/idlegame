package com.idle.game.server.service;

import com.idle.game.helper.MailHelper;
import com.idle.game.model.mongo.Friend;
import com.idle.game.model.mongo.Mail;
import com.idle.game.server.repository.FriendRepository;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MailHelper mailHelper;

    public List<Friend> getFriends(String user) {

        return friendRepository.findAllByUserAndAccepted(user, Boolean.TRUE);
    }

    public List<Friend> getFriendsRequest(String user) {

        return friendRepository.findAllByUserAndAccepted(user, Boolean.FALSE);
    }

    public void sendFriendRequest(String user, String userFriend) {

        if (user.equals(userFriend)) {
            throw new ValidationException("you.can.not.be.your.friend");
        }

        Friend friendAlready = friendRepository.findByUserAndUserFriend(user, userFriend);

        if (friendAlready != null) {
            throw new ValidationException("user.already.your.friend");
        }

        Friend friend = new Friend(user, userFriend);

        friendRepository.save(friend);

        Mail mail = new Mail();
        mail.setToUser(userFriend);
        mail.setText("friend.request");

        mailHelper.sendPrivateMail(mail);

    }

    public void acceptFriendRequest(String user, String friendRequestId) {

        Friend request = friendRepository.findByUserFriendAndIdAndAccepted(user, friendRequestId, Boolean.FALSE);

        if (request != null) {

            request.setAccepted(Boolean.TRUE);

            friendRepository.save(request);

            Friend reverse = new Friend(request.getUserFriend(), request.getUser());
            reverse.setAccepted(Boolean.TRUE);

            friendRepository.save(reverse);

            Mail mail = new Mail();
            mail.setToUser(request.getUser());
            mail.setText("friend.request.accepted");

            mailHelper.sendPrivateMail(mail);

        } else {
            throw new ValidationException("friend.request.not.found");
        }

    }

    public void removeFriend(String user, String friendId) {

        Friend friend = friendRepository.findByUserAndIdAndAccepted(user, friendId, Boolean.TRUE);

        if (friend != null) {
            friendRepository.delete(friend);
        } else {
            throw new ValidationException("friend.not.found");
        }

    }

}
