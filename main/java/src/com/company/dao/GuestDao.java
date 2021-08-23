package com.company.dao;

import com.company.injection.annotation.Component;
import com.company.model.Guest;
import com.company.api.dao.IGuestDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GuestDao extends AbstractDao<Guest> implements IGuestDao {

    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());
    private static final String INSERT_QUERY = "INSERT INTO GUESTS (name, days_of_stay) VALUES (?,?);";
    private static final String UPDATE_QUERY = "UPDATE GUESTS SET name = ?, days_of_stay = ?, date_check_in = ?, date_check_out = ?, room_id = ? WHERE id =?;";
    private static final String TABLE_NAME = "GUESTS";

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
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
    protected String getTableName() {
        return TABLE_NAME;
    }
}
