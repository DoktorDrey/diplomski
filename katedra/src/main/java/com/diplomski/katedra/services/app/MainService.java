/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diplomski.katedra.services.app;

import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentAktivnostAss;

import java.util.List;

/**
 *
 * @author PC
 */
public interface MainService {

    public Student authenticate(String email, String password) throws Exception;

    public void izmenaKorisnika(Student student);

    public void registracijaKorisnika(Student student) throws Exception;

    public List<Student> getAllStudents();

    boolean aktivacijaKorisnika(String token);

    List<StudentAktivnostAss> pronadjiAktivnostiKorisnika(Student student);

    List<Aktivnost> getFutureActivities(Student student);
}
