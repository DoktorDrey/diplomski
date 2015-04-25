package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Student;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Repository("StudentDao")
public class StudentDaoImpl extends HibernateDao<Student, String> implements StudentDao{
    @Override
    public Student getByUserPass(String username, String password) {
        Query query = currentSession().createQuery("from Student S where S.username = '"+username+"' and S.password = '"+password+"'");
        List result = query.list();
        if(result.isEmpty())
            return null;
        return (Student) result.get(0);
    }
}
