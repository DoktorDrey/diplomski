package com.diplomski.katedra.services.admin;

import com.diplomski.katedra.db.dao.PredavacDao;
import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.security.Crypto;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public class AdminServiceImpl implements AdminService {
    @Inject
    private PredavacDao predavacDao;

    @Inject
    private StudentDao studentDao;

    @Override
    public Predavac authenticate(String username, String password) throws Exception {
        System.out.println(Crypto.hash(password));
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
}
