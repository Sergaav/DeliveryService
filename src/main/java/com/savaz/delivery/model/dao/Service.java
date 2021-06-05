package com.savaz.delivery.model.dao;

import com.savaz.delivery.model.entity.bean.OrderBean;

public interface Service extends AutoCloseable{

    void topUpAccount (int userId, long amount);
    void payCheck (int userId, OrderBean orderBean);
}
