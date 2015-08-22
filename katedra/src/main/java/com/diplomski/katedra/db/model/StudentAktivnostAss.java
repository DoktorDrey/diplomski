package com.diplomski.katedra.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andrija on 8/6/15.
 */
@Entity
@Table(name = "student_aktivnost_ass", schema = "", catalog = "katedra")
@IdClass(StudentAktivnostAssPK.class)
public class StudentAktivnostAss implements Serializable{
    private Student student;
    private Aktivnost aktivnost;
    private Double brojPoena;

    @Id
    @ManyToOne
    @JoinColumn(name = "student")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "aktivnost")
    public Aktivnost getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(Aktivnost aktivnost) {
        this.aktivnost = aktivnost;
    }

    @Basic
    @Column(name = "broj_poena", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(Double brojPoena) {
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
