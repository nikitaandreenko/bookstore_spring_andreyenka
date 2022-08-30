package com.company.service;

import java.util.List;

public interface AbstractService <K, T, V>{

    T create(V dto);

    V findById(K id);

    List<V> findAll();

    K countAll();

    T update(V dto);

    void delete(K id);

}
