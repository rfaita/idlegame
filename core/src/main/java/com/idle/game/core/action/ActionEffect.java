package com.idle.game.core.action;

import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.BaseObject;
import com.idle.game.core.buff.BuffEffect;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.TargetType;

import java.util.ArrayList;
import java.util.List;

public class ActionEffect extends BaseObject {

    private ActionType type;
    private TargetType targetType;
    //Habilidade pode errar?
    private Boolean canBeDodge = Boolean.FALSE;
    //Percentual do dano que sera utilizado no efeito caso o mesmo necessite
    private Integer value;
    private Double accuracy;
    private DamageType damageType;
    private Boolean overSameTeam;
    private List<BuffEffect> buffEffects;

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Boolean getOverSameTeam() {
        return overSameTeam;
    }

    public void setOverSameTeam(Boolean overSameTeam) {
        this.overSameTeam = overSameTeam;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public void addBuffEffect(BuffEffect buffEffect) {
        if (this.buffEffects == null) {
            this.buffEffects = new ArrayList<>();
        }
        this.buffEffects.add(buffEffect);
    }

    public List<BuffEffect> getBuffEffects() {
        return buffEffects;
    }

    public void setBuffEffects(List<BuffEffect> buffEffects) {
        this.buffEffects = buffEffects;
    }

    public ActionEffect(ActionType type, TargetType targetType, Integer value, Double accuracy, DamageType damageType) {
        this(type, targetType, value, accuracy, damageType, Boolean.FALSE);
    }

    public ActionEffect(ActionType type, TargetType targetType, Integer value, Double accuracy, DamageType damageType, Boolean overSameTeam) {
        this(type, targetType, value, accuracy, damageType, overSameTeam, null);
    }

    public ActionEffect(ActionType type, TargetType targetType, Integer value, Double accuracy, DamageType damageType, Boolean overSameTeam, List<BuffEffect> buffEffects) {
        this.type = type;
        this.targetType = targetType;
        this.value = value;
        this.accuracy = accuracy;
        this.damageType = damageType;
        this.overSameTeam = overSameTeam;
        this.buffEffects = buffEffects;
    }

    public ActionEffect() {
    }

    @Override
    public String toString() {
        return "AE{" + "t=" + type + ", tt=" + targetType + ", d?=" + canBeDodge
                + ", value=" + value + ", dt=" + damageType + ", aost=" + overSameTeam + '}';
    }

}
