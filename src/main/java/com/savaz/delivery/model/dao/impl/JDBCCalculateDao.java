package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.CalculateDao;
import com.savaz.delivery.model.entity.bean.CalculateBean;

import java.sql.Connection;
import java.util.List;

public class JDBCCalculateDao implements CalculateDao {
    Connection connection;

    public JDBCCalculateDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CalculateBean entity) {

    }

    @Override
    public CalculateBean findById(int id) {
        return null;
    }

    @Override
    public List<CalculateBean> findAll() {
        return null;
    }

    @Override
    public void update(CalculateBean entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
