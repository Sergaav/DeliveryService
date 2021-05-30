package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.exception.ValidationException;
import com.savaz.delivery.model.Fields;
import com.savaz.delivery.model.dao.DestinationDao;
import com.savaz.delivery.model.dao.EntityMapper;
import com.savaz.delivery.model.entity.bean.DestinationsBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDestinationDao implements DestinationDao {
    Connection connection;

    private static final String SQL_FIND_RATE_BY_ID = "SELECT rate FROM departure_has_arrive WHERE arrive_id=? AND departure_id=?";


    private static final String SQL_FIND_ALL_DESTINATIONS = "SELECT city_arrive,city_departure FROM arrive LEFT JOIN" +
            " departure_has_arrive ON arrive.id=arrive_id JOIN departure ON departure.id=departure_id";

    private static final String SQL_FIND_ALL_DESTINATIONS_BY_PAGE = "SELECT city_arrive,city_departure FROM arrive LEFT JOIN" +
            " departure_has_arrive ON arrive.id=arrive_id JOIN departure ON departure.id=departure_id ORDER BY city_arrive LIMIT ? " +
            "offset ?";

    public JDBCDestinationDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(DestinationsBean entity) {

    }

    @Override
    public DestinationsBean findById(int id) {
        return null;
    }

    @Override
    public double findRateById(int cityArrive, int cityDeparture) {
        ResultSet resultSet = null;
        double rate=1;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_RATE_BY_ID)) {
            statement.setInt(1, cityArrive);
            statement.setInt(2, cityDeparture);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rate = resultSet.getDouble("rate");
            }
        } catch (SQLException throwables) {
            throw new ValidationException("Error SQL query");
        }finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return rate;
    }

    @Override
    public List<DestinationsBean> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DestinationsBean> destinationsBeans = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_DESTINATIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                destinationsBeans.add(new DestinationsMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return destinationsBeans;
    }

    @Override
    public void update(DestinationsBean entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    public static void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<DestinationsBean> findAllByPage(int page, int limit) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DestinationsBean> destinationsBeans = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_DESTINATIONS_BY_PAGE);
            statement.setInt(1, limit);
            statement.setInt(2, limit * (page - 1));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                destinationsBeans.add(new DestinationsMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return destinationsBeans;
    }

    private static class DestinationsMapper implements EntityMapper<DestinationsBean> {

        @Override
        public DestinationsBean mapRow(ResultSet rs) {
            try {
                DestinationsBean bean = new DestinationsBean();
                bean.setCityArrive(rs.getString(Fields.CITY_ARRIVE));
                bean.setCityDeparture(rs.getString(Fields.CITY_DEPARTURE));
                return bean;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
