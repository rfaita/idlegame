package com.idle.game.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Skill extends BaseObject {
    
    private SkillEffect mainEffect;
    
    private List<SkillEffect> secundaryEffects = new ArrayList<>();
    
    public Skill() {
    }
    
    public Skill(SkillEffect mainEffect, List<SkillEffect> secundaryEffects) {
        this.mainEffect = mainEffect;
        this.secundaryEffects = secundaryEffects;
    }
    
    public SkillEffect getMainEffect() {
        return mainEffect;
    }
    
    public void setMainEffect(SkillEffect mainEffect) {
        this.mainEffect = mainEffect;
    }
    
    public List<SkillEffect> getSecundaryEffects() {
        return secundaryEffects;
    }
    
    public void addSecundaryEffects(SkillEffect secundaryEffect) {
        this.secundaryEffects.add(secundaryEffect);
    }

    @Override
    public String toString() {
        return "SS{" + "me=" + mainEffect + ", ses=" + secundaryEffects + '}';
    }
    
    
}
