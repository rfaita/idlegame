package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.USER_FIND_BY_ID;
import static com.idle.game.constant.CacheConstants.USER_FIND_BY_NICK_NAME;
import com.idle.game.model.PvpRating;
import com.idle.game.model.User;
import com.idle.game.server.repository.UserRepository;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = USER_FIND_BY_ID, key = "'" + USER_FIND_BY_ID + "' + #userId")
    public User findById(String userId) {

        Optional<User> ret = userRepository.findById(userId);

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    @Cacheable(value = USER_FIND_BY_NICK_NAME, key = "'" + USER_FIND_BY_NICK_NAME + "' + #nickName")
    public User findByNickName(String nickName) {
        User ret = userRepository.findByNickName(nickName);

        if (ret == null) {
            throw new ValidationException("user.not.found");
        } else {
            return ret;
        }

    }

    public User create(User user) {
        User userRet = findById(user.getId());

        if (userRet != null) {
            return userRet;
        } else {
            return this.userRepository.save(user);
        }
    }
}
