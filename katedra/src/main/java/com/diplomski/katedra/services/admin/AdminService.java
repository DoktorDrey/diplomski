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
    public List<Predmet> findAllPredmetsForPredavac(Predavac predavac);
    public List<Integer> getYears();
    public List<StudentPredmetAss> findAllStudentsInfo(int predmet, int year);

    List<Activity> getActivities(Program program);
    List<Aktivnost> vratiAktivnosti(Program program);
    List<StudentAktivnostAss> getStudentActivities(int predmet, int year);

    public void unesiRezultat(String brojIndeksa, double brojPoena, Aktivnost selectedActivity);

    List<TipAktivnosti> findAllActivityTypes();

    void setProgramActivities(List<Activity> activities, ProgramOcene programOcene);

    Program findProgram(int predmet, int year);
}
