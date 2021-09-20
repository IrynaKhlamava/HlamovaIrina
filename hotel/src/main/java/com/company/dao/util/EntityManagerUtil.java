package com.company.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManagerUtil() {
        init();
    }

    private void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
        this.entityManager = factory.createEntityManager();
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }

    public void rollback() {
        entityManager.getTransaction().rollback();
    }


}
