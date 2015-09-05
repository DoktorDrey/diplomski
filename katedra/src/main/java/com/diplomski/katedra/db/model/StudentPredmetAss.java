package com.diplomski.katedra.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andrija on 8/6/15.
 */
@Entity
@Table(name = "student_predmet_ass", schema = "", catalog = "katedra")
@IdClass(StudentPredmetAssPK.class)
public class StudentPredmetAss implements Serializable{
    private Student studentId;
    private Program programId;
    private Integer konacnaOcena;
    private Double brojBodova;

    @Id
//    @Column(name = "student_id", nullable = false, insertable = true, updatable = true)
    @ManyToOne
    @JoinColumn(name = "student_id")
    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "program_id")
    public Program getProgramId() {
        return programId;
    }

    public void setProgramId(Program programId) {
        this.programId = programId;
    }

    @Basic
    @Column(name = "konacna_ocena", nullable = true, insertable = true, updatable = true)
    public Integer getKonacnaOcena() {
        return konacnaOcena;
    }

    public void setKonacnaOcena(Integer konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

    @Basic
    @Column(name = "broj_bodova", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Double brojBodova) {
        this.brojBodova = brojBodova;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentPredmetAss that = (StudentPredmetAss) o;

        if (brojBodova != null ? !brojBodova.equals(that.brojBodova) : that.brojBodova != null) return false;
        if (konacnaOcena != null ? !konacnaOcena.equals(that.konacnaOcena) : that.konacnaOcena != null) return false;
        if (programId != null ? !programId.equals(that.programId) : that.programId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = programId != null ? programId.hashCode() : 0;
        result = 31 * result + (konacnaOcena != null ? konacnaOcena.hashCode() : 0);
        result = 31 * result + (brojBodova != null ? brojBodova.hashCode() : 0);
        return result;
    }
}
