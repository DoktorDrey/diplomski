package com.diplomski.katedra.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
public class Aktivnost {
    private int id;
    private int tipAktivnosti;
    private int program;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tip_aktivnosti")
    public int getTipAktivnosti() {
        return tipAktivnosti;
    }

    public void setTipAktivnosti(int tipAktivnosti) {
        this.tipAktivnosti = tipAktivnosti;
    }

    @Basic
    @Column(name = "program")
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
        result = 31 * result + tipAktivnosti;
        result = 31 * result + program;
        return result;
    }
}
