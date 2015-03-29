package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.PredavacDao;
import com.diplomski.katedra.db.model.Predavac;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("PredavacDao")
public class PredavacDaoImpl extends HibernateDao<Predavac, Integer> implements PredavacDao{
    @Override
    public Predavac getByUserPass(String username, String password) {
        Query query = currentSession().createQuery("from Predavac P where P.username = '"+username+"' and P.password = '"+password+"'");
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (Predavac) result.get(0);
    }
}
