package com.idle.game.core.battle;

import com.idle.game.core.action.Action;
import com.idle.game.core.battle.type.BattleTeamType;
import com.idle.game.core.buff.Buff;
import com.idle.game.core.formation.type.FormationPosition;
import com.idle.game.core.formation.type.FormationPositionType;
import com.idle.game.core.hero.Unit;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.Defense;
import com.idle.game.core.type.DefenseType;
import com.idle.game.core.util.DiceUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;

import static com.idle.game.core.constant.IdleConstants.LOG;

public class BattleUnit implements Serializable {

    private Integer energy = 0;
    private BattleTeamType battleTeamType;
    private FormationPosition position;
    private Unit unit;

    private List<Defense> currDefenses = new ArrayList<>();
    private Integer currSpeed;
    private Integer currCritChance;
    private Integer currCritDamage;
    private Integer currDodgeChance;
    private Integer currHp;

    private Boolean canDoAction;

    private List<Buff> currBuffs;

    private Boolean clone = Boolean.FALSE;

    public Defense getCurrDefense(DefenseType dt) {

        Optional<Defense> ret = this.getCurrDefenses().stream().filter((d) -> d.getType().equals(dt)).findFirst();
        if (ret.isPresent()) {
            return ret.get();
        } else {
            this.getCurrDefenses().add(new Defense(dt, 0d));
            return getCurrDefense(dt);
        }
    }

    public void setCurrDefense(DefenseType dt, Double value) {
        Defense d = getCurrDefense(dt);
        if (d != null) {
            d.setValue(value);
        } else {
            this.getCurrDefenses().add(new Defense(dt, value));
        }
    }

    public List<Defense> getCurrDefenses() {
        return currDefenses;
    }

    public void setCurrDefenses(List<Defense> currDefenses) {
        this.currDefenses = currDefenses;
    }

    public Integer getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrSpeed(Integer currSpeed) {
        this.currSpeed = currSpeed;
    }

