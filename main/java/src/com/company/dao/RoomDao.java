package com.company.dao;

import com.company.api.dao.IRoomDao;

import com.company.dao.util.Connector;
import com.company.dao.util.EntityMapper;
import com.company.exceptions.DaoException;
import com.company.injection.annotation.Component;
import com.company.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());
    private static final String INSERT_QUERY = "INSERT INTO ROOMS (number, capacity, status, comfort, price) VALUES (?,?,?,?,?);";
    private static final String UPDATE_QUERY = "UPDATE ROOMS SET number= ?, capacity = ?, status = ?, comfort = ?, price = ? WHERE id=?;";
    private static final String UPDATE_STATUS = "UPDATE ROOMS SET status = ? where number = ? ";
    private static final String UPDATE_PRICE = "UPDATE ROOMS SET price = ? where number= ? ";
    private static final String GET_FREE_ROOMS = "SELECT r.id, number, capacity, status, comfort, price FROM ROOMS r join GUESTS g on r.id = g.room_id " +
                                                      "group by r.id having count(g.room_id)< r.capacity;";
    private static final String SORT_FREE_ROOMS = "SELECT r.id, number, capacity, status, comfort, price FROM ROOMS r join GUESTS g on r.id = g.room_id" +
                                                      "group by r.id having count(g.room_id)< r.capacity ORDER BY ?;";
    private static final String GET_FREE_ROOMS_BY_DATE = "SELECT r.id, number, capacity, status, comfort, price FROM ROOMS r join GUESTS g " +
                                                           "on r.id = g.room_id where g.date_check_out <= ? group by r.id;";
    private static final String GET_ROOM_PRICE = "SELECT price FROM rooms WHERE id = ?;";
    private static final TableEnum TABLE_NAME = TableEnum.ROOMS;

    private final Connector connector = Connector.getInstance();

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, Room entity) throws SQLException {
        statement.setInt(1, entity.getNumber());
        statement.setInt(2, entity.getCapacity());
        statement.setInt(3, entity.getRoomStatus().ordinal());
        statement.setInt(4, entity.getComfort().getValue());
        statement.setDouble(5, entity.getPriceRoom());
    }


    protected void prepareStatementForUpdateStatus(PreparedStatement statement, Integer roomNum, RoomStatus roomStatus) throws SQLException {
        statement.setInt(1, roomStatus.ordinal());
        statement.setInt(2, roomNum);
    }

    protected void prepareStatementForUpdatePrice(PreparedStatement statement, Integer roomNum, Double newPrice) throws SQLException {
        statement.setDouble(1, newPrice);
        statement.setInt(2, roomNum);
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Room entity) throws SQLException {
        statement.setInt(1, entity.getNumber());
        statement.setInt(2, entity.getCapacity());
        statement.setInt(3, entity.getRoomStatus().ordinal());
        statement.setInt(4, entity.getComfort().getValue());
        statement.setDouble(5, entity.getPriceRoom());
    }

    @Override
    protected TableEnum getTableName() {
        return TABLE_NAME;
    }

    public Room getRoomByNumber(Integer roomNum) {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT * FROM ROOMS WHERE number=?");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, roomNum);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return EntityMapper.createRooms(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("Get room by number failed"));
            throw new DaoException(String.format("Get room by number failed"));
        }
    }

    @Override
    public void changeRoomStatus(Integer roomNum, RoomStatus newStatus) {
        Connection connection = connector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            prepareStatementForUpdateStatus(statement, roomNum, newStatus);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("Change room status failed"), e);
            throw new DaoException(String.format("Change room status failed"), e);
        }
    }

    @Override
    public void changeRoomPrice(Integer roomNum, Double newPrice) {
        Connection connection = connector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRICE)) {
            prepareStatementForUpdatePrice(statement, roomNum, newPrice);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("Change room price failed"), e);
            throw new DaoException(String.format("Change room price failed"), e);
        }
    }

    public List<Room> getFreeRooms() {
        Connection connection = connector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_FREE_ROOMS)) {
            ResultSet resultSet = statement.executeQuery();
            List<Room> repository = new ArrayList<>();
            while (resultSet.next()) {
                repository.add(EntityMapper.createRooms(resultSet));
            }
            return repository;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("get all free rooms failed"));
            throw new DaoException(String.format("get all free rooms failed"));
        }
    }

    public List<Room> getFreeRoomsSort(String col) {
        Connection connection = connector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SORT_FREE_ROOMS)) {
            prepareStatementForSort(statement, col);
            ResultSet resultSet = statement.executeQuery();
            List<Room> repository = new ArrayList<>();
            while (resultSet.next()) {
                repository.add(EntityMapper.createRooms(resultSet));
            }
            return repository;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("sort free rooms failed"));
            throw new DaoException(String.format("sort free rooms failed"));
        }
    }

    protected void prepareStatementForSort(PreparedStatement statement, String col) throws SQLException {
        statement.setString(1, col);
    }

    public List<Room> getFreeRoomsByDate(String byDate) {
        Connection connection = connector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_FREE_ROOMS_BY_DATE)) {
            prepareStatementForByDate(statement, byDate);
            ResultSet resultSet = statement.executeQuery();
            List<Room> repository = new ArrayList<>();
            while (resultSet.next()) {
                repository.add(EntityMapper.createRooms(resultSet));
            }
            return repository;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("get free rooms by date failed"));
            throw new DaoException(String.format("get free rooms by date failed"));
        }
    }

    private void prepareStatementForByDate(PreparedStatement statement, String byDate) throws SQLException {
        statement.setString(1, byDate);
    }

    public double getRoomPrice(Long id) {
        Connection connection = connector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ROOM_PRICE)) {
            prepareStatementForById(statement, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("price");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("get room price failed"));
            throw new DaoException(String.format("get room price failed"));
        }
    }

    private void prepareStatementForById(PreparedStatement statement, Long id) throws SQLException {
        statement.setLong(1, id);
    }
}

