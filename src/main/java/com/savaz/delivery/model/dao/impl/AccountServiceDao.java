package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.exception.ValidationException;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.dao.Service;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountServiceDao implements Service {
    static final Logger logger = LogManager.getLogger(AccountServiceDao.class);

    private static final String SQL_TRANSACTION_POPUP = "UPDATE users SET balance = balance+? WHERE id = ?";
    private static final String SQL_TRANSACTION_PAY_FOR_ORDER = "UPDATE users SET balance = balance-? WHERE id = ?";
    Connection connection;

    public AccountServiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void topUpAccount(int userId, long amount) {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_TRANSACTION_POPUP);
            statement.setLong(1, amount);
            statement.setInt(2, userId);
            statement.executeUpdate();
            connection.commit();
            logger.info("topUpAccount successfully");
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                logger.info("Something went wrong with top up an account!! Roll backed!!");
                throw new ValidationException("Something went wrong with top up an account!!");
            }
        } finally {
            closeStatement(statement);
            closeConnection();
        }
    }

    @Override
    public void payCheck(int userId, OrderBean orderBean) {
        long bill = orderBean.getBill();
        try (PreparedStatement statement = connection.prepareStatement(SQL_TRANSACTION_PAY_FOR_ORDER)) {
            connection.setAutoCommit(false);
            statement.setLong(1, bill);
            statement.setInt(2, userId);
            statement.executeUpdate();
            connection.commit();
            new OrderService().changeStatus(orderBean.getId(), Status.PAID);
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
               logger.error(throwables);
            }
            logger.info("Something went wrong with top up an account!! Roll backed!!");
            throw new ValidationException("Transaction fail");
        }finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
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

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
