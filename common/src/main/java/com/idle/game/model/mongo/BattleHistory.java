package com.idle.game.model.mongo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.battle.Battle;
import com.idle.game.core.battle.BattleLog;
import com.idle.game.core.formation.BattleFormation;
import com.idle.game.core.formation.type.FormationType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "battlehistory")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BattleHistory implements Serializable {

    @Id
    private String id;
    private List<BattleLog> battleLog;
    private Integer turn = 1;
    private BattleFormation attackFormation;
    private BattleFormation defenseFormation;
    private FormationType winner;
    @Indexed(name = "expirationDate", expireAfterSeconds = 432000)
    private final Date date = new Date();

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BattleLog> getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(List<BattleLog> battleLog) {
        this.battleLog = battleLog;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public BattleFormation getAttackFormation() {
        return attackFormation;
    }

    public void setAttackFormation(BattleFormation attackFormation) {
        this.attackFormation = attackFormation;
    }

    public BattleFormation getDefenseFormation() {
        return defenseFormation;
    }

    public void setDefenseFormation(BattleFormation defenseFormation) {
        this.defenseFormation = defenseFormation;
    }

    public FormationType getWinner() {
        return winner;
    }

    public void setWinner(FormationType winner) {
        this.winner = winner;
    }

    public static BattleHistory getBattleHistory(Battle battle) {
        BattleHistory ret = new BattleHistory();
        ret.setAttackFormation(battle.getAttackFormation());
        ret.setDefenseFormation(battle.getDefenseFormation());
        ret.setBattleLog(battle.getBattleLog());
        ret.setTurn(battle.getTurn());
        ret.setWinner(battle.getWinner().getFormationType());

        return ret;
    }
}
