package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.PredavacDao;
import com.diplomski.katedra.db.model.Predavac;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("PredavacDao")
public class PredavacDaoImpl extends HibernateDao<Predavac, Integer> implements PredavacDao {
    private static final Logger logger = Logger.getLogger(PredavacDaoImpl.class);

    @Override
    public Predavac getByUserPass(String username, String password) {
        Query query = currentSession().createQuery("from Predavac P where P.username = '"+username+"' and P.password = '"+password+"'");
        logger.debug(query.getQueryString());
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (Predavac) result.get(0);
    }
}
