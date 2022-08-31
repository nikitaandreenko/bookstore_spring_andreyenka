package com.company.service;

import java.util.List;

public interface AbstractService <K, T>{

    T create(T dto);

    T findById(K id);

    List<T> findAll();

    T update(T dto);

    void delete(K id);

}
