package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.formation.PositionedHero;
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
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Formation implements Serializable {

    @Id
    private String id;
    private String player;
    @Size(min = 1, max = 6, message = "formation.min.size.max.size")
    private List<PositionedHero> heroes;
    @NotNull(message = "formation.allocation.can.not.be.null")
    private FormationAllocation formationAllocation;
    private String nextLevelFormation;
    private Reward reward;

    public Formation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public List<PositionedHero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<PositionedHero> heroes) {
        this.heroes = heroes;
    }

    public FormationAllocation getFormationAllocation() {
        return formationAllocation;
    }

    public void setFormationAllocation(FormationAllocation formationAllocation) {
        this.formationAllocation = formationAllocation;
    }

    public String getNextLevelFormation() {
        return nextLevelFormation;
    }

    public void setNextLevelFormation(String nextLevelFormation) {
        this.nextLevelFormation = nextLevelFormation;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

}
