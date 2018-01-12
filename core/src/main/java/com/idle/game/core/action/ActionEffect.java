package com.idle.game.core.action;

import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.BaseObject;
import com.idle.game.core.buff.type.BuffEffect;
import com.idle.game.core.type.DamageType;
import static com.idle.game.core.type.DamageType.PHYSICAL;
import com.idle.game.core.type.TargetType;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ActionEffect extends BaseObject {

    private ActionType type;
    private TargetType targetType;
    //Habilidade pode errar?
    private Boolean canBeDodge = Boolean.TRUE;
    //Percentual do dano que sera utilizado no efeito caso o mesmo necessite
    private Integer percentage;
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

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
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

    public List<BuffEffect> getBuffEffects() {
        return buffEffects;
    }

    public void setBuffEffects(List<BuffEffect> buffEffects) {
        this.buffEffects = buffEffects;
    }

    public ActionEffect(ActionType type, TargetType targetType, Integer percentage) {
        this(type, targetType, percentage, PHYSICAL);
    }

    public ActionEffect(ActionType type, TargetType targetType, Integer percentage, DamageType damageType) {
        this(type, targetType, percentage, damageType, Boolean.FALSE);
    }

    public ActionEffect(ActionType type, TargetType targetType, Integer percentage, DamageType damageType, Boolean overSameTeam) {
        this(type, targetType, percentage, damageType, overSameTeam, null);
    }

    public ActionEffect(ActionType type, TargetType targetType, Integer percentage, DamageType damageType, Boolean overSameTeam, List<BuffEffect> buffEffects) {
        this.type = type;
        this.targetType = targetType;
        this.percentage = percentage;
        this.damageType = damageType;
        this.overSameTeam = overSameTeam;
        this.buffEffects = buffEffects;
    }

    public ActionEffect() {
    }

    @Override
    public String toString() {
        return "SSE{" + "t=" + type + ", tt=" + targetType + ", d?=" + canBeDodge
                + ", ap=" + percentage + ", dt=" + damageType + ", aost=" + overSameTeam + '}';
    }

}
