package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Predavac;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface PredavacDao  extends GenericDao<Predavac, Integer>{
    public Predavac getByUserPass(String username, String password);
}
