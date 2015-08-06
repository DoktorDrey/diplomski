package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.StudentPredmetAssDao;
import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.StudentPredmetAss;
import com.diplomski.katedra.db.model.StudentPredmetAssPK;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andrija on 8/6/15.
 */
@Repository("StudentPredmetAssDao")
public class StudentPredmetAssDaoImpl extends HibernateDao<StudentPredmetAss,StudentPredmetAssPK> implements StudentPredmetAssDao {
    @Override
    public List<StudentPredmetAss> getStudentsByProgram(Program program) {
//        String query = "SELECT * from student_predmet_ass where program_id="+program.getId();
        Query query = currentSession().createQuery("from StudentPredmetAss S where S.programId = "+program.getId());
        List result = query.list();
        return result;
    }
}
