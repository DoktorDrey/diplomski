package com.diplomski.katedra.db.dao;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface PredmetDao extends GenericDao<Predmet, Integer> {

    public List<Predmet> vratiPredmeteZaPredavaca(Predavac predavac);
}
