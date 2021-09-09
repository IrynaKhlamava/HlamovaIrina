package com.company.dao;

import com.company.dao.util.Connector;
import com.company.dao.util.EntityMapper;
import com.company.exceptions.DaoException;
import com.company.injection.annotation.Component;
import com.company.model.Guest;
import com.company.api.dao.IGuestDao;
import com.company.model.LastGuestsInfo;

import com.company.model.TableEnum;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());

    private static final String INSERT_QUERY = "INSERT INTO GUESTS (name, days_of_stay) VALUES (?,?);";
    private static final String UPDATE_QUERY = "UPDATE GUESTS SET name = ?, days_of_stay = ?, date_check_in = ?, date_check_out = ?, room_id = ? " +
            "WHERE id =?;";
    private static final TableEnum TABLE_NAME = TableEnum.GUESTS;
    private static final String LAST_GUEST = "SELECT name, date_check_in, date_check_out from guests where room_id = ? order by date_check_out desc;";

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
    protected void prepareStatementForCreate(PreparedStatement statement, Guest entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getDaysOfStay());
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, Guest entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getDaysOfStay());
        statement.setDate(3, Date.valueOf(entity.getDateCheckIn()));
        statement.setDate(4, Date.valueOf(entity.getDateCheckOut()));
        statement.setLong(5, entity.getRoomId());
        statement.setLong(6, entity.getId());
    }

    @Override
    protected TableEnum getTableName() {
        return TABLE_NAME;
    }

    public int getCountGuestsInRoomById(Long id) {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT COUNT(id) FROM GUESTS WHERE room_id=" + id);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            result.next();
            return Integer.parseInt(result.getString(1));
        } catch (SQLException e) {
            LOGGER.warn("Get count guests in room failed", e);
            throw new DaoException(String.format("Get count guests in room failed"));
        }
    }

    @Override
    public List<LastGuestsInfo> getLastGuestsOfRoom(Long roomId, Integer lastGuestNum) {
        Connection connection = connector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(LAST_GUEST)) {
            prepareStatementById(statement, roomId);
            ResultSet result = statement.executeQuery();
            List<LastGuestsInfo> repository = new ArrayList<>();
            int count = 0;
            while (result.next() && count < lastGuestNum) {
                repository.add(EntityMapper.createLastGuestsInfo(result));
            }
            return repository;
        } catch (SQLException e) {
            LOGGER.warn("Get count guests in room failed", e);
            throw new DaoException(String.format("Get count guests in room failed"));
        }

    }
}
