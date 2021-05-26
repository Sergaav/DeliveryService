package com.savaz.delivery.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;


public class LocaleChangeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getContextPath();
        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();
        Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", locale);
        return "redirect:"+path;

    }
}
