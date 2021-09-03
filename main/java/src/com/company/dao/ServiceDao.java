package com.company.dao;

import com.company.api.dao.IServiceDao;

import com.company.dao.util.Connector;
import com.company.exceptions.DaoException;
import com.company.injection.annotation.Component;
import com.company.model.Service;
import com.company.model.TableEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ServiceDao extends AbstractDao<Service> implements IServiceDao {

    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());
    private static final String INSERT_QUERY = "INSERT INTO SERVICES (name, price, guests_id) VALUES (?,?,?);";
    private static final TableEnum TABLE_NAME = TableEnum.SERVICES;
    private static final String UPDATE_QUERY = "UPDATE SERVICES SET name= ?, price = ?, guest_id = ? WHERE id = ?;";
    private static final String GET_BILL = "SELECT sum(price) from services where guest_id = ?";

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
    protected void prepareStatement(PreparedStatement statement, Service entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setDouble(2, entity.getPrice());
        statement.setLong(3, entity.getGuestId());
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Service entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setDouble(2, entity.getPrice());
        statement.setLong(3, entity.getGuestId());
    }

    @Override
    protected TableEnum getTableName() {
        return TABLE_NAME;
    }

    public List<Service> getGuestServices(Long id) {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT * FROM SERVICES WHERE guest_id=" + id);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            List<Service> services = new ArrayList<>();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getLong("id"));
                service.setName(result.getString("name"));
                service.setPrice(result.getDouble("price"));
                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("Get guest services failed"), e);
            throw new DaoException(String.format("Get guest services failed"));
        }
    }

    public List<Service> getAllGuestServices(Long id) {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT * FROM SERVICES WHERE guest_id=%s ORDER BY price", id);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            List<Service> services = new ArrayList<>();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getLong("id"));
                service.setName(result.getString("name"));
                service.setPrice(result.getDouble("price"));
                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("Get all guest services failed"), e);
            throw new DaoException(String.format("Get all guest services failed"));
        }
    }

    public double getBillForGuestForServices(Long id) {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT sum(price) FROM SERVICES WHERE guest_id=" + id);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getDouble("sum(price)");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("Get guest services failed"), e);
            throw new DaoException(String.format("Get guest services failed"));
        }
    }

}
