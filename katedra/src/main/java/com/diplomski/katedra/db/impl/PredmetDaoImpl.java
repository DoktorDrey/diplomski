package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.PredmetDao;
import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("PredmetDao")
public class PredmetDaoImpl extends HibernateDao<Predmet, Integer> implements PredmetDao {
    private static final Logger logger = Logger.getLogger(PredmetDaoImpl.class);

    @Override
    public List<Predmet> vratiPredmeteZaPredavaca(Predavac predavac) {
//        Query query = currentSession().createQuery("from Predmet P where P.id in (SELECT predmet from predmet_predavac where predavac="+predavac.getId()+")");
        Query query = currentSession().createQuery("from Predmet P where P.id in (SELECT PP.predmet from PredmetPredavac PP where PP.predavac="+predavac.getId()+")");
        logger.debug(query.getQueryString());
        List result = query.list();
        return result;
    }
}
