package com.company.service;

import java.util.List;

public interface AbstractService <K, T>{

    T create(T entity);

    T findById(K id);

    List<T> findAll();

    K countAll();

    T update(T entity);

    void delete(K id);

}
