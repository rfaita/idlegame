package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.USER_FIND_BY_ID;
import com.idle.game.model.User;
import com.idle.game.server.repository.UserRepository;
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
        return userRepository.findOne(userId);
    }

    public User findByNickName(String name) {
        User ret = userRepository.findByNickName(name);

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
