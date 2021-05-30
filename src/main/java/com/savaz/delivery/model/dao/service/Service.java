package com.savaz.delivery.model.dao.service;

import com.savaz.delivery.model.entity.Order;
import com.savaz.delivery.model.entity.User;

public interface Service {

    void popUpAccount (int userId, double amount);
    void payCheck (int userId, Order order);
}
