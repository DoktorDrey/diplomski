package com.diplomski.katedra.db.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
@Table(name = "aktivnost", schema = "", catalog = "katedra")
public class Aktivnost {
    private int id;
    private TipAktivnosti tipAktivnosti;
    private Program program;
    private Timestamp datum;
    private Float vrednost;
    private int minPoints;
    private int maxPoints;
    private int status;

    @Id
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tip_aktivnosti")
    public TipAktivnosti getTipAktivnosti() {
        return tipAktivnosti;
    }

    public void setTipAktivnosti(TipAktivnosti tipAktivnosti) {
        this.tipAktivnosti = tipAktivnosti;
    }

    @ManyToOne
    @JoinColumn(name = "program")
    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }


    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aktivnost aktivnost = (Aktivnost) o;

        if (id != aktivnost.id) return false;
        if (program != aktivnost.program) return false;
        if (tipAktivnosti != aktivnost.tipAktivnosti) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

    @Basic
    @Column(name = "datum", nullable = true, insertable = true, updatable = true)
    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    @Basic
    @Column(name = "vrednost", nullable = true, insertable = true, updatable = true, precision = 2)
    public Float getVrednost() {
        return vrednost;
    }

    public void setVrednost(Float vrednost) {
        this.vrednost = vrednost;
    }
    @Basic
    @Column(name = "min_points", nullable = false, insertable = true, updatable = true)
    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    @Basic
    @Column(name = "max_points", nullable = false, insertable = true, updatable = true)
    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }
}
