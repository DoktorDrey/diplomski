package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.AktivnostDao;
import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("AktivnostDao")
public class AktivnostDaoImpl extends HibernateDao<Aktivnost, Integer> implements AktivnostDao {
    private static final Logger logger = Logger.getLogger(AktivnostDaoImpl.class);
    @Override
    public List<Aktivnost> findForProgram(Program program) {
        Query query = currentSession().createQuery("from Aktivnost A where A.program = "+program.getId());
        List result = query.list();
        return result;
    }

    @Override
    public void removeActivitiesForProgram(Program program) {
        logger.debug(program.getId());
        Query query = currentSession().createQuery("DELETE from Aktivnost A where A.program = " + program.getId());
        logger.debug(query.getQueryString());
        query.executeUpdate();
    }

    @Override
    public List<Aktivnost> findFuture(Student student) {
        Query query = currentSession().createQuery("from Aktivnost A where A.id  not in (SELECT S.aktivnost from StudentAktivnostAss S where S.student = "+student.getId() + " and S.brojPoena>0) and A.program in (SELECT SP.programId from StudentPredmetAss SP where SP.studentId="+student.getId()+")");
        logger.debug(query.getQueryString());
        List result = query.list();
        return result;
    }

    @Override
    public void add(Aktivnost entity) {
        if(entity.getId() == 0) {
            logger.debug(entity);
            super.add(entity);
            return;
        }
        logger.debug(entity);
        super.update(entity);
    }
}
