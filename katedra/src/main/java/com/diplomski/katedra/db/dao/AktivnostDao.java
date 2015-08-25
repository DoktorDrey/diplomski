package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Program;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface AktivnostDao extends GenericDao<Aktivnost, Integer> {
    List<Aktivnost> findForProgram(Program program);

    void removeActivitiesForProgram(Program program);
}
