package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Program;
import com.diplomski.katedra.db.model.StudentAktivnostAss;
import com.diplomski.katedra.db.model.StudentAktivnostAssPK;

import java.util.List;

/**
 * Created by andrija on 8/6/15.
 */
public interface StudentAktivnostDao extends GenericDao<StudentAktivnostAss, StudentAktivnostAssPK> {
    List<StudentAktivnostAss> findForProgram(Program program);
}
