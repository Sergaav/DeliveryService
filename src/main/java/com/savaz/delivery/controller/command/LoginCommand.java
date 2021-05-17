package com.savaz.delivery.controller.command;

import com.savaz.delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("pasword");
        HttpSession session = request.getSession();


       return "user_menu.jsp";
    }
}
