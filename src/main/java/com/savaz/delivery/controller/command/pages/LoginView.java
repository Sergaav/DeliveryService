package com.savaz.delivery.controller.command.pages;

import com.savaz.delivery.Path;
import com.savaz.delivery.controller.command.Command;
import com.savaz.delivery.model.entity.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginView implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forward = Path.PAGE_LOGIN;
        if (session != null && session.getAttribute("role")!=null) {
            Roles role = Roles.values()[(int) session.getAttribute("role")];
            if (Roles.ADMIN.equals(role)) {
                forward = "redirect:" + Path.PAGE_ADMIN_MENU;
            }
            if (Roles.USER.equals(role)) {
                forward = "redirect:" + Path.PAGE_USER_MENU;
            }
        }
        return forward;
    }
}
