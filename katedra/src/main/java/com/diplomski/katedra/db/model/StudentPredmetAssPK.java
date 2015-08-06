package com.diplomski.katedra.db.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by andrija on 8/6/15.
 */
public class StudentPredmetAssPK implements Serializable {
    private Integer studentId;
    private Integer programId;

    @Column(name = "student_id", nullable = false, insertable = true, updatable = true)
    @Id
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Column(name = "program_id", nullable = false, insertable = true, updatable = true)
    @Id
    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentPredmetAssPK that = (StudentPredmetAssPK) o;

        if (programId != null ? !programId.equals(that.programId) : that.programId != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (programId != null ? programId.hashCode() : 0);
        return result;
    }
}
