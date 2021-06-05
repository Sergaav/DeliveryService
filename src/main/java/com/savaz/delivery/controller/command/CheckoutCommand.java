package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter("id"));
        new OrderService().changeStatus(orderId, Status.CONFIRMED);
        return Path.COMMAND_ADMIN_ORDERS;
    }
}
