package com.company.dao;

import com.company.api.dao.IRoomDao;

import com.company.dao.util.Connector;
import com.company.injection.annotation.Component;
import com.company.model.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RoomDao extends AbstractDao<Room> implements IRoomDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());
    private static final String INSERT_QUERY = "INSERT INTO ROOMS (number, capacity, status, comfort, price) VALUES (?,?,?,?,?);";
    private static final String UPDATE_QUERY = "UPDATE ROOMS SET number= ?, capacity = ?, status = ?, comfort = ?, price = ? WHERE id=?;";
    private static final String TABLE_NAME = "ROOMS";

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

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
