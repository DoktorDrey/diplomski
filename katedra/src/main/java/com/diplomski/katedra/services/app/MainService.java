/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diplomski.katedra.services.app;

import com.diplomski.katedra.db.model.Student;

import java.util.List;

/**
 *
 * @author PC
 */
public interface MainService {

    public Student authenticate(String email, String password) throws Exception;

    public void updateStudent(String email);

    public boolean registerStudent(Student student) throws Exception;

    public List<Student> getAllStudents();
}
