package com.diplomski.katedra.db.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by andrija on 8/6/15.
 */
public class StudentAktivnostAssPK implements Serializable {
    private Integer student;
    private Integer aktivnost;

    @Column(name = "student", nullable = false, insertable = true, updatable = true)
    @Id
    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    @Column(name = "aktivnost", nullable = false, insertable = true, updatable = true)
    @Id
    public Integer getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(Integer aktivnost) {
        this.aktivnost = aktivnost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentAktivnostAssPK that = (StudentAktivnostAssPK) o;

        if (aktivnost != null ? !aktivnost.equals(that.aktivnost) : that.aktivnost != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (aktivnost != null ? aktivnost.hashCode() : 0);
        return result;
    }
}
