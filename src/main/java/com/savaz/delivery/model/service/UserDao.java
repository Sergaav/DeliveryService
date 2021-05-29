package com.savaz.delivery.model.service;

import com.savaz.delivery.model.entity.User;

public interface UserDao extends GenericDao<User>{

    User findUserByLogin(String login);
    User findUserByLoginAndPass(String login, String password);
    boolean userIsExist(String login,String password);

}
