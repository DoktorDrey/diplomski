package com.diplomski.katedra.db.dao;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface GenericDao<E, K> {

    void dodaj(E entity);

    void sacuvaj(E entity);

    void obrisi(E entity);

    E promeni(K key);

    List<E> izlistaj();
}