    public Boolean getCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(Boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public Integer getCurrCritChance() {
        return currCritChance;
    }

    public void setCurrCritChance(Integer currCritChance) {
        this.currCritChance = currCritChance;
    }

    public Integer getCurrCritDamage() {
        return currCritDamage;
    }

    public void setCurrCritDamage(Integer currCritDamage) {
        this.currCritDamage = currCritDamage;
    }

    public Integer getCurrDodgeChance() {
        return currDodgeChance;
    }

    public void setCurrDodgeChance(Integer currDodgeChance) {
        this.currDodgeChance = currDodgeChance;
    }

    public Integer getCurrHp() {
        return currHp;
    }

    public List<Buff> getCurrBuffs() {
        return currBuffs;
    }

    public void setCurrBuffs(List<Buff> currBuffs) {
        this.currBuffs = currBuffs;
    }

    public void addCurrBuff(Buff buff) {
        this.currBuffs.add(buff);
    }

    public void setCurrHp(Integer currHp) {
        if (currHp > this.getUnit().getHp()) {
            currHp = this.getUnit().getHp();
        }
        if (currHp < 0) {
            currHp = 0;
        }
        this.currHp = currHp;
    }

    public Double rollCriticalHit() {
        Double dmgModifier = 1.0d;

        if (this.getCurrCritChance() > 0 && DiceUtil.random(100) <= this.getCurrCritChance()) {
            dmgModifier += this.getCurrCritDamage() / 100d;
        }

        return dmgModifier;
    }

    public Double getDmgDefenseReduction(DamageType dmgType) {
        return this.getCurrDefense(dmgType.getDefenseType()).getValue();
    }

    public void prepareToBattle() {
        this.prepareToTurn();
        this.setCurrHp(this.getUnit().getHp());
        this.setCurrBuffs(new ArrayList<>());
        LOG.log(Level.INFO, "[PREPARE_TO_BATTLE] {0}", this.toStringComplete());
    }

    public void prepareToTurn() {
        this.setCurrDefenses(this.getUnit().getDefenses());
        this.setCurrCritChance(this.getUnit().getCritChance());
        this.setCurrCritDamage(this.getUnit().getCritDamage());
        this.setCurrDodgeChance(this.getUnit().getDodgeChance());
        this.setCurrSpeed(this.getUnit().getSpeed());
        this.setCanDoAction(Boolean.TRUE);
        LOG.log(Level.INFO, "[PREPARE_TO_TURN] {0}", this.toStringComplete());
    }

    public void calcTradeAttribute(AttributeType o, AttributeType d, Integer ratioPercentage) {
        Integer perc = (int) (this.getMissingAttributePercentage(o) / 100d * ratioPercentage / 100d);
        LOG.log(Level.INFO, "[calcTradeAttribute][perc] {0}", perc);
        if (perc > 0) {
            this.recalcAttribute(d, perc, 1);
        }
    }

    public Integer getMissingAttributePercentage(AttributeType at) {
        switch (at) {
            case HP:
                return (int) ((this.getUnit().getHp() - this.getCurrHp()) * 1d / this.getUnit().getHp() * 100);
            default:
                return 0;
        }

    }

    public void recalcAttribute(AttributeType d, Integer ratioPercentage, Integer signal) {
        Double perc = ratioPercentage / 100d * signal;
        LOG.log(Level.INFO, "[recalcAttribute][perc] {0}", perc);
        if (perc > 0) {
            switch (d) {
                case SPEED:
                    this.setCurrSpeed(this.getCurrSpeed() + (int) (this.getCurrSpeed() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][speed] {0}", this.getCurrSpeed());
                    break;
                case DODGE:
                    this.setCurrDodgeChance(this.getCurrDodgeChance() + (int) (this.getCurrDodgeChance() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][dodgeChance] {0}", this.getCurrDodgeChance());
                    break;
                case CRIT_DMG:
                    this.setCurrCritDamage(this.getCurrCritDamage() + (int) (this.getCurrCritDamage() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][critDmg] {0}", this.getCurrCritDamage());
                    break;
                case CRIT_CHANCE:
                    this.setCurrCritChance(this.getCurrCritChance() + (int) (this.getCurrCritChance() * perc));
                    LOG.log(Level.INFO, "[recalcAttribute][critChance] {0}", this.getCurrCritChance());
                    break;
                case DEFENSE:
                    this.getCurrDefenses().forEach((dt) -> {
                        dt.setValue(dt.getValue() + perc);
                        LOG.log(Level.INFO, "[calcTradeAttribute][{0}] {1}", new Object[]{dt.getType(), dt.getValue()});
                    });
                    break;

            }
        }

    }

    public Boolean getClone() {
        return clone;
    }

    public void setClone(Boolean clone) {
        this.clone = clone;
    }

    public FormationPosition getPosition() {
        return position;
    }

    public void setPosition(FormationPosition position) {
        this.position = position;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BattleTeamType getBattleTeamType() {
        return battleTeamType;
    }

    public void setBattleTeamType(BattleTeamType battleTeamType) {
        this.battleTeamType = battleTeamType;
    }

    public BattleUnit(BattleTeamType battleTeamType) {
        this.battleTeamType = battleTeamType;
    }

    public BattleUnit() {
    }

    public BattleUnit(FormationPosition position, Unit hero) {
        this(null, position, hero);
    }

    public BattleUnit(BattleTeamType battleTeamType, FormationPosition position, Unit hero) {
        this.position = position;
        this.unit = hero;
        this.battleTeamType = battleTeamType;
    }

    @Override
    public String toString() {
        return "BPH{" + "type=" + battleTeamType + ",p=" + position + ",hero=" + unit + ",energy=" + energy + '}';
    }

    public Action nextAction() {

        Unit aHero = this.getUnit();

        FormationPositionType fpt = this.getPosition().getType();

        assert aHero.getUnitType().getDefaultAction(fpt) != null : "Hero with action is null. BUG";

        return aHero.getUnitType().getDefaultAction(fpt);
    }

    public BattleUnit duplicate() {
        return duplicate(Boolean.TRUE);
    }

    public BattleUnit duplicate(Boolean removeHeroType) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            BattleUnit ret = (BattleUnit) ois.readObject();
            if (removeHeroType) {
                ret.getUnit().setUnitType(null);
            }
            return ret;
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public Boolean damage(Integer value) {
        Integer newHp = this.getCurrHp() + value;
        if (newHp < 0) {
            this.setCurrHp(0);
            return Boolean.TRUE;
        } else {
            this.setCurrHp(newHp);
            return Boolean.FALSE;
        }
    }

    public void heal(Integer value) {
        this.setCurrHp(this.getCurrHp() + value);
    }


    public String toStringComplete() {
        Unit h = this.getUnit();
        return "H{" + "id=" + h.getId().substring(h.getId().length() - 5, h.getId().length() - 1)
                + ", ht=" + h.getUnitType() + ", e=" + h.getExperience()
                + ", def=" + h.getDefenses()
                + ", s=" + h.getSpeed() + ", cc=" + h.getCritChance()
                + ", cd=" + h.getCritDamage() + ", dc=" + h.getDodgeChance()
                + ", hp=" + h.getHp() + ", currDef=" + currDefenses + ", currS=" + currSpeed
                + ", currCcc=" + currCritChance
                + ", currCd=" + currCritDamage + ", currDc=" + currDodgeChance
                + ", currHp=" + currHp + ", canDoAction=" + canDoAction
                + ", currBuffs=" + currBuffs + '}';
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.battleTeamType);
        hash = 59 * hash + Objects.hashCode(this.position);
        hash = 59 * hash + Objects.hashCode(this.unit);
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
        final BattleUnit other = (BattleUnit) obj;
        if (this.battleTeamType != other.battleTeamType) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        return Objects.equals(this.unit, other.unit);
    }


}
