package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.AktivnostDao;
import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Program;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("AktivnostDao")
public class AktivnostDaoImpl extends HibernateDao<Aktivnost, Integer> implements AktivnostDao {
    @Override
    public List<Aktivnost> findForProgram(Program program) {
        Query query = currentSession().createQuery("from Aktivnost A where A.program = "+program.getId());
        List result = query.list();
        return result;
    }
}
