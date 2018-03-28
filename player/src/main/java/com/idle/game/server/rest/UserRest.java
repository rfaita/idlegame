package com.idle.game.server.rest;

import com.idle.game.helper.TokenHelper;
import com.idle.game.server.dto.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.idle.game.model.User;
import com.idle.game.server.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserRest {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/{userId}", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<User> addResources(@PathVariable("userId") String userId) {

        Envelope<User> ret = new Envelope<>();
        ret.setData(userService.findById(userId));

        return ret;

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseBody
    public Envelope<User> create() throws Exception {

        User user = new User();
        user.setId(tokenHelper.getUserId());
        user.setNickName(tokenHelper.getNickName());
        user.setEmail(tokenHelper.getEmail());

        return new Envelope<>(userService.create(user));
    }

}
