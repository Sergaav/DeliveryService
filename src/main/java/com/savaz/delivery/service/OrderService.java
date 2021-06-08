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
           dao.deleteOrder(orderID);
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
        OrderBean bean;
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
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.updateStatus(orderId, status);
        }
    }

    public void updateOrder(OrderBean orderBean) {
        DaoFactory daoFactory;
        daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.update(orderBean);
        }
    }

    public List<OrderBean> getOrdersByDateArriveDeparture(LocalDate date, int cityArriveId, int cityDepartureId) {
        List<OrderBean> beanList;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            beanList = dao.findAllOrdersByDateArriveDeparture (date,cityArriveId,cityDepartureId);
        }
        return beanList;
    }

    public List<OrderBean> getOrdersByDateArrive(LocalDate date, int cityArriveId) {
        List<OrderBean> beanList;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            beanList = dao.findAllOrdersByDateArrive (date,cityArriveId);
        }
        return beanList;
    }

    public List<OrderBean> getOrdersByDateDeparture(LocalDate date, int cityDepartureId) {
        List<OrderBean> beanList;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            beanList = dao.findAllOrdersByDateDeparture (date,cityDepartureId);
        }
        return beanList;
    }

    public List<OrderBean> getOrdersByDate(LocalDate date) {
        List<OrderBean> beanList;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            beanList = dao.findAllOrdersByDate(date);
        }
        return beanList;
    }

    public List<OrderBean> getOrdersByArriveDeparture(int cityArriveId, int cityDepartureId) {
        List<OrderBean> beanList;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            beanList = dao.findAllOrdersByArriveDeparture (cityArriveId,cityDepartureId);
        }
        return beanList;
    }

    public List<OrderBean> getOrdersByArrive(int cityArriveId) {
        List<OrderBean> beanList;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            beanList = dao.findAllOrdersByArrive (cityArriveId);
        }
        return beanList;
    }

    public List<OrderBean> getOrdersByDeparture(int cityDepartureId) {
        List<OrderBean> beanList;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            beanList = dao.findAllOrdersByDeparture (cityDepartureId);
        }
        return beanList;
    }
}
