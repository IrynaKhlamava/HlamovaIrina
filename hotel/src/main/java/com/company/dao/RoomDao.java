package com.company.dao;

import com.company.api.dao.IRoomDao;

import com.company.exceptions.DaoException;
import com.company.injection.annotation.Component;
import com.company.model.*;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());

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

    public List<Room> getFreeRoomsSort(RoomFilter filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = builder.createQuery(Room.class);
            Root<Room> rmRoot = query.from(Room.class);
            Root<Guest> gstRoot = query.from(Guest.class);
            List<Room> roomList = new ArrayList<>();
            query.select(rmRoot).where(builder.equal(gstRoot.get("roomId"), rmRoot.get("id")));
            query.groupBy(rmRoot.get("id"));
            if (filter.getDate() == null && filter.getSortField() == null) {
                query.having(builder.greaterThan(rmRoot.get("capacity"),
                        builder.count(gstRoot.get("roomId"))));
                roomList = entityManager.createQuery(query).getResultList();
            }
            if (filter.getDate() != null) {
                query.select(rmRoot).where(builder.lessThan(gstRoot.<LocalDate>get("dateCheckOut"), filter.getDate())).distinct(true);
                roomList = entityManager.createQuery(query).getResultList();
            }
            if (filter.getSortField() != null) {
                query.having(builder.greaterThan(rmRoot.get("capacity"), builder.count(gstRoot.get("roomId"))));
                query.orderBy(builder.asc(rmRoot.get(filter.getSortField())));
                roomList = entityManager.createQuery(query).getResultList();
            }
            return roomList;
        } catch (Exception e) {
            LOGGER.warn("sort free rooms failed");
            throw new DaoException(String.format("sort free rooms failed"));
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

    @Override
    public Integer getNumOfAvailableRooms() {
        return getFreeRoomsSort(new RoomFilter()).size();
    }

}

