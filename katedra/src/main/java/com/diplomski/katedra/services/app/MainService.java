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

    public void updateStudent(Student student);

    public void registerStudent(Student student) throws Exception;

    public List<Student> getAllStudents();

    boolean activation(String token);

    List<StudentAktivnostAss> getStudentActivities(Student student);

    List<Aktivnost> getFutureActivities(Student student);
}
