package com.diplomski.katedra.services.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Student;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface AdminService {

    public Predavac authenticate(String username, String password) throws Exception;

    public void saveStudent(Student s);
}
