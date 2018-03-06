package com.idle.game.server.repository;

import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.model.Formation;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface FormationRepository extends MongoRepository<Formation, String> {

    Formation findById(String id);

    List<Formation> findAllByPlayer(String player);
    
    Formation findByPlayerAndFormationAllocation(String player, FormationAllocation formationAllocation);

}
