package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Student;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("StudentDao")
public class StudentDaoImpl extends HibernateDao<Student, String> implements StudentDao{
}
