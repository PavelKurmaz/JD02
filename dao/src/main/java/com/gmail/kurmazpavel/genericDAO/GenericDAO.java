package com.gmail.kurmazpavel.genericDAO;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T extends Serializable> {
    T read(final long entityID);
    List<T> getAll();
    void create(final T entity);
    void save (final T entity);
    void update (final T entity);
    void delete (final T entity);
    Session getCurrentSession();
}
