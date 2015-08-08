package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentPredmetAss;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface StudentDao extends GenericDao<Student, String>{
    public Student getByUserPass(String email, String password);
    public Student getByBrIndeks(String brIndeks);
    public void prijaviStudenta(Student student, Program program);

    Student getByToken(String token);
}
