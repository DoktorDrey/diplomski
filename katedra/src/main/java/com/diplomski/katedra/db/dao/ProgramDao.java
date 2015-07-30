package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Program;

/**
 * Created by andrija on 7/30/15.
 */
public interface ProgramDao extends GenericDao<Program, Integer>{

    public Program findProgram(int predmet, int year);

}
