package com.idle.game.core.action;

import com.idle.game.core.type.ActionType;
import com.idle.game.core.BaseObject;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.TargetType;

/**
 *
 * @author rafael
 */
public class ActionEffect extends BaseObject {

    private ActionType actionType;
    private TargetType targetType;
    //Habilidade pode errar?
    private Boolean canBeDodge = Boolean.TRUE;
    //Percentual do dano que sera utilizado no efeito caso o mesmo necessite
    private Integer actionPercentage;
    //Percentual do efeito ocorrer;
    private Integer actionChance;
    //Numero de turnos que o efeito persiste
    private Integer actionDuration;
    private DamageType damageType;
    private Boolean actionOverSameTeam;

    public Boolean getActionOverSameTeam() {
        return actionOverSameTeam;
    }

    public void setActionOverSameTeam(Boolean actionOverSameTeam) {
        this.actionOverSameTeam = actionOverSameTeam;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType at) {
        this.actionType = at;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public Boolean getCanBeDodge() {
        return canBeDodge;
    }

    public void setCanBeDodge(Boolean canBeDodge) {
        this.canBeDodge = canBeDodge;
    }

    public Integer getActionPercentage() {
        return actionPercentage;
    }

    public void setActionPercentage(Integer ap) {
        this.actionPercentage = ap;
    }

    public Integer getActionChance() {
        return actionChance;
    }

    public void setActionChance(Integer ac) {
        this.actionChance = ac;
    }

    public Integer getActionDuration() {
        return actionDuration;
    }

    public void setActionDuration(Integer ac) {
        this.actionDuration = ac;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public ActionEffect(ActionType actionType, Integer actionPercentage) {
        this(actionType, null, actionPercentage, null, null, null, Boolean.FALSE);
    }

    public ActionEffect(ActionType actionType, Integer actionPercentage, DamageType damageType) {
        this(actionType, null, actionPercentage, null, null, damageType, Boolean.FALSE);
    }

    public ActionEffect(ActionType actionType, TargetType targetType,
            Integer actionPercentage, Integer actionChance,
            Integer actionDuration, DamageType damageType, Boolean actionOverSameTeam) {
        this.actionType = actionType;
        this.targetType = targetType;
        this.actionPercentage = actionPercentage;
        this.actionChance = actionChance;
        this.actionDuration = actionDuration;
        this.damageType = damageType;
        this.actionOverSameTeam = actionOverSameTeam;
    }

    public ActionEffect() {
    }

    @Override
    public String toString() {
        return "SSE{" + "t=" + actionType + ", tt=" + targetType + ", d?=" + canBeDodge
                + ", ap=" + actionPercentage + ", ac=" + actionChance + ", ad=" + actionDuration + ", dt=" + damageType + ", aost=" + actionOverSameTeam + '}';
    }

}
