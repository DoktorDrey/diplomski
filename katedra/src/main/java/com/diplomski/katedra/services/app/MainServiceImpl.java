/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diplomski.katedra.services.app;

import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.security.Crypto;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

/**
 *
 * @author PC
 */
public class MainServiceImpl implements MainService {
    private static final Logger logger = Logger.getLogger(MainServiceImpl.class);

    @Inject
    private StudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentDao.list();
        return students;
    }

    public Student authenticate(String email, String password) throws Exception {
        logger.debug(email);
        logger.debug(Crypto.hash(password));
        Student student = studentDao.getByUserPass(email, Crypto.hash(password));
        if(student == null) {
            throw new Exception("Invalid email or password!");
        }
        return student;
    }


    public boolean registerStudent(Student student) throws Exception {
        Student s = studentDao.getByBrIndeks(student.getBrojIndeksa());
        logger.debug(s);
        logger.debug(s.getBrojIndeksa());
        student.setId(s.getId());
        if(s == null) {
            throw new Exception("Student doesn't exist!");
        } else {
            studentDao.update(student);
        }
        return true;
    }

    public void updateStudent(String email) {
        
    }
  

   
    
}
