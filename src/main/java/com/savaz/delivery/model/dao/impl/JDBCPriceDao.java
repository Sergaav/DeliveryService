package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.EntityMapper;
import com.savaz.delivery.model.dao.PriceDao;
import com.savaz.delivery.model.entity.bean.PriceBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPriceDao implements PriceDao {
    Connection connection;

    public JDBCPriceDao(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_FIND_ALL_PRICE = "SELECT id,rate_name,weight,rate FROM weight_rate ORDER BY id";

    @Override
    public void create(PriceBean entity) {

    }

    @Override
    public PriceBean findById(int id) {
        return null;
    }

    @Override
    public List<PriceBean> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<PriceBean> priceBeans = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_PRICE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                priceBeans.add(new PriceMapper().mapRow(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        return priceBeans;
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

    public static void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void update(PriceBean entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    private static class PriceMapper implements EntityMapper<PriceBean> {

        @Override
        public PriceBean mapRow(ResultSet rs) {
            try {
                PriceBean bean = new PriceBean();
                bean.setId(rs.getInt("id"));
                bean.setRateName(rs.getString("rate_name"));
                bean.setMaxWeight(rs.getInt("weight"));
                bean.setRate(rs.getInt("rate"));
                return bean;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
