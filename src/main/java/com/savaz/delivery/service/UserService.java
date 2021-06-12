package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;

public class UserService {

    public User getUserById(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        User user;
        try (UserDao dao = daoFactory.createUserDao()) {
            user = dao.findById(id);
        }
        return user;
    }
}
