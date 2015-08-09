package com.diplomski.katedra.db.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
public class Aktivnost {
    private int id;
    private TipAktivnosti tipAktivnosti;
    private int program;
    private Timestamp datum;
    private Float vrednost;

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

    @Basic
    @Column(name = "program", nullable = false, insertable = true, updatable = true)
    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
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
        result = 31 * result + program;
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
}
