package com.diplomski.katedra.services.admin;

import com.diplomski.katedra.db.dao.*;
import com.diplomski.katedra.db.model.*;
import com.diplomski.katedra.security.Crypto;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);
    @Inject
    private PredavacDao predavacDao;

    @Inject
    private StudentDao studentDao;

    @Inject
    private PredmetDao predmetDao;

    @Inject
    private ProgramDao programDao;

    @Inject
    private AktivnostDao aktivnostDao;

    @Inject
    private StudentPredmetAssDao studentPredmetAssDao;

    private static final int numOfLastYears = 10;

    @Override
    public Predavac authenticate(String username, String password) throws Exception {
        logger.debug(Crypto.hash(password));
        logger.debug(username);
        Predavac predavac = predavacDao.getByUserPass(username, Crypto.hash(password));
        if(predavac == null) {
            throw new Exception("Invalid username or password!");
        }
        return predavac;
    }

    @Override
    public void saveStudent(Student s) {
        studentDao.add(s);
    }

    @Override
    public void prijaviStudenta(String brojIndeksa, int predmet, int year) {
        Student student = studentDao.getByBrIndeks(brojIndeksa);
        Program program = programDao.findProgram(predmet, year);
        studentDao.prijaviStudenta(student, program);
    }

    @Override
    public List<Predmet> findAllPredmets() {
        return predmetDao.list();
    }

    @Override
    public List<Integer> getYears() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        List<Integer> years= new ArrayList<Integer>();
        for(int i = 0; i < numOfLastYears; i++) {
            years.add(year-i);
        }
        return years;
    }

    @Override
    public List<StudentPredmetAss> findAllStudentsInfo(int predmet, int year) {
        Program program = programDao.findProgram(predmet, year);
        List<StudentPredmetAss> students = studentPredmetAssDao.getStudentsByProgram(program);
        logger.debug(students.toString());
        return students;
    }

    @Override
    public List<Aktivnost> getActivities(int predmet, int year) {
        Program program = programDao.findProgram(predmet, year);
        logger.debug(program.getId());
        /*Aktivnost a = new Aktivnost();
        Aktivnost b = new Aktivnost();
        a.setId(1);
        b.setId(2);
        TipAktivnosti tipAktivnosti = new TipAktivnosti();
        tipAktivnosti.setId(1);
        tipAktivnosti.setNazivAktivnosti("test");
        a.setTipAktivnosti(tipAktivnosti);
        b.setTipAktivnosti(tipAktivnosti);*/
        List<Aktivnost> aktivnosts = aktivnostDao.findForProgram(program);
//        aktivnosts.add(a);
//        aktivnosts.add(b);
        return aktivnosts;
    }
}
