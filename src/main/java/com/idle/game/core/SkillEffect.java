package com.idle.game.core;

/**
 *
 * @author rafael
 */
public class SkillEffect extends BaseObject {

    private EffectType skillEffectType;
    private TargetType targetType;
    //Habilidade pode errar?
    private Boolean canBeDodge = Boolean.FALSE;
    //Percentual do dano que sera utilizado no efeito caso o mesmo necessite
    private Integer effectDamagePercentage;
    //Percentual do efeito ocorrer;
    private Integer effectChance;
    //Numero de turnos que o efeito persiste
    private Integer effectTurnDuration;
    private DamageType damageType;

    public EffectType getSkillEffectType() {
        return skillEffectType;
    }

    public void setSkillEffectType(EffectType skillEffectType) {
        this.skillEffectType = skillEffectType;
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

    public Integer getEffectDamagePercentage() {
        return effectDamagePercentage;
    }

    public void setEffectDamagePercentage(Integer effectDamagePercentage) {
        this.effectDamagePercentage = effectDamagePercentage;
    }

    public Integer getEffectChance() {
        return effectChance;
    }

    public void setEffectChance(Integer effectChance) {
        this.effectChance = effectChance;
    }

    public Integer getEffectTurnDuration() {
        return effectTurnDuration;
    }

    public void setEffectTurnDuration(Integer effectTurnNumber) {
        this.effectTurnDuration = effectTurnNumber;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public SkillEffect(EffectType skillEffectType, TargetType targetType, Integer effectDamagePercentage, DamageType damageType) {
        this.skillEffectType = skillEffectType;
        this.targetType = targetType;
        this.effectDamagePercentage = effectDamagePercentage;
        this.damageType = damageType;
    }

    public SkillEffect(EffectType skillEffectType, TargetType targetType, Integer effectDamagePercentage, Integer effectChance, Integer effectTurnNumber, DamageType damageType) {
        this.skillEffectType = skillEffectType;
        this.targetType = targetType;
        this.effectDamagePercentage = effectDamagePercentage;
        this.effectChance = effectChance;
        this.effectTurnDuration = effectTurnNumber;
        this.damageType = damageType;
    }

    public SkillEffect() {
    }

    @Override
    public String toString() {
        return "SSE{" + "type=" + skillEffectType + ", tt=" + targetType + ", d?=" + canBeDodge + ", edp=" + effectDamagePercentage + ", ec=" + effectChance + ", etd=" + effectTurnDuration + ", dt=" + damageType + '}';
    }
    
    

}
