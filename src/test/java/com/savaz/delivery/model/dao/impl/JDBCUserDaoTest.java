package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JDBCUserDaoTest {

    @Test
    void findUserByLogin() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        int role;
        try (UserDao dao = daoFactory.createUserDao()){
            User user = dao.findUserByLogin("admin");
            role = user.getRole();
        }
        Assertions.assertEquals(0,role);
    }

    @Test
    void findById() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        String login;
        try (UserDao dao = daoFactory.createUserDao()){
            User user = dao.findById(1);
            login = user.getLogin();
        }
        Assertions.assertEquals("admin",login);
    }
}