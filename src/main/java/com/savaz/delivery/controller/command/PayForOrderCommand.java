package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.service.AccountService;
import com.savaz.delivery.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayForOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        new AccountService().payForOrderByUser(userId,orderId);
        session.setAttribute("balance",new UserService().getUserById(userId).getBalance());

        return Path.COMMAND_LIST_ORDERS;
    }
}
