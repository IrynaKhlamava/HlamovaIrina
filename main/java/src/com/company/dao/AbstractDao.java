package com.company.dao;

import com.company.api.dao.GenericDao;
import com.company.model.AEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {

    private final List<T> repository = new ArrayList<>();

    @Override
    public void save(T entity) {
        repository.add(entity);
    }

    @Override
    public void delete(T entity) {
        repository.remove(entity);
    }

    @Override
    public T getById(Long id) {
    /*    for(T entity: repository){
          if(id.equals(entity.getId())){
              return entity;
          }
        }
        return null;
    }*/
        return repository.stream()
                .filter(x -> id.equals(x.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(repository);
    }

    @Override
    public T update(T entity) {
        /*T entity = getById(entity.getId());
        entity.setName(entity.getName());
        guest.setDaysOfStay(entity.getDaysOfStay());
        return guest;*/
        return null;
    }

    @Override
    public int getTotalNumber() {
        return repository.size();
    }


}
