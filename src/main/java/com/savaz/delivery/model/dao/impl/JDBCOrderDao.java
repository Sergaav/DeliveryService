package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.bean.OrderBean;

import java.sql.*;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;

    private static final String SQL_CREATE_NEW_PARCEL = "INSERT INTO parsels values (default,?,?,?,?,?,?)";
    private static final String SQL_CREATE_NEW_ORDER = "INSERT INTO orders values (default,?,?,?,?,?,?,?,?,?,?)";

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(OrderBean entity) {
        ResultSet resultSetParcel = null;
        ResultSet resultSet = null;

        try (PreparedStatement statementParcel = connection.prepareStatement(SQL_CREATE_NEW_PARCEL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementOrder = connection.prepareStatement(SQL_CREATE_NEW_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statementParcel.setString(1, entity.getParcel().getDescription());
            statementParcel.setInt(2, entity.getParcel().getLength());
            statementParcel.setInt(3, entity.getParcel().getWidth());
            statementParcel.setInt(4, entity.getParcel().getHeight());
            statementParcel.setInt(5, entity.getParcel().getWeight());
            statementParcel.setInt(6, entity.getParcel().getWeightRateId());
            statementParcel.executeUpdate();
            int parcelId = 0;
            resultSetParcel = statementParcel.getGeneratedKeys();
            while (resultSetParcel.next()) {
                parcelId = resultSetParcel.getInt(1);
            }
            entity.getParcel().setId(parcelId);
            statementOrder.setString(1, entity.getAddress());
            statementOrder.setString(2, entity.getStatus().toString());
            statementOrder.setDate(3, Date.valueOf(entity.getDateCreation()));
            statementOrder.setInt(4, entity.getParcel().getId());
            statementOrder.setInt(5, entity.getUser().getId());
            statementOrder.setInt(6, entity.getCityDepartureId());
            statementOrder.setInt(7, entity.getCityArriveId());
            statementOrder.setDate(8, Date.valueOf(entity.getDateDeparture()));
            statementOrder.setString(9, entity.getRecipientName());
            statementOrder.setLong(10, entity.getBill());
            statementOrder.executeUpdate();
            resultSet = statementParcel.getGeneratedKeys();
            while (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closeResultSet(resultSetParcel);
            closeConnection(connection);
        }

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
    public OrderBean findById(int id) {
        return null;
    }

    @Override
    public List<OrderBean> findAll() {
        return null;
    }

    @Override
    public void update(OrderBean entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
