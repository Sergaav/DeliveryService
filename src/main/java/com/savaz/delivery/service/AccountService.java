package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.dao.Service;
import com.savaz.delivery.model.entity.bean.OrderBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountService {
    static final Logger logger = LogManager.getLogger(AccountService.class);

    public void payForOrderByUser(int userId, int orderId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Service dao = daoFactory.createAccountServiceDao();
             OrderDao orderDao = daoFactory.createOrderDao()) {
            OrderBean bean = orderDao.findById(orderId);
            dao.payCheck(userId, bean);
        } catch (Exception exception) {
            logger.error(exception);
        }
    }
}
