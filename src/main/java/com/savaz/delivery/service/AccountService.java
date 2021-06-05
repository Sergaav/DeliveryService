package com.savaz.delivery.service;

import com.savaz.delivery.exception.ValidationException;
import com.savaz.delivery.model.entity.bean.OrderBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService implements Service {
    private static final String SQL_TRANSACTION_POPUP = "UPDATE users SET balance = balance+? WHERE id = ?";
    Connection connection;

    public AccountService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void topUpAccount(int userId, long amount) {
        PreparedStatement statement=null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_TRANSACTION_POPUP);
            statement.setLong(1, amount);
            statement.setInt(2,userId);
           statement.executeUpdate();
           connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw  new ValidationException("Something went wrong with pop up account!!");
            }
        } finally {
            closeStatement(statement);
            closeConnection();
        }
    }

    @Override
    public void payCheck(int userId, OrderBean orderBean) {

    }

    public static void closeStatement (PreparedStatement statement){
        if (statement !=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeResultSet (ResultSet resultSet){
        if (resultSet !=null){
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
}
