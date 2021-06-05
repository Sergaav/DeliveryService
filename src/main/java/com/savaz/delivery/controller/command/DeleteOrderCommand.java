package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            new OrderService().deleteOrder(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Path.COMMAND_LIST_ORDERS;
    }
}
