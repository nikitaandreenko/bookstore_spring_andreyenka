package com.company.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractService <K, T>{

    T create(T dto);

    T findById(K id);

    Page<T> findAll(Pageable pageable);

    T update(T dto);

    void delete(K id);

}
