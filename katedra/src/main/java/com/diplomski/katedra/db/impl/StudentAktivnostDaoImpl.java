package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentAktivnostDao;
import com.diplomski.katedra.db.model.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andrija on 8/6/15.
 */
@Repository("StudentAktivnostDao")
public class StudentAktivnostDaoImpl extends HibernateDao<StudentAktivnostAss,StudentAktivnostAssPK> implements StudentAktivnostDao {
    private static final Logger logger = Logger.getLogger(StudentAktivnostDaoImpl.class);

    @Override
    public List<StudentAktivnostAss> findForProgram(Program program) {
//        String q = "SELECT * from student_aktivnost_ass where aktivnost in (SELECT id from aktivnost where program="+program.getId() + ")";
//        logger.debug(q);
//        Query query = currentSession().createSQLQuery(q);
//        Query query = currentSession().createQuery("from StudentAktivnostAss S where S.aktivnost = "+program.getId());
        Query query = currentSession().createQuery("from StudentAktivnostAss S where S.aktivnost in (SELECT A.id from Aktivnost A where A.program="+program.getId()+")");
        logger.debug(query.getQueryString());
        List result = query.list();
        return result;
    }

    @Override
     public List<StudentAktivnostAss> findForStudent(Student student) {
        Query query = currentSession().createQuery("from StudentAktivnostAss S where S.student = "+student.getId() + " and S.brojPoena>0");
        logger.debug(query.getQueryString());
        List result = query.list();
        return result;
    }

    @Override
    public StudentAktivnostAss findActivity(Student student, Aktivnost selectedActivity) {
        Query query = currentSession().createQuery("from StudentAktivnostAss S where S.aktivnost = "+selectedActivity.getId()+" and S.student="+student.getId());
        logger.debug(query.getQueryString());
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (StudentAktivnostAss) result.get(0);
    }
}
