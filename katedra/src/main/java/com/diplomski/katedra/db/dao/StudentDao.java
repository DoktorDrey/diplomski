package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Student;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface StudentDao extends GenericDao<Student, String>{
    public Student getByUserPass(String username, String password);
    public Student getByBrIndeks(String brIndeks);
}
