package com.company.dao;

import com.company.api.dao.IRoomDao;

import com.company.exceptions.DaoException;
import com.company.model.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
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

    public List<Room> getAllAvailableRooms(RoomFilter filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = builder.createQuery(Room.class);
            Root<Room> rmRoot = query.from(Room.class);
            Root<Guest> gstRoot = query.from(Guest.class);
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> predicatesHaving = new ArrayList<>();
            List<Order> predicatesOrder = new ArrayList<>();
            Predicate predicateId = builder.equal(gstRoot.get("roomId"), rmRoot.get("id"));
            Predicate predicateAvailableRooms = builder.or(builder.greaterThan(rmRoot.get("capacity"),
                    builder.count(gstRoot.get("roomId"))), builder.equal(builder.count(gstRoot.get("roomId")), 0));
            Predicate predicateByDate = builder.lessThan(gstRoot.<LocalDate>get("dateCheckOut"), filter.getDate());
            Path predicateGroupBy = null;
            Order predicateSortField;
            predicates.add(predicateId);
            if (filter.getDate() == null && (filter.getSortField() == null || filter.getSortField().equals(""))) {
                predicatesHaving.add(predicateAvailableRooms);
                predicateGroupBy = rmRoot.get("id");
            }
            if (filter.getDate() != null) {
                predicates.add(predicateByDate);
                predicateGroupBy = rmRoot.get("id");
            }
            if (filter.getSortField() != null && !filter.getSortField().equals("")) {
                predicateGroupBy = rmRoot.get("id");
                predicatesHaving.add(predicateAvailableRooms);
                predicateSortField = builder.asc(rmRoot.get(filter.getSortField()));
                predicatesOrder.add(predicateSortField);
            }
            query.select(rmRoot).where(predicates.toArray(new Predicate[0])).groupBy(predicateGroupBy).having(predicatesHaving.toArray(new Predicate[0])).orderBy(predicatesOrder).distinct(true);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            LOGGER.warn("sort Available rooms failed");
            throw new DaoException(String.format("sort Available rooms failed"));
        }
    }

    public Double getRoomPrice(Long id) {
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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = builder.createQuery(Room.class);
        Root<Room> rmRoot = query.from(Room.class);
        Root<Guest> gstRoot = query.from(Guest.class);
        Predicate predicateAvailableRooms = builder.or(builder.greaterThan(rmRoot.get("capacity"),
                builder.count(gstRoot.get("roomId"))), builder.equal(builder.count(gstRoot.get("roomId")), 0));
        query.select(rmRoot).where(builder.equal(gstRoot.get("roomId"), rmRoot.get("id")));
        query.groupBy(rmRoot.get("id"));
        query.having(predicateAvailableRooms);
        return entityManager.createQuery(query).getResultList().size();
    }
}

