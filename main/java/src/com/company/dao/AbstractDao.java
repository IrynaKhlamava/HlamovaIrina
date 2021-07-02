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

    private static final String GET_BY_DATA_ERROR_MESSAGE = "could not find an entity by data: %s";
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
        LOGGER.log(Level.INFO, String.format("Get entity by id: %s", id));
        return repository.stream()
                .filter(x -> id.equals(x.getId()))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, String.format(GET_BY_DATA_ERROR_MESSAGE, id));
                    throw new DaoException(String.format(GET_BY_DATA_ERROR_MESSAGE, id));
                });
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
}
