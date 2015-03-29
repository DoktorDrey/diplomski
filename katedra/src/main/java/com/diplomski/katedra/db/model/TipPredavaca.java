package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
@Table(name = "tip_predavaca", schema = "", catalog = "katedra")
public class TipPredavaca {
    private int id;
    private String nazivTitule;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "naziv_titule")
    public String getNazivTitule() {
        return nazivTitule;
    }

    public void setNazivTitule(String nazivTitule) {
        this.nazivTitule = nazivTitule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipPredavaca that = (TipPredavaca) o;

        if (id != that.id) return false;
        if (nazivTitule != null ? !nazivTitule.equals(that.nazivTitule) : that.nazivTitule != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nazivTitule != null ? nazivTitule.hashCode() : 0);
        return result;
    }
}
