package com.company.dao;

import com.company.api.dao.IRoomDao;

import com.company.dao.util.EntityManagerUtil;
import com.company.exceptions.DaoException;
import com.company.injection.annotation.Component;
import com.company.model.*;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;


@Component
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

    // private static final EntityManagerUtil emu = new EntityManagerUtil();

    // EntityManager entityManager = emu.getEntityManager();

    public RoomDao() {
    }

    public RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Class<Room> getClazz() {
        return Room.class;
    }

    public Room getRoomByNumber(Integer roomNum) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = builder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            query.select(root).where(builder.equal(root.get("number"), roomNum));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            LOGGER.warn("Get room by number failed");
            throw new DaoException(String.format("Get room by number failed"));
        }
    }

    @Override
    public void changeRoomStatus(Integer roomNum, RoomStatus newStatus) {
        try {
            Room room = getRoomByNumber(roomNum);
            room.setRoomStatus(newStatus);
            update(room);
        } catch (Exception e) {
            LOGGER.warn("Change room status failed", e);
            throw new DaoException(String.format("Change room status failed"), e);
        }
    }

    @Override
    public void changeRoomPrice(Integer roomNum, Double newPrice) {
        try {
            Room room = getRoomByNumber(roomNum);
            room.setPriceRoom(newPrice);
            update(room);
        } catch (Exception e) {
            LOGGER.warn("Change room price failed", e);
            throw new DaoException(String.format("Change room price failed"), e);
        }
    }

    public List<Room> getFreeRooms() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = builder.createQuery(Room.class);
            Root<Room> rmRoot = query.from(Room.class);
            Root<Guest> gstRoot = query.from(Guest.class);
            query.select(rmRoot).where(builder.equal(gstRoot.get("roomId"), rmRoot.get("id")));
            query.groupBy(rmRoot.get("id"));
            query.having(builder.greaterThan(rmRoot.get("capacity"), builder.count(gstRoot.get("roomId"))));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            LOGGER.warn("get all free rooms failed");
            throw new DaoException(String.format("get all free rooms failed"));
        }
    }

    public List<Room> getFreeRoomsSort(String col) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = builder.createQuery(Room.class);
            Root<Room> rmRoot = query.from(Room.class);
            Root<Guest> gstRoot = query.from(Guest.class);
            query.multiselect(rmRoot, gstRoot);
            query.groupBy(rmRoot.get("id"));
            query.having(builder.greaterThan(rmRoot.get("capacity"), builder.count(gstRoot.get("roomId"))));
            query.orderBy(builder.desc(rmRoot.get(col)));
            return entityManager.createQuery(query).getResultList();

        } catch (Exception e) {
            LOGGER.warn("sort free rooms failed");
            throw new DaoException(String.format("sort free rooms failed"));
        }
    }

    public List<Room> getFreeRoomsByDate(String byDate) {
        try {
            LocalDate date = LocalDate.parse(byDate);
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = builder.createQuery(Room.class);
            Root<Room> rmRoot = query.from(Room.class);
            Root<Guest> gstRoot = query.from(Guest.class);
            query.select(rmRoot).where(builder.lessThan(gstRoot.<LocalDate>get("dateCheckOut"), date)).distinct(true);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            LOGGER.warn("get free rooms by date failed");
            throw new DaoException(String.format("get free rooms by date failed"));
        }
    }

    public double getRoomPrice(Long id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Double> query = builder.createQuery(Double.class);
            Root<Room> root = query.from(Room.class);
            query.select(root.get("priceRoom")).where(builder.equal(root.get("id"), id));
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            LOGGER.warn("get room price failed");
            throw new DaoException(String.format("get room price failed"));
        }
    }
}

