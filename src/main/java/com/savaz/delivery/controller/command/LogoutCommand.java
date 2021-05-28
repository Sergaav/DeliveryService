package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import org.apache.catalina.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("role");
        return Path.PAGE_INDEX;
    }
}
