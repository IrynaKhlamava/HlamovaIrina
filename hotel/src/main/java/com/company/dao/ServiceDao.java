package com.company.dao;

import com.company.api.dao.IServiceDao;

import com.company.exceptions.DaoException;
import com.company.model.Service;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ServiceDao extends AbstractDao<Service> implements IServiceDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    public ServiceDao() {
    }

    public ServiceDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class<Service> getClazz() {
        return Service.class;
    }

    public List<Service> getAllGuestServicesSortByPrice(Long id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Service> query = builder.createQuery(Service.class);
            Root<Service> srvRoot = query.from(Service.class);
            query.select(srvRoot).where(builder.equal(srvRoot.get("guestId"), id));
            query.orderBy(builder.asc(srvRoot.get("price")));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            LOGGER.warn("Get all guest services failed", e);
            throw new DaoException(String.format("Get all guest services failed"));
        }
    }

    public double getBillForGuestForServices(Long id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Double> query = builder.createQuery(Double.class);
            Root<Service> srvRoot = query.from(Service.class);
            query.select(builder.sum(srvRoot.get("price"))).where(builder.equal(srvRoot.get("guestId"), id));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            LOGGER.warn("Get all guest services failed", e);
            throw new DaoException(String.format("Get all guest services failed"));
        }
    }

}
