package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.ProgramOceneDao;
import com.diplomski.katedra.db.model.ProgramOcene;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrija Ilic on 9/3/2015.
 */
@Repository("ProgramOceneDao")
public class ProgramOceneDaoImpl extends HibernateDao<ProgramOcene, Integer> implements ProgramOceneDao {
}
