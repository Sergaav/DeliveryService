package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.bean.OrderBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JDBCOrderDaoTest {

    @Test
    void findById() {
       DaoFactory daoFactory = DaoFactory.getInstance();
       String login;
       try (OrderDao dao = daoFactory.createOrderDao()){
           OrderBean bean = dao.findById(7);
           login = bean.getUser().getLogin();
       }
        Assertions.assertEquals("user001", login);
    }
}