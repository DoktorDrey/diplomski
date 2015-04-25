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

    public Student authenticate(String userName, String password) throws Exception {
        logger.debug(userName);
        logger.debug(Crypto.hash(password));
        Student student = studentDao.getByUserPass(userName, Crypto.hash(password));
        if(student == null) {
            throw new Exception("Invalid username or password!");
        }
        return student;
    }

   
    public boolean postojiStudent(String brIndeksa, String ime, String prezime) {
        /*try {
            return  DatabaseBroker.vratiDB().postojiStudent(brIndeksa, ime, prezime);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException ex1){
            ex1.printStackTrace();
        }*/
        return false;
    }

    public void updateStudent(String email) {
        
    }
  

   
    
}
