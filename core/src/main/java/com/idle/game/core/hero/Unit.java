package com.idle.game.core.hero;

import com.idle.game.core.type.UnitType;
import com.idle.game.core.type.Defense;
import com.idle.game.core.type.DefenseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Unit {

    private String id;
    private UnitType unitType;
    private Long experience;

    private List<Defense> defenses = new ArrayList<>();
    private Integer speed;
    private Integer critChance;
    private Integer critDamage;
    private Integer dodgeChance;
    private Integer hp;

    public Unit() {
    }

    public Unit(String id, UnitType unitType, Long experience) {
        this.id = id;
        this.unitType = unitType;
        this.experience = experience;
    }


    public List<Defense> getDefenses() {
        return defenses;
    }

    public void setDefenses(List<Defense> defenses) {
        this.defenses = defenses;
    }

    public Defense getDefense(DefenseType dt) {

        Optional<Defense> ret = this.getDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        if (ret.isPresent()) {
            return ret.get();
        } else {
            this.getDefenses().add(new Defense(dt, 0d));
            return getDefense(dt);
        }
    }

    public void setDefense(DefenseType dt, Double value) {
        Defense d = getDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getDefenses().add(new Defense(dt, value));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getCritChance() {
        return critChance;
    }

    public void setCritChance(Integer critChance) {
        this.critChance = critChance;
    }

    public Integer getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(Integer critDamage) {
        this.critDamage = critDamage;
    }

    public Integer getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(Integer dodgeChange) {
        this.dodgeChance = dodgeChange;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }


    @Override
    public String toString() {
        return "H{" + "id=" + this.id.substring(this.id.length() - 5, this.id.length() - 1)
                + ",type=" + this.unitType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unit other = (Unit) obj;
        return Objects.equals(this.id, other.id);
    }

}
