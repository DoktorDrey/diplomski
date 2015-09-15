/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diplomski.katedra.services.app;

import com.diplomski.katedra.db.dao.AktivnostDao;
import com.diplomski.katedra.db.dao.StudentAktivnostDao;
import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentAktivnostAss;
import com.diplomski.katedra.security.Crypto;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.Messages;
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

    @Inject
    private Messages messages;

    @Inject
    private StudentAktivnostDao studentAktivnostDao;

    @Inject
    private AktivnostDao aktivnostDao;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentDao.izlistaj();
        return students;
    }

    @Override
    public boolean aktivacijaKorisnika(String token) {
        Student student = studentDao.getByToken(token);
        if(student == null) {
            return false;
        }
        student.setActivated(true);
        studentDao.sacuvaj(student);
        return true;
    }

    @Override
    public List<StudentAktivnostAss> pronadjiAktivnostiKorisnika(Student student) {
        logger.debug("getStudentActivities");
        logger.debug(student.getId());
        List<StudentAktivnostAss> activities = studentAktivnostDao.findForStudent(student);
        return activities;
    }

    @Override
    public List<Aktivnost> getFutureActivities(Student student) {
        List<Aktivnost> activities = aktivnostDao.findFuture(student);
        return activities;
    }

    public Student authenticate(String email, String password) throws Exception {
        logger.debug(email);
        logger.debug(Crypto.hash(password));
        Student student = studentDao.getByUserPass(email, Crypto.hash(password));
        if(student == null) {
            throw new Exception("err_invalid_credentials");
        }
        return student;
    }


    public void registracijaKorisnika(Student student) throws Exception {
        student.setPassword(Crypto.hash(student.getPassword()));
        student.setToken(Crypto.generateToken());
        studentDao.dodaj(student);
    }


    public void izmenaKorisnika(Student student) {
        logger.debug(student.getBrojIndeksa());
        studentDao.sacuvaj(student);
    }
  

   
    
}
