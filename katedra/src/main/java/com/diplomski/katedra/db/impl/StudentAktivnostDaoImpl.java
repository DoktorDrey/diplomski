package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentAktivnostDao;
import com.diplomski.katedra.db.model.StudentAktivnostAss;
import com.diplomski.katedra.db.model.StudentAktivnostAssPK;
import org.springframework.stereotype.Repository;

/**
 * Created by andrija on 8/6/15.
 */
@Repository("StudentAktivnostDao")
public class StudentAktivnostDaoImpl extends HibernateDao<StudentAktivnostAss,StudentAktivnostAssPK> implements StudentAktivnostDao {

}
