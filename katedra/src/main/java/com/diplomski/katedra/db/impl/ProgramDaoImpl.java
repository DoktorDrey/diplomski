package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.ProgramDao;
import com.diplomski.katedra.db.model.Program;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andrija on 7/30/15.
 */
@Repository("ProgramDao")
public class ProgramDaoImpl extends HibernateDao<Program, Integer> implements ProgramDao {
    private static final Logger logger = Logger.getLogger(ProgramDaoImpl.class);


    @Override
    public Program findProgram(int predmet, int year) {
        Query query = currentSession().createQuery("from Program P where P.idPredmeta = "+predmet+" and P.godina="+year);
        logger.debug(query.getQueryString());
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (Program) result.get(0);
    }
}
