package com.diplomski.katedra.db.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public class ProgramPK implements Serializable {
    private int id;
    private int godina;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "godina")
    @Id
    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgramPK programPK = (ProgramPK) o;

        if (godina != programPK.godina) return false;
        if (id != programPK.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + godina;
        return result;
    }
}
