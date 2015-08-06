package com.diplomski.katedra.services.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentPredmetAss;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface AdminService {

    public Predavac authenticate(String username, String password) throws Exception;

    public void saveStudent(Student s);
    public void prijaviStudenta(String brojIndeksa, int predmet, int year);

    public List<Predmet> findAllPredmets();
    public List<Integer> getYears();
    public List<StudentPredmetAss> findAllStudentsInfo(int predmet, int year);
}
