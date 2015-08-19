package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by andrija on 7/30/15.
 */
@Entity
@Table(name = "program", schema = "", catalog = "katedra")
public class Program {
    private Integer id;
    private Integer godina;
    private Predmet predmet;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "godina", nullable = false, insertable = true, updatable = true)
    public Integer getGodina() {
        return godina;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    @ManyToOne
    @JoinColumn(name = "id_predmeta")
    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        if (godina != null ? !godina.equals(program.godina) : program.godina != null) return false;
        if (id != null ? !id.equals(program.id) : program.id != null) return false;
        if (predmet != null ? !predmet.equals(program.predmet) : program.predmet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (godina != null ? godina.hashCode() : 0);
        result = 31 * result + (predmet != null ? predmet.hashCode() : 0);
        return result;
    }
}
