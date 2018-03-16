package com.idle.game.server.service;

import com.idle.game.helper.PlayerResourceHelper;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.server.repository.LootRollRepository;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class LootRollService {

    @Autowired
    private LootRollRepository lootRollRepository;

    @Autowired
    private PlayerResourceHelper playerResourceHelper;

    public List<LootRoll> findAll() {
        return lootRollRepository.findAll();
    }

    public LootRoll findById(String id) {
        return lootRollRepository.findOne(id);
    }

    public LootRoll buyById(String id) {
        LootRoll lootRoll = findById(id);

        if (lootRoll != null) {
            playerResourceHelper.useResources(lootRoll.getCost());
            return lootRoll;
        } else {
            throw new ValidationException("loot.roll.not.found");
        }

    }

}
