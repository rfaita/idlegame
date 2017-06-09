package com.idle.game.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "pvproll")
public class PvpRoll implements Serializable {

    @Id
    @SequenceGenerator(name = "seqPvpRoll", sequenceName = "seqPvpRoll", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(generator = "seqPvpRoll")
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @ManyToOne
    @JoinColumn(name = "idFormationHigher")
    private Formation formationHigher;
    @ManyToOne
    @JoinColumn(name = "idFormationLower")
    private Formation formationLower;
    @ManyToOne
    @JoinColumn(name = "idFormationRandom")
    private Formation formationRandom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Formation getFormationHigher() {
        return formationHigher;
    }

    public void setFormationHigher(Formation formationHigher) {
        this.formationHigher = formationHigher;
    }

    public Formation getFormationLower() {
        return formationLower;
    }

    public void setFormationLower(Formation formationLower) {
        this.formationLower = formationLower;
    }

    public Formation getFormationRandom() {
        return formationRandom;
    }

    public void setFormationRandom(Formation formationRandom) {
        this.formationRandom = formationRandom;
    }

}
