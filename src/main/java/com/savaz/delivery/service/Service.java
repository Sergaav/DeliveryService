package com.savaz.delivery.service;

import com.savaz.delivery.model.entity.bean.OrderBean;

public interface Service {

    void popUpAccount (int userId, double amount);
    void payCheck (int userId, OrderBean orderBean);
}
