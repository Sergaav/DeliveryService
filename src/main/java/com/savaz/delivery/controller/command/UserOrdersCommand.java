package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserOrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        List<OrderBean> beanList = new OrderService().getUserOrdersList(userId);
        session.setAttribute("userOrders",beanList);

        return Path.PAGE_USER_ORDERS;
    }
}
