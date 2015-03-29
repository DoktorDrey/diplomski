package com.diplomski.katedra.services;

import com.diplomski.katedra.db.dao.StudentDao;
import com.diplomski.katedra.db.model.Student;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public class TestServiceImpl implements TestService {
    @Inject
    private StudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentDao.list();
        return students;
    }
}
