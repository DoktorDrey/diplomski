package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.ProgramOcene;

/**
 * Created by Andrija Ilic on 9/3/2015.
 */
public interface ProgramOceneDao extends GenericDao<ProgramOcene, Integer> {
    void addOrUpdate(ProgramOcene programOcene);
}
