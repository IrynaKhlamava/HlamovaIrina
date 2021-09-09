package com.company.service;

import com.company.api.dao.GenericDao;
import com.company.api.service.GenericService;
import com.company.model.AEntity;

public abstract class AbstractService<T extends AEntity, D extends GenericDao<T>> implements GenericService<T> {

}
