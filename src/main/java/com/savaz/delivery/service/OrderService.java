package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

public class OrderService {


    public void insertNewOrder(OrderBean orderBean) {
       DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.create(orderBean);
        }
    }

    public List<OrderBean> getUserOrdersList(int userId){
        List<OrderBean> list;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            list = dao.findAllUserOrdersByUserId(userId);
        }
        return list;
    }

    public List<OrderBean>  getAllOrdersByPage(int page){
        List<OrderBean> list;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            list = dao.findAllByPage(page);
        }
        return list;
    }

    public List<OrderBean>  getAllOrders(){
        List<OrderBean> list;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            list = dao.findAll();
        }
        return list;
    }




}
