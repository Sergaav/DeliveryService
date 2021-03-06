package com.savaz.delivery.controller.command;

import com.savaz.delivery.model.entity.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;


public class LocaleChangeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getRequestURI().substring(0,request.getRequestURI().lastIndexOf('/'));
        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();
        String forward = "redirect:"+path;
        Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", locale);

        return forward;

    }
}
