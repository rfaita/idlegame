package com.idle.game.helper;

import com.idle.game.helper.cb.FormationCircuitBreakerService;
import com.idle.game.model.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class FormationHelper {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private FormationCircuitBreakerService formationCircuitBreakerService;

    public Formation getFormationById(String id) {
        return formationCircuitBreakerService.getFormationById(id, tokenHelper.getToken());
    }

    public Formation getFormationByPlayerAndFormationAllocation(String id, String formationAllocation) {
        return formationCircuitBreakerService.getFormationByPlayerAndFormationAllocation(id, formationAllocation, tokenHelper.getToken());
    }

}
