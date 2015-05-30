package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("StudentDao")
public class StudentDaoImpl extends HibernateDao<Student, String> implements StudentDao{
    private static final Logger logger = Logger.getLogger(StudentDaoImpl.class);

    @Override
    public Student getByUserPass(String email, String password) {
        logger.debug("test");
        Query query = currentSession().createQuery("from Student S where S.email = '"+email+"' and S.password = '"+password+"'");
        logger.debug("test prosao");
        logger.debug(query.getQueryString());
        logger.debug("test prosao");
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (Student) result.get(0);
    }

    @Override
    public Student getByBrIndeks(String brIndeks) {
        Query query = currentSession().createQuery("from Student S where S.brojIndeksa = '"+brIndeks+"'");
        logger.debug(query.getQueryString());
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (Student) result.get(0);
    }
}
