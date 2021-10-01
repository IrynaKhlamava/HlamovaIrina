package com.company.dao;

import com.company.api.dao.GenericDao;
import com.company.exceptions.DaoException;
import com.company.model.AEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;


    public void save(T entity) {
        entityManager.persist(entity);
    }

    public T getById(Long id) {
        return entityManager.find(getClazz(), id);
    }

    public List<T> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root<T> root = query.from(getClazz());
        return entityManager.createQuery(query).getResultList();
    }

    protected abstract Class<T> getClazz();

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            LOGGER.warn("DELETE by id failed ", e);
            throw new DaoException(String.format("DELETE by id failed"));
        }
    }

    @Override
    public Integer getTotalNumber() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
            Root<T> root = query.from(getClazz());
            builder.count(root.get("id"));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            LOGGER.warn("getTotalNumber failed");
            throw new DaoException(String.format("getTotalNumber failed"));
        }
    }

    @Override
    public List<T> getAllSorted(String col) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(getClazz());
            Root<T> root = query.from(getClazz());
            query.orderBy(builder.asc(root.get(col)));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            LOGGER.warn("getAllSorted failed");
            throw new DaoException(String.format("getAllSorted failed"));
        }
    }
}
