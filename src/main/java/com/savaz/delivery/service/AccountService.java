package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.dao.Service;
import com.savaz.delivery.model.entity.bean.OrderBean;

public class AccountService {

    public void payForOrderByUser(int userId, int orderId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Service dao = daoFactory.createAccountServiceDao();
             OrderDao orderDao = daoFactory.createOrderDao()) {
            OrderBean bean = orderDao.findById(orderId);
            dao.payCheck(userId, bean);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
