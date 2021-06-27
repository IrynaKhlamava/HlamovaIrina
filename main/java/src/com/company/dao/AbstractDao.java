package com.company.dao;

import com.company.api.dao.GenericDao;
import com.company.exceptions.DaoException;
import com.company.model.AEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {

    private static final String GET_BY_DATA_ERROR_MESSAGE = "could not find an entity by data: %d";
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());


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
        for (T entity : repository) {
            if (id.equals(entity.getId())) {
                return entity;
            }
        }
        LOGGER.log(Level.WARNING, String.format(GET_BY_DATA_ERROR_MESSAGE, id));
        throw new DaoException(String.format(GET_BY_DATA_ERROR_MESSAGE, id));
    }
//        return repository.stream()
//                .filter(x -> id.equals(x.getId()))
//                .findFirst()
//                .orElseThrow();
//    }

    @Override
    public List<T> getAll() {
            if ((new ArrayList<>(repository)).size() > 0) {
                return new ArrayList<>(repository);
            }
        else {
            LOGGER.log(Level.INFO, String.format("repository is empty "));
            throw new DaoException(String.format("repository is empty"));
        }
    }


    @Override
    public int getTotalNumber() {
        return repository.size();
    }

    @Override
    public List<T> getAllSorted(Comparator<T> comparator) {
        try {
            return getAll()
                    .stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (DaoException e){
            LOGGER.log(Level.WARNING, String.format("get All Sorted failed"));
            throw new DaoException(String.format("get All Sorted failed"));
        }
    }

    @Override
    public List<T> getFilteredListSorted(List<T> filteredList, Comparator<T> comparator) {
        return filteredList
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }


}
