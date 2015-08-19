package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentAktivnostDao;
import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentAktivnostAss;
import com.diplomski.katedra.db.model.StudentAktivnostAssPK;
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
        Query query = currentSession().createQuery("from StudentAktivnostAss S where S.aktivnost = "+program.getId());
        List result = query.list();
        return result;
    }

    @Override
     public List<StudentAktivnostAss> findForStudent(Student student) {
        Query query = currentSession().createQuery("from StudentAktivnostAss S where S.student = "+student.getId() + " and S.aktivnost.status=1");
        logger.debug(query.getQueryString());
        List result = query.list();
        return result;
    }
}
