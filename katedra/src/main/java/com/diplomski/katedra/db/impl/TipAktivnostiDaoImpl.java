package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.TipAktivnostiDao;
import com.diplomski.katedra.db.model.TipAktivnosti;
import org.springframework.stereotype.Repository;

/**
 * Created by andrija on 8/24/15.
 */
@Repository("TipAktivnostDao")
public class TipAktivnostiDaoImpl extends HibernateDao<TipAktivnosti, Integer> implements TipAktivnostiDao {
}
