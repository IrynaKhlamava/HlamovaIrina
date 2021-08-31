package com.company.dao;

import com.company.api.dao.GenericDao;

import com.company.dao.util.Connector;
import com.company.dao.util.EntityMapper;
import com.company.exceptions.DaoException;

import com.company.model.AEntity;
import com.company.model.TableEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {

    private static final String GET_BY_ID_ERROR_MESSAGE = "could not find an entity by id: %s";
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    @Deprecated
    private List<T> repository = new ArrayList<>();

    private final Connector connector = Connector.getInstance();

    @Override
    public T save(T entity) {
        Connection connection = connector.getConnection();
        String sql = getInsertQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreate(statement, entity);
            int affected = statement.executeUpdate();
            if (affected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                entity.setId(resultSet.getLong(1));
            } else {
                throw new DaoException("Creation failed");
            }
            return entity;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("Creation failed"), e);
            throw new DaoException(String.format("Creation failed"), e);
        }
    }

    @Override
    public T update(T entity) {
        Connection connection = connector.getConnection();
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatement(statement, entity);
            statement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("UPDATE failed"), e);
            throw new DaoException(String.format("UPDATE failed"), e);
        }
    }

    @Override
    public void delete(T entity) {
        Connection connection = connector.getConnection();
        Long id = entity.getId();
        String sql = String.format("DELETE FROM %s WHERE id=" + id, getTableName());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("DELETE by id failed", id));
            throw new DaoException(String.format("DELETE by id failed", id));
        }
    }

    @Override
    public T getById(Long id) {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT * FROM %s WHERE id=?", getTableName());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return (T) EntityMapper.parseResultSet(resultSet, getTableName());
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format(GET_BY_ID_ERROR_MESSAGE, id));
            throw new DaoException(String.format(GET_BY_ID_ERROR_MESSAGE, id));
        }
    }

    @Override
    public List<T> getAll() {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT * FROM %s", getTableName());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> repository = new ArrayList<>();
            while (resultSet.next()) {
                repository.add((T) EntityMapper.parseResultSet(resultSet, getTableName()));
            }
            return repository;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("getAll failed"));
            throw new DaoException(String.format("getAll failed"));
        }
    }

    @Override
    public int getTotalNumber() {
        return getAll().size();
    }

    @Override
    public List<T> getAllSorted(String col) {
        Connection connection = connector.getConnection();
        String sql = String.format("SELECT * FROM %s ORDER BY %s", getTableName(), col);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> repository = new ArrayList<>();
            while (resultSet.next()) {
                repository.add((T) EntityMapper.parseResultSet(resultSet, getTableName()));
            }
            return repository;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, String.format("getAllSorted failed"));
            throw new DaoException(String.format("getAllSorted failed"));
        }

    }

    @Deprecated
    public void saveAll(List<T> entity) {
        if (!entity.isEmpty()) {
            repository.addAll(entity);
        }
    }

    protected abstract String getInsertQuery();

    protected abstract String getUpdateQuery();

    protected abstract void prepareStatement(PreparedStatement statement, T entity) throws SQLException;

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T entity) throws SQLException;

    protected abstract TableEnum getTableName();

    public void prepareStatementById(PreparedStatement statement, Long id) throws SQLException {
        statement.setLong(1, id);
    }

}
