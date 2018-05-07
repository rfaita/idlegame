package com.idle.game.server.service;

import com.idle.game.helper.client.battle.BattleFieldClient;
import com.idle.game.helper.client.battle.PveBattleFieldConfigClient;
import com.idle.game.helper.client.shop.InventoryClient;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.battle.BattleLayer;
import com.idle.game.model.battle.BattleSite;
import com.idle.game.model.battle.pve.PveBattleField;
import com.idle.game.model.battle.pve.PveBattleFieldConfig;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.server.repository.PveBattleFieldRepository;

/**
 *
 * @author rafael
 */
@Service
public class PveBattleFieldService {

    @Autowired
    private PveBattleFieldRepository pveBattleFieldRepository;

    @Autowired
    private PveBattleFieldConfigClient pveBattleFieldConfigClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private BattleFieldClient battleFieldClient;

    public PveBattleField findByUserId(String userId) {
        return pveBattleFieldRepository.findByUserId(userId);
    }

    public PveBattleField update(PveBattleField pveBattleField) {

        PveBattleField temp = findByUserId(pveBattleField.getUserId());

        if (temp != null) {
            pveBattleField.setBattleFieldId(temp.getBattleFieldId());
            pveBattleField.getBattleField().setId(temp.getBattleFieldId());
        } else {
            pveBattleField.setBattleFieldId(null);
            pveBattleField.getBattleField().setId(null);
        }

        Inventory inventory = inventoryClient.findByUserId().getData();

        if (inventory == null) {
            throw new ValidationException("inventory.not.found");
        }

        for (BattleLayer layer : pveBattleField.getBattleField().getLayers()) {
            for (BattleSite site : layer.getSites()) {
                if (!inventory.containsFormationItem(site.getFormationId())) {
                    throw new ValidationException("you.do.not.have.this.formation.in.your.inventory");
                }
                //TODO - marcar o item como utilizado
            }
        }

        PveBattleFieldConfig pbfc = pveBattleFieldConfigClient.findByUserId().getData();

        if (pbfc == null) {
            throw new ValidationException("battle.field.config.not.found");
        }

        pveBattleField.getBattleField().validate(pbfc);

        pveBattleField.setBattleField(battleFieldClient.update(pveBattleField.getBattleField()).getData());

        return pveBattleFieldRepository.save(pveBattleField);

    }

}
