package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
@IdClass(ProgramPK.class)
public class Program {
    private int id;
    private int godina;
    private int idPredmeta;
    private int maxBrojPoena;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "godina")
    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    @Basic
    @Column(name = "max_broj_poena")
    public int getMaxBrojPoena() {
        return maxBrojPoena;
    }

    public void setMaxBrojPoena(int maxBrojPoena) {
        this.maxBrojPoena = maxBrojPoena;
    }


    @Basic
    @Column(name = "id_predmeta")
    public int getIdPredmeta() {
        return idPredmeta;
    }

    public void setIdPredmeta(int idPredmeta) {
        this.idPredmeta = idPredmeta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        if (godina != program.godina) return false;
        if (id != program.id) return false;
        if (idPredmeta != program.idPredmeta) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + godina;
        result = 31 * result + idPredmeta;
        return result;
    }
}
