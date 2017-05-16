package com.idle.game.server.model;

import com.idle.game.core.HeroType;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rafael
 */
@Entity
public class Hero implements Serializable {

    @Id
    @SequenceGenerator(name = "seqhero", sequenceName = "seqhero", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqhero")
    private Long id;
    private String heroTypeId;
    private transient HeroType heroType;
    private Integer level;
    private Integer dmg;
    private Integer armor;
    private Integer magicResist;
    private Integer speed;
    private Integer luck;
    private Integer critChance;
    private Integer critDamage;
    private Integer dodgeChance;
    private Integer blockChance;
    private Integer hp;
    @ManyToOne
    @JoinColumn(name = "idplayer")
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeroTypeId() {
        return heroTypeId;
    }

    public void setHeroTypeId(String heroTypeId) {
        this.heroTypeId = heroTypeId;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
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

    public void setMagicResist(Integer magicResist) {
        this.magicResist = magicResist;
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

    public void setDodgeChance(Integer dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public Integer getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Integer blockChance) {
        this.blockChance = blockChance;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public com.idle.game.core.Hero toHero() {
        return new com.idle.game.core.Hero(heroType, level, dmg, armor, magicResist, speed, luck, critChance, critDamage, dodgeChance, blockChance, hp);
    }

}
