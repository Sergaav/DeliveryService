package com.savaz.delivery.model.service.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    private ConnectionPoolHolder() {
    }

    public static DataSource getDataSource() {
        Driver driver=null;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (dataSource == null && driver !=null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriver(driver);
                    ds.setUrl("jdbc:mysql://localhost:3306/delivery");
                    ds.setUsername("root");
                    ds.setPassword("12345");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}



