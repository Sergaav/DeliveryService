package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            new OrderService().deleteOrder(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String path = Path.PAGE_ERROR_PAGE;
        if ((int) session.getAttribute("role") ==0){
            path = Path.COMMAND_ADMIN_ORDERS;
        }
        if ((int) session.getAttribute("role") ==1){
            path = Path.COMMAND_LIST_ORDERS;
        }
        return path;
    }
}
