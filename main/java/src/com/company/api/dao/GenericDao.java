package com.company.api.dao;

import com.company.model.AEntity;

import java.util.List;

public interface GenericDao<T extends AEntity> {

    T save(T entity);

    void delete(T entity);

    T getById(Long Id);

    List<T> getAll();

    T update(T entity);

    int getTotalNumber();

    List<T> getAllSorted(String col);

}
