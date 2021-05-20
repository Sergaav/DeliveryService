package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.dao.ParcelDao;
import com.savaz.delivery.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();


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

    private Connection getConnection() {
        try {
          //  Class.forName("com.mysql.cj.jdbc.Driver");
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
