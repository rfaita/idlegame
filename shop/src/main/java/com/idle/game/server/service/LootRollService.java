package com.idle.game.server.service;

import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.repository.LootRollRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 *
 * @author rafael
 */
@Service
public class LootRollService {

    @Autowired
    private LootRollRepository lootRollRepository;

    public List<LootRoll> findAll() {
        return lootRollRepository.findAll();
    }

    public LootRoll findById(String id) {

        Optional<LootRoll> ret = lootRollRepository.findById(id);

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }

    }
}
