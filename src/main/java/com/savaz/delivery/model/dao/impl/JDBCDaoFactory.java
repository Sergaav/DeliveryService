package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.*;
import com.savaz.delivery.model.dao.service.AccountService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();


    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return null;
    }

    @Override
    public ParcelDao createParcelDao() {
        return null;
    }

    @Override
    public DestinationDao createDestinationDao() {
        return new JDBCDestinationDao(getConnection());
    }

    @Override
    public PriceDao createPriceDao() {
        return new JDBCPriceDao(getConnection());
    }

    @Override
    public CalculateDao createCalculateDao() {
        return new JDBCCalculateDao(getConnection());
    }

    @Override
    public AccountService createAccountService() {
        return new AccountService(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
