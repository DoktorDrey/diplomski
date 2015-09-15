package com.diplomski.katedra.db.impl;

import com.diplomski.katedra.db.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HibernateDao<E, K extends Serializable> implements GenericDao<E, K> {

    private SessionFactory sessionFactory;
    protected Class<? extends E> daoType;

    public HibernateDao() {
        daoType = (Class<E>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void dodaj(E entity) {
        currentSession().saveOrUpdate(entity);
    }
    @Override
    public void sacuvaj(E entity) {
        currentSession().update(entity);
    }

    @Override
    public void obrisi(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public E promeni(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @Override
    public List<E> izlistaj() {
        return currentSession().createCriteria(daoType).list();
    }
}
