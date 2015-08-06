package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentAktivnostDao;
import com.diplomski.katedra.db.dao.StudentPredmetAssDao;
import com.diplomski.katedra.db.model.*;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andrija on 8/6/15.
 */
@Repository("StudentAktivnostDao")
public class StudentAktivnostDaoImpl extends HibernateDao<StudentAktivnostAss,StudentAktivnostAssPK> implements StudentAktivnostDao {

}
