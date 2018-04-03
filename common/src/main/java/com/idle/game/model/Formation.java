package com.idle.game.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.battle.BattlePositionedHero;
import com.idle.game.core.formation.type.FormationAllocation;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "formation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Formation implements Serializable {

    @Id
    private String id;
    private String userId;
    @Size(min = 1, max = 9, message = "formation.min.size.max.size")
    private List<BattlePositionedHero> heroes;
    @NotNull(message = "formation.allocation.can.not.be.null")
    private FormationAllocation formationAllocation;

    public Formation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<BattlePositionedHero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<BattlePositionedHero> heroes) {
        this.heroes = heroes;
    }

    public FormationAllocation getFormationAllocation() {
        return formationAllocation;
    }

    public void setFormationAllocation(FormationAllocation formationAllocation) {
        this.formationAllocation = formationAllocation;
    }

}
