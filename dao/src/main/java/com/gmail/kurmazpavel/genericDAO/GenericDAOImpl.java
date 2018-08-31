package com.gmail.kurmazpavel.genericDAO;


import com.gmail.kurmazpavel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class GenericDAOImpl<T extends  Serializable> implements GenericDAO<T> {
    private Class<T> aClass;
    private SessionFactory sessionFactory;

    GenericDAOImpl(Class<T> aClass){
        this.aClass = aClass;
        sessionFactory = HibernateUtil.getSessionFactory(aClass);
    }

    @Override
    public T read(long entityID) {
        return getCurrentSession().get(aClass, entityID);
    }

    @Override
    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + aClass.getSimpleName()).list();
    }

    @Override
    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
