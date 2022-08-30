package com.company.data.repository;

import java.util.List;

public interface AbstractRepository<K, T> {

    T create(T entity);

    T findById(K id);

    List<T> findAll();

    K countAll();

    T update(T entity);

    boolean delete(K id);
}
