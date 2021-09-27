package com.company.dao;

import com.company.exceptions.DaoException;
import com.company.injection.annotation.Component;
import com.company.model.Guest;
import com.company.api.dao.IGuestDao;

import com.company.model.Room;
import com.company.model.Service;
import org.apache.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

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
    public List<Guest> getLastGuestsOfRoom(Integer roomNum, Integer lastGuestNum) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guest> query = builder.createQuery(Guest.class);
            Root<Guest> guest = query.from(Guest.class);
            Root<Room> room = query.from(Room.class);
            Join<Guest, Room> roomJoin = guest.join("room");
            roomJoin.on(builder.equal(room.get("id"),guest.get("roomId")));
            query.select(guest).where(builder.equal(room.get("number"), roomNum)).orderBy(builder.desc(guest.get(
                    "dateCheckOut")));
            return entityManager.createQuery(query).setMaxResults(lastGuestNum).getResultList();
        } catch (Exception e) {
            LOGGER.warn("Get count guests in room failed", e);
            throw new DaoException(String.format("Get count guests in room failed"));
        }
    }

    @Override
    public Set<Service> getGuestServices(Long guestId) {
        try {
            return getById(guestId).getServices();
        } catch (Exception e) {
            LOGGER.warn("Get guests services failed", e);
            throw new DaoException(String.format("Get guests services failed"));
        }
    }
}
