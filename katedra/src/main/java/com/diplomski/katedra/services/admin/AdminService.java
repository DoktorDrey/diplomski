package com.diplomski.katedra.services.admin;

import com.diplomski.katedra.db.model.*;

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

    List<Aktivnost> getActivities(int predmet, int year);
}
