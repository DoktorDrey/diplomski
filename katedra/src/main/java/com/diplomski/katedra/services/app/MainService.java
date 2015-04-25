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

    public Student authenticate(String userName, String password) throws Exception;

    public void updateStudent(String email);

    public boolean postojiStudent(String brIndeksa, String ime, String prezime);

    public List<Student> getAllStudents();
}
