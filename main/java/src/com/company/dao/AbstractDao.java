package com.company.dao;

import com.company.api.dao.GenericDao;
import com.company.model.AEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public int getTotalNumber() {
        return repository.size();
    }

    @Override
    public List<T> getAllSorted(Comparator<T> comparator) {
        return getAll()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<T> getFilteredListSorted(List<T> filteredList, Comparator<T> comparator) {
        return filteredList
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }


}
