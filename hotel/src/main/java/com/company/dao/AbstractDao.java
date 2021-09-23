package com.company.dao;

import com.company.api.dao.GenericDao;
import com.company.dao.util.EntityManagerUtil;
import com.company.exceptions.DaoException;
import com.company.model.AEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    private static final EntityManagerUtil entityManagerUtil = new EntityManagerUtil();

    protected EntityManager entityManager = entityManagerUtil.getEntityManager();


    public void save(T entity) {
        entityManagerUtil.beginTransaction();
        entityManager.persist(entity);
        entityManagerUtil.commit();
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
        entityManagerUtil.beginTransaction();
        entityManager.merge(entity);
        entityManagerUtil.commit();
    }

    @Override
    public void delete(T entity) {
        try {
            entityManagerUtil.beginTransaction();
            entityManager.remove(entity);
            entityManagerUtil.commit();
        } catch (Exception e) {
            LOGGER.warn("DELETE by id failed ", e);
            throw new DaoException(String.format("DELETE by id failed"));
        }
    }

    @Override
    public int getTotalNumber() {
        return getAll().size();
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
