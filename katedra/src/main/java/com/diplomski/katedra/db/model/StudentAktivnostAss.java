package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by andrija on 8/6/15.
 */
@Entity
@Table(name = "student_aktivnost_ass", schema = "", catalog = "katedra")
@IdClass(StudentAktivnostAssPK.class)
public class StudentAktivnostAss {
    private Integer student;
    private Integer aktivnost;
    private Float brojPoena;

    @Id
    @Column(name = "student", nullable = false, insertable = true, updatable = true)
    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    @Id
    @Column(name = "aktivnost", nullable = false, insertable = true, updatable = true)
    public Integer getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(Integer aktivnost) {
        this.aktivnost = aktivnost;
    }

    @Basic
    @Column(name = "broj_poena", nullable = true, insertable = true, updatable = true, precision = 2)
    public Float getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(Float brojPoena) {
        this.brojPoena = brojPoena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentAktivnostAss that = (StudentAktivnostAss) o;

        if (aktivnost != null ? !aktivnost.equals(that.aktivnost) : that.aktivnost != null) return false;
        if (brojPoena != null ? !brojPoena.equals(that.brojPoena) : that.brojPoena != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (aktivnost != null ? aktivnost.hashCode() : 0);
        result = 31 * result + (brojPoena != null ? brojPoena.hashCode() : 0);
        return result;
    }
}