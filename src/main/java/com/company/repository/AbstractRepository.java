package com.company.repository;

import java.util.List;

public interface AbstractRepository<K, T> {

    T create(T entity);

    T findById(K id);

    List<T> findAll();

    T update(T entity);

    void delete(K id);
}
