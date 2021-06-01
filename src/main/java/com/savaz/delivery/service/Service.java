package com.savaz.delivery.service;

import com.savaz.delivery.model.entity.bean.OrderBean;

public interface Service {

    void topUpAccount (int userId, double amount);
    void payCheck (int userId, OrderBean orderBean);
}
