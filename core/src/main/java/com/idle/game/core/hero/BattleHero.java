package com.idle.game.core.hero;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.core.item.ItemType;
import com.idle.game.core.item.Item;
import com.idle.game.core.buff.Buff;
import com.idle.game.core.constant.IdleConstants;
import static com.idle.game.core.constant.IdleConstants.LOG;
import com.idle.game.core.passive.Passive;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.type.BattleHeroType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

/**
 *
 * @author rafael
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BattleHero implements Serializable {

    private String id;
    private BattleHeroType heroType;
    private Integer level;

    private Integer dmg;
    private Integer armor;
    private Integer magicResist;
    private Integer speed;
    private Integer luck;
    private Integer critChance;
    private Integer critDamage;
    private Integer dodgeChance;
    private Integer hp;

    private Integer currDmg;
    private Integer currArmor;
    private Integer currMagicResist;
    private Integer currSpeed;
    private Integer currLuck;
    private Integer currCritChance;
    private Integer currCritDamage;
    private Integer currDodgeChance;
    private Integer currHp;
    private Boolean canDoAction;

    private List<Buff> currBuffs;

    private Item chest;
    private Item weapon;
    private Item boot;
    private Item ring;
    private Item ammulet;
    private Item jewel;

    public BattleHero() {
    }

    public BattleHero(String id, BattleHeroType heroType, Integer level) {
        this.id = id;
        this.heroType = heroType;
        this.calcAtributtes();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getChest() {
        return chest;
    }

    public Item getWeapon() {
        return weapon;
    }

    public Item getBoot() {
        return boot;
    }

    public Item getRing() {
        return ring;
    }

    public Item getAmmulet() {
        return ammulet;
    }

    public Item getJewel() {
        return jewel;
    }

    public BattleHeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(BattleHeroType heroType) {
        this.heroType = heroType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDmg() {
        return dmg;
    }

    public void setDmg(Integer dmg) {
        this.dmg = dmg;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(Integer nagicResist) {
        this.magicResist = nagicResist;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getLuck() {
        return luck;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
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

    public Integer getCurrArmor() {
        return currArmor;
    }

    public void setCurrArmor(Integer currArmor) {
        this.currArmor = currArmor;
    }

    public Integer getCurrMagicResist() {
        return currMagicResist;
    }

    public void setCurrMagicResist(Integer currMagicResist) {
        this.currMagicResist = currMagicResist;
    }

    public Integer getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrSpeed(Integer currSpeed) {
        this.currSpeed = currSpeed;
    }

    public Integer getCurrLuck() {
        return currLuck;
    }

    public Boolean getCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(Boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public void setCurrLuck(Integer currLuck) {
        this.currLuck = currLuck;
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

    public void addBuff(Buff buff) {
        this.currBuffs.add(buff);
    }

    public Integer getCurrDmg() {
        return currDmg;
    }

    public void setCurrDmg(Integer currDmg) {
        this.currDmg = currDmg;
    }

    public void setCurrHp(Integer currHp) {
        if (currHp > getHp()) {
            currHp = getHp();
        }
        if (currHp < 0) {
            currHp = 0;
        }
        this.currHp = currHp;
    }

    public void setBoot(Item i) throws Exception {
        assert i.getItemType().equals(ItemType.BOOT) : "Item must be a boot, BUG";
        this.boot = i;
        calcAtributtes();
    }

    public void setChest(Item i) throws Exception {
        assert i.getItemType().equals(ItemType.CHEST) : "Item must be a chest, BUG";
        this.chest = i;
        calcAtributtes();
    }

    public void setWeapon(Item i) throws Exception {
        assert i.getItemType().equals(ItemType.WEAPON) : "Item must be a weapon, BUG";
        this.weapon = i;
        calcAtributtes();
    }

    public void setRing(Item i) throws Exception {
        assert i.getItemType().equals(ItemType.RING) : "Item must be a ring, BUG";
        this.ring = i;
        calcAtributtes();
    }

    public void setAmmulet(Item i) throws Exception {
        assert i.getItemType().equals(ItemType.AMMULET) : "Item must be a ammulet, BUG";
        this.ammulet = i;
        calcAtributtes();
    }

    public void setJewel(Item i) throws Exception {
        assert i.getItemType().equals(ItemType.JEWEL) : "Item must be a jewel, BUG";
        this.jewel = i;
        calcAtributtes();
    }

    public void prepareToBattle() {
        LOG.log(Level.INFO, "[prepareToBattle]");
        this.prepareToTurn();
        this.setCurrHp(this.getHp());
        this.setCurrBuffs(new ArrayList<>());
    }

    public void prepareToTurn() {
        LOG.log(Level.INFO, "[prepareToTurn][hero] {0}", this);
        this.setCurrArmor(this.getArmor());
        LOG.log(Level.INFO, "[prepareToTurn][armor] {0}", this.getCurrArmor());
        this.setCurrCritChance(this.getCritChance());
        LOG.log(Level.INFO, "[prepareToTurn][critChance] {0}", this.getCurrCritChance());
        this.setCurrCritDamage(this.getCritDamage());
        LOG.log(Level.INFO, "[prepareToTurn][critDmg] {0}", this.getCurrCritDamage());
        this.setCurrDodgeChance(this.getDodgeChance());
        LOG.log(Level.INFO, "[prepareToTurn][dodgeChange] {0}", this.getCurrDodgeChance());
        this.setCurrLuck(this.getLuck());
        LOG.log(Level.INFO, "[prepareToTurn][luck] {0}", this.getCurrLuck());
        this.setCurrMagicResist(this.getMagicResist());
        LOG.log(Level.INFO, "[prepareToTurn][magicResist] {0}", this.getCurrMagicResist());
        this.setCurrDmg(this.getDmg());
        LOG.log(Level.INFO, "[prepareToTurn][dmg] {0}", this.getCurrDmg());
        this.setCurrSpeed(this.getSpeed());
        LOG.log(Level.INFO, "[prepareToTurn][speed] {0}", this.getCurrSpeed());
        this.setCanDoAction(Boolean.TRUE);
        LOG.log(Level.INFO, "[prepareToTurn][canDoAction] {0}", this.getCanDoAction());
        LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER);
    }

    private Integer getMissingAttributePercentage(AttributeType at) {
        switch (at) {
            case HP:
                return (int) ((this.getHp() - this.getCurrHp()) * 1d / this.getHp() * 100);
            default:
                return 0;
        }

    }

    private void calcTradeAttribute(AttributeType o, AttributeType d, Integer ratioPercentage) {
        Double perc = this.getMissingAttributePercentage(o) / 100d * ratioPercentage / 100d;
        LOG.log(Level.INFO, "[passive][perc] {0}", perc);
        if (perc > 0) {
            switch (d) {
                case LUCK:
                    this.setCurrLuck(this.getCurrLuck() + (int) (this.getCurrLuck() * perc));
                    LOG.log(Level.INFO, "[passive][luck] {0}", this.getCurrLuck());
                    break;
                case SPEED:
                    this.setCurrSpeed(this.getCurrSpeed() + (int) (this.getCurrSpeed() * perc));
                    LOG.log(Level.INFO, "[passive][speed] {0}", this.getCurrSpeed());
                    break;
                case DODGE:
                    this.setCurrDodgeChance(this.getCurrDodgeChance() + (int) (this.getCurrDodgeChance() * perc));
                    LOG.log(Level.INFO, "[passive][dodgeChance] {0}", this.getCurrDodgeChance());
                    break;
                case CRIT_DMG:
                    this.setCurrCritDamage(this.getCurrCritDamage() + (int) (this.getCurrCritDamage() * perc));
                    LOG.log(Level.INFO, "[passive][critDmg] {0}", this.getCurrCritDamage());
                    break;
                case CRIT_CHANCE:
                    this.setCurrCritChance(this.getCurrCritChance() + (int) (this.getCurrCritChance() * perc));
                    LOG.log(Level.INFO, "[passive][critChance] {0}", this.getCurrCritChance());
                    break;
                case DMG:
                    this.setCurrDmg(this.getCurrDmg() + (int) (this.getCurrDmg() * perc));
                    LOG.log(Level.INFO, "[passive][dmg] {0}", this.getCurrDmg());
                    break;
                case ARMOR:
                    this.setCurrArmor(this.getCurrArmor() + (int) (this.getCurrArmor() * perc));
                    LOG.log(Level.INFO, "[passive][armor] {0}", this.getCurrArmor());
                    break;
                case MAGIC_RESIST:
                    this.setCurrMagicResist(this.getCurrMagicResist() + (int) (this.getCurrMagicResist() * perc));
                    LOG.log(Level.INFO, "[passive][magicResist] {0}", this.getCurrMagicResist());
                    break;
                case DEFENSE:
                    this.setCurrArmor(this.getCurrArmor() + (int) (this.getCurrArmor() * perc));
                    this.setCurrMagicResist(this.getCurrMagicResist() + (int) (this.getCurrMagicResist() * perc));
                    LOG.log(Level.INFO, "[passive][armor] {0}", this.getCurrArmor());
                    LOG.log(Level.INFO, "[passive][magicResist] {0}", this.getCurrMagicResist());
                    break;

            }
        }

    }

    public void calcPassives() {
        LOG.log(Level.INFO, "[passive][heroi] {0}", this);
        if (this.getHeroType().getPassives() != null) {
            for (Passive p : this.getHeroType().getPassives()) {
                LOG.log(Level.INFO, "[passive][type] {0}", p.getPassiveType());
                switch (p.getPassiveType()) {
                    case TRADE_ATTRIBUTE:
                        calcTradeAttribute(p.getAttributeTypeLost(), p.getAttributeTypeGain(), p.getRatioPercentage());
                        break;
                }
                LOG.log(Level.INFO, IdleConstants.LOG_DELIMITER);

            }
        }
    }

    private void calcAtributtes() {
        LOG.log(Level.INFO, "[calcAtributtes]");

        this.calcAtributtesItem(this.getAmmulet());
        this.calcAtributtesItem(this.getBoot());
        this.calcAtributtesItem(this.getChest());
        this.calcAtributtesItem(this.getJewel());
        this.calcAtributtesItem(this.getRing());
        this.calcAtributtesItem(this.getWeapon());

    }

    private void calcAtributtesItem(Item i) {
        if (i != null) {
            this.setArmor(this.getArmor() + i.getArmor());
            this.setCritChance(this.getCritChance() + i.getCritChance());
            this.setCritDamage(this.getCritDamage() + i.getCritDamage());
            this.setDodgeChance(this.getDodgeChance() + i.getDodgeChance());
            this.setHp(this.getHp() + i.getHp());
            this.setLuck(this.getLuck() + i.getLuck());
            this.setMagicResist(this.getMagicResist() + i.getMagicResist());
            this.setDmg(this.getDmg() + i.getDmg());
            this.setSpeed(this.getSpeed() + i.getSpeed());
        }
    }

    public BattleHero duplicate() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (BattleHero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "H{" + "id=" + this.id.substring(this.id.length() - 5, this.id.length() - 1)
                + ",currHp=" + this.currHp + ",type=" + this.heroType + '}';
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
        final BattleHero other = (BattleHero) obj;
        return Objects.equals(this.id, other.id);
    }

}
