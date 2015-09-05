package com.diplomski.katedra.db.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrija Ilic on 9/3/2015.
 */
@Entity
@Table(name = "program_ocene", schema = "", catalog = "katedra")
public class ProgramOcene implements Serializable {
    private Integer id;
    private Program programId;
    private Integer sest;
    private Integer sedam;
    private Integer osam;
    private Integer devet;
    private Integer deset;

    @Id
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "program")
    public Program getProgramId() {
        return programId;
    }

    public void setProgramId(Program programId) {
        this.programId = programId;
    }

    @Basic
    @Column(name = "sest", nullable = true, insertable = true, updatable = true)
    public Integer getSest() {
        return sest;
    }

    public void setSest(Integer sest) {
        this.sest = sest;
    }

    @Basic
    @Column(name = "sedam", nullable = true, insertable = true, updatable = true)
    public Integer getSedam() {
        return sedam;
    }

    public void setSedam(Integer sedam) {
        this.sedam = sedam;
    }

    @Basic
    @Column(name = "osam", nullable = true, insertable = true, updatable = true)
    public Integer getOsam() {
        return osam;
    }

    public void setOsam(Integer osam) {
        this.osam = osam;
    }

    @Basic
    @Column(name = "devet", nullable = true, insertable = true, updatable = true)
    public Integer getDevet() {
        return devet;
    }

    public void setDevet(Integer devet) {
        this.devet = devet;
    }

    @Basic
    @Column(name = "deset", nullable = true, insertable = true, updatable = true)
    public Integer getDeset() {
        return deset;
    }

    public void setDeset(Integer deset) {
        this.deset = deset;
    }

    public ProgramOcene(Integer sest, Integer sedam, Integer osam, Integer devet, Integer deset, Program program) {
        this.sest = sest;
        this.sedam = sedam;
        this.osam = osam;
        this.devet = devet;
        this.deset = deset;
        this.programId = program;
    }

    public ProgramOcene() {
    }

    public static ProgramOcene getDefault(Program program) {
        return new ProgramOcene(51,61,71,81,91, program);
    }


}
