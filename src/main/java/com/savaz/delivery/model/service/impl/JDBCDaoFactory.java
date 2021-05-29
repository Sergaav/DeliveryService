package com.savaz.delivery.model.service.impl;

import com.savaz.delivery.model.service.*;

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

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
