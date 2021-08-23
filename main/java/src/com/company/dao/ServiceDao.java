package com.company.dao;

import com.company.api.dao.IServiceDao;

import com.company.injection.annotation.Component;
import com.company.model.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ServiceDao extends AbstractDao<Service> implements IServiceDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());
    private static final String INSERT_QUERY = "INSERT INTO SERVICES (name, price, guests_id) VALUES (?,?,?);";
    private static final String TABLE_NAME = "SERVICES";
    private static final String UPDATE_QUERY = "UPDATE SERVICES SET name= ?, price = ?, guest_id = ? WHERE id = ?;";

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, Service entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setDouble(2, entity.getPrice());
        statement.setLong(3, entity.getGuestId());
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
