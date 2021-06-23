package com.company.api.service;

import com.company.model.AEntity;

import java.util.List;

public interface GenericService<T extends AEntity> {

    void save(T entity);

    void delete(T entity);

    List<T> getAll();

}
