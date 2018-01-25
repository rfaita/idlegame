package com.idle.game.server.rest;

import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.mongo.Friend;
import com.idle.game.server.service.FriendService;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private FriendService friendService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<Friend>> getFriends() {

        Envelope<List<Friend>> ret = new Envelope<>();
        ret.setData(friendService.getFriends(tokenHelper.getUser()));

        return ret;

    }

    @RequestMapping(path = "/friendsRequest", method = RequestMethod.GET)
    public @ResponseBody
    Envelope<List<Friend>> getFriendsRequest() {

        Envelope<List<Friend>> ret = new Envelope<>();
        ret.setData(friendService.getFriendsRequest(tokenHelper.getUser()));

        return ret;

    }

    @RequestMapping(path = "/{userFriend}", method = RequestMethod.POST)
    public void sendFriendRequest(@PathVariable("userFriend") String userFriend) {

        friendService.sendFriendRequest(tokenHelper.getUser(), userFriend);
    }

    @RequestMapping(path = "/{friendId}", method = RequestMethod.DELETE)
    public void removeFriend(@PathVariable("friendId") String friendId) {

        friendService.removeFriend(tokenHelper.getUser(), friendId);
    }

    @RequestMapping(path = "/{friendId}", method = RequestMethod.PUT)
    public void acceptFriendRequest(@PathVariable("friendId") String friendId) {

        friendService.acceptFriendRequest(tokenHelper.getUser(), friendId);
    }

}
