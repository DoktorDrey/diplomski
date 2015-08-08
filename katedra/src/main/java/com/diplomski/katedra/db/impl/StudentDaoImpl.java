package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentPredmetAss;
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
        Query query = currentSession().createQuery("from Student S where S.email = '"+email+"' and S.password = '"+password+"' and S.activated = true");
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

    @Override
    public void prijaviStudenta(Student student, Program program) {
        String query = "INSERT into student_predmet_ass (student_id,program_id) values ("+student.getId()+","+program.getId()+")";
        logger.debug(query);
        int result = currentSession().createSQLQuery(query).executeUpdate();
        logger.debug(result);
    }

    @Override
    public Student getByToken(String token) {
        Query query = currentSession().createQuery("from Student S where S.token = '"+token+"' and S.activated = false");
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (Student) result.get(0);
    }
}
