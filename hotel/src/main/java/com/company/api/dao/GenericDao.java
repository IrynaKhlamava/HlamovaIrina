package com.company.api.dao;

import com.company.model.AEntity;

import java.util.List;

public interface GenericDao<T extends AEntity> {

    void save(T entity);

    void delete(T entity);

    T getById(Long Id);

    List<T> getAll(String col);

    Integer getTotalNumber();

    void update(T entity);

}
