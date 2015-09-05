package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentPredmetAss;
import com.diplomski.katedra.db.model.StudentPredmetAssPK;

import java.util.List;

/**
 * Created by andrija on 8/6/15.
 */
public interface StudentPredmetAssDao extends GenericDao<StudentPredmetAss, StudentPredmetAssPK> {
    public List<StudentPredmetAss> getStudentsByProgram(Program program);
    public StudentPredmetAss getProgramForStudent(Program program, Student student);
}
