package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by andrija on 8/20/15.
 */
@Entity
@Table(name = "predmet_predavac", schema = "", catalog = "katedra")
@IdClass(PredmetPredavacPK.class)
public class PredmetPredavac {
    private Predmet predmet;
    private Predavac predavac;

    @Id
    @ManyToOne
    @JoinColumn(name = "predmet")
    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "predavac")
    public Predavac getPredavac() {
        return predavac;
    }

    public void setPredavac(Predavac predavac) {
        this.predavac = predavac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PredmetPredavac that = (PredmetPredavac) o;

        if (predavac != null ? !predavac.equals(that.predavac) : that.predavac != null) return false;
        if (predmet != null ? !predmet.equals(that.predmet) : that.predmet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = predmet != null ? predmet.hashCode() : 0;
        result = 31 * result + (predavac != null ? predavac.hashCode() : 0);
        return result;
    }
}
