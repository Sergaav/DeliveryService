package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmOrderCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        OrderBean orderBean = (OrderBean) session.getAttribute("orderBean");
        OrderService service = new OrderService();
        service.insertNewOrder(orderBean);

        return Path.COMMAND_LIST_ORDERS;
    }
}
