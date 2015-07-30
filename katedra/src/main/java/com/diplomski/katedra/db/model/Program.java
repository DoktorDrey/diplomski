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
    private Integer idPredmeta;
    private Integer maxBrojPoena;
    private Integer bpPredavanja;
    private Integer bpVezbe;

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

    @Basic
    @Column(name = "id_predmeta", nullable = false, insertable = true, updatable = true)
    public Integer getIdPredmeta() {
        return idPredmeta;
    }

    public void setIdPredmeta(Integer idPredmeta) {
        this.idPredmeta = idPredmeta;
    }

    @Basic
    @Column(name = "max_broj_poena", nullable = true, insertable = true, updatable = true)
    public Integer getMaxBrojPoena() {
        return maxBrojPoena;
    }

    public void setMaxBrojPoena(Integer maxBrojPoena) {
        this.maxBrojPoena = maxBrojPoena;
    }

    @Basic
    @Column(name = "bp_predavanja", nullable = true, insertable = true, updatable = true)
    public Integer getBpPredavanja() {
        return bpPredavanja;
    }

    public void setBpPredavanja(Integer bpPredavanja) {
        this.bpPredavanja = bpPredavanja;
    }

    @Basic
    @Column(name = "bp_vezbe", nullable = true, insertable = true, updatable = true)
    public Integer getBpVezbe() {
        return bpVezbe;
    }

    public void setBpVezbe(Integer bpVezbe) {
        this.bpVezbe = bpVezbe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        if (bpPredavanja != null ? !bpPredavanja.equals(program.bpPredavanja) : program.bpPredavanja != null)
            return false;
        if (bpVezbe != null ? !bpVezbe.equals(program.bpVezbe) : program.bpVezbe != null) return false;
        if (godina != null ? !godina.equals(program.godina) : program.godina != null) return false;
        if (id != null ? !id.equals(program.id) : program.id != null) return false;
        if (idPredmeta != null ? !idPredmeta.equals(program.idPredmeta) : program.idPredmeta != null) return false;
        if (maxBrojPoena != null ? !maxBrojPoena.equals(program.maxBrojPoena) : program.maxBrojPoena != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (godina != null ? godina.hashCode() : 0);
        result = 31 * result + (idPredmeta != null ? idPredmeta.hashCode() : 0);
        result = 31 * result + (maxBrojPoena != null ? maxBrojPoena.hashCode() : 0);
        result = 31 * result + (bpPredavanja != null ? bpPredavanja.hashCode() : 0);
        result = 31 * result + (bpVezbe != null ? bpVezbe.hashCode() : 0);
        return result;
    }
}
