package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.bean.OrderBean;

public class OrderService {


    public void insertNewOrder(OrderBean orderBean) {
       DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.create(orderBean);
        }
    }


}
