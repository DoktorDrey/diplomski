package com.diplomski.katedra.services.admin;

import com.diplomski.katedra.db.dao.PredavacDao;
import com.diplomski.katedra.db.dao.PredmetDao;
import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.db.model.Student;
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
        studentDao.prijaviStudenta(student, predmet, year);
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
}
