package com.savaz.delivery.controller.filters;

import com.savaz.delivery.Path;
import com.savaz.delivery.controller.command.Command;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.dao.impl.JDBCUserDao;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.enums.Roles;
import org.apache.catalina.Session;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static org.apache.logging.log4j.LogManager.getLogger;

public class AuthFilter implements Filter {
    Set<String> loggedUsers;
    ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
       loggedUsers = (Set<String>) context.getAttribute("loggedUsers");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (UserDao dao = daoFactory.createUserDao()) {
            final HttpServletRequest req = (HttpServletRequest) servletRequest;
            final HttpServletResponse res = (HttpServletResponse) servletResponse;
            final HttpSession session = req.getSession();
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (session != null && session.getAttribute("login") != null && session.getAttribute("password") != null) {
                final Roles role = Roles.values()[(int) session.getAttribute("role")];
                moveToMenu(req, res, role);
            } else if (dao.userIsExist(login, password)) {
                User user = dao.findUserByLoginAndPass(login, password);
                final Roles role = Roles.values()[user.getRole()];
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("password", password);
                req.getSession().setAttribute("role", user.getRole());
                context.setAttribute("loggedUsers",loggedUsers.add(login));
                System.out.println(context.getAttribute("loggedUsers"));
                moveToMenu(req, res, role);
            } else {
                moveToMenu(req, res, Roles.UNKNOWN);
            }
        }
    }

    private void moveToMenu(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Roles role)
            throws ServletException, IOException {
        if (role.equals(Roles.ADMIN)) {
            servletResponse.sendRedirect(Path.PAGE_LIST_ORDERS);
        } else if (role.equals(Roles.USER)) {
            servletResponse.sendRedirect(Path.PAGE_LIST_MENU);
        } else {
            servletResponse.sendRedirect(Path.PAGE_INDEX);
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}