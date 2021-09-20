package com.company.dao;

import com.company.exceptions.DaoException;
import com.company.injection.annotation.Component;
import com.company.model.Guest;
import com.company.api.dao.IGuestDao;

import com.company.model.Service;
import org.apache.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());

    public GuestDao() {
    }

    @Override
    protected Class<Guest> getClazz() {
        return Guest.class;
    }

    public int getCountGuestsInRoomById(Long id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<Guest> root = query.from(Guest.class);
            query.select(builder.count(root)).where(builder.equal(root.get("roomId"), id));
            return Math.toIntExact(entityManager.createQuery(query).getSingleResult());
        } catch (Exception e) {
            LOGGER.warn("Get count guests in room failed", e);
            throw new DaoException(String.format("Get count guests in room failed"));
        }
    }

    @Override
    public List<Guest> getLastGuestsOfRoom(Long roomId, Integer lastGuestNum) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guest> query = builder.createQuery(Guest.class);
            Root<Guest> root = query.from(Guest.class);
            query.select(root).where(builder.equal(root.get("roomId"), roomId));
            query.orderBy(builder.desc(root.get("dateCheckOut")));
            List<Guest> list = entityManager.createQuery(query).getResultList();
            List<Guest> lastGuest = new ArrayList<>();
            int count = 0;
            for (int i = 0; i <= list.size() - 1 && count < lastGuestNum; i++) {
                for (Guest guest : list)
                    lastGuest.add(guest);
                count++;
            }
            return lastGuest;
        } catch (Exception e) {
            LOGGER.warn("Get count guests in room failed", e);
            throw new DaoException(String.format("Get count guests in room failed"));
        }
    }

    @Override
    public void addService(Service service, Guest guest) {
        Set<Service> setGuestService = guest.getServices();
        setGuestService.add(service);
        save(guest);
    }
}
