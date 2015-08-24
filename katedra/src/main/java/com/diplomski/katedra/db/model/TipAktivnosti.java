package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
@Table(name = "tip_aktivnosti", schema = "", catalog = "katedra")
public class TipAktivnosti {
    private int id;
    private String nazivAktivnosti;

    public TipAktivnosti(int id, String nazivAktivnosti) {
        this.nazivAktivnosti = nazivAktivnosti;
        this.id = id;
    }

    public TipAktivnosti() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "naziv_aktivnosti")
    public String getNazivAktivnosti() {
        return nazivAktivnosti;
    }

    public void setNazivAktivnosti(String nazivAktivnosti) {
        this.nazivAktivnosti = nazivAktivnosti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipAktivnosti that = (TipAktivnosti) o;

        if (id != that.id) return false;
        if (nazivAktivnosti != null ? !nazivAktivnosti.equals(that.nazivAktivnosti) : that.nazivAktivnosti != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nazivAktivnosti != null ? nazivAktivnosti.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return nazivAktivnosti;
    }
}
