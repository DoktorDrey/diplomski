package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
@Table(name = "tip_predavaca", schema = "", catalog = "katedra")
public class TipPredavaca {
    private int id;
    private String pozicija;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pozicija")
    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipPredavaca that = (TipPredavaca) o;

        if (id != that.id) return false;
        if (pozicija != null ? !pozicija.equals(that.pozicija) : that.pozicija != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pozicija != null ? pozicija.hashCode() : 0);
        return result;
    }
}
