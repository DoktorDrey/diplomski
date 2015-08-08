package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.PredmetDao;
import com.diplomski.katedra.db.model.Predmet;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("PredmetDao")
public class PredmetDaoImpl extends HibernateDao<Predmet, Integer> implements PredmetDao {
}
