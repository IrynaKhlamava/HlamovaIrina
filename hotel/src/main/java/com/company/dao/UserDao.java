package com.company.dao;

import com.company.api.dao.IUserDao;
import com.company.exceptions.DaoException;
import com.company.model.Role;
import com.company.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;

import java.util.Set;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());

    public UserDao() {
    }

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }

    @Override
    public Set<Role> getRolesById(Long id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> query = builder.createQuery(Role.class);
            Root<Role> root = query.from(Role.class);
            query.select(root).where(builder.equal(root.get("users_id"), id));
           return new HashSet<>(entityManager.createQuery(query).getResultList());
        } catch (Exception e) {
            LOGGER.warn("Get roles failed");
            throw new DaoException(String.format("Get roles failed"));
        }

    }

    @Override
    public User findByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("username"), username));
        return entityManager.createQuery(query).getResultStream().findFirst().orElse(null);

    }
}
