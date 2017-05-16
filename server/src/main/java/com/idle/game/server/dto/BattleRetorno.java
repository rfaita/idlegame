package com.idle.game.server.dto;

import com.idle.game.core.BattleLog;
import com.idle.game.core.FormationType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rafael
 */
public class BattleRetorno implements Serializable {

    private List<BattleLog> battleLog;
    private FormationType winner;

    public List<BattleLog> getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(List<BattleLog> battleLog) {
        this.battleLog = battleLog;
    }

    public FormationType getWinner() {
        return winner;
    }

    public void setWinner(FormationType winner) {
        this.winner = winner;
    }

    public BattleRetorno(List<BattleLog> battleLog, FormationType winner) {
        this.battleLog = battleLog;
        this.winner = winner;
    }

    public BattleRetorno() {
    }

}
