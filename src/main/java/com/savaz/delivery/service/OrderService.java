package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class OrderService {


    public void insertNewOrder(OrderBean orderBean) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.create(orderBean);
        }
    }

    public void deleteOrder(int orderID) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            int parcelId = dao.findById(orderID).getParcel().getId();
            dao.delete(orderID);
            dao.deleteParcel(parcelId);
        }
    }

    public List<OrderBean> getUserOrdersList(int userId) {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAllUserOrdersByUserId(userId);
        }
        return list;
    }

    public OrderBean getOrderBeanById(int id) {
        OrderBean bean ;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            bean = dao.findById(id);
        }
        return bean;
    }

    public List<OrderBean> getAllOrdersByPage() {
        List<OrderBean> list;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAll();
        }
        return list;
    }

    public List<OrderBean> getAllOrdersByPageWithStatusFilter(Status status) {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAllByWithStatus(status);
        }
        return list;
    }

    public List<OrderBean> getAllOrdersByPageWithDateFilter(LocalDate date) {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAllByPageWithDate(date);
        }
        return list;
    }

    public List<OrderBean> getAllOrdersByPageWithStatusAndDateFilter(Status status, LocalDate date) {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAllByPageWithStatusAndDate(status, date);
        }
        return list;
    }

    public List<OrderBean> getAllOrdersByPageWithStatusAndDateFilterByUser(int userId, Status status, LocalDate date) {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAllByPageWithStatusAndDateByUser(userId, status, date);
        }
        return list;
    }

    public List<OrderBean> getAllOrdersByPageWithStatusFilterByUser(int userId, Status status) {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAllByPageWithStatusByUser(userId, status);
        }
        return list;
    }

    public List<OrderBean> getAllOrdersByPageWithDateFilterByUser(int userId, LocalDate date) {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAllByPageWithDateByUser(userId, date);
        }
        return list;
    }

    public List<OrderBean> getAllOrders() {
        List<OrderBean> list;
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            list = dao.findAll();
        }
        return list;
    }


    public void changeStatus(int orderId, Status status) {
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            dao.updateStatus(orderId,status);
        }
    }

    public void updateOrder(OrderBean orderBean) {
        DaoFactory daoFactory;
        daoFactory =DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            dao.update(orderBean);
        }
    }
}
