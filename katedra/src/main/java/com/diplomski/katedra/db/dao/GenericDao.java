package com.diplomski.katedra.db.dao;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public interface GenericDao<E, K> {

    void add(E entity);

    void update(E entity);

    void remove(E entity);

    E find(K key);

    List<E> list();
}
