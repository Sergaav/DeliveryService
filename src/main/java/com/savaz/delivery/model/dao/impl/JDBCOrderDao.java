package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.bean.OrderBean;

import java.sql.Connection;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(OrderBean entity) {

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
