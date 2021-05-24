package com.savaz.delivery.controller.filters;


import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.enums.Roles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


public class AuthFilter implements Filter {
    Set<String> loggedUsers;
    ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
        Object o = context.getAttribute("loggedUsers");
        if (o instanceof Set) {
            loggedUsers = (Set<String>) o;
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        Roles role;

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DaoFactory daoFactory = DaoFactory.getInstance();
        if (session.getAttribute("login") != null && session.getAttribute("password") != null &&
                session.getAttribute("role") != null) {
            role = Roles.values()[(int) session.getAttribute("role")];
            moveToMenu(req, res, role);
        } else if (login != null && password != null) {
            try (UserDao dao = daoFactory.createUserDao()) {
                if (dao.userIsExist(login, password)) {
                    User user = dao.findUserByLoginAndPass(login, password);
                    role = Roles.values()[user.getRole()];
                    req.getSession().setAttribute("login", login);
                    req.getSession().setAttribute("password", password);
                    req.getSession().setAttribute("role", role);
                    filterChain.doFilter(req, res);
                } else {

                    moveToMenu(req, res, Roles.UNKNOWN);
                }
            }
        } else {
            String page = req.getParameter("page");
            if (page != null && page.equals("login")) {
                res.sendRedirect(Path.PAGE_LOGIN);
                return;
            }
            moveToMenu(req, res, Roles.UNKNOWN);
        }
    }

    private void moveToMenu(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Roles role) throws ServletException, IOException {
        if (role.equals(Roles.ADMIN)) {
            servletResponse.sendRedirect(Path.PAGE_LIST_ORDERS);
        } else if (role.equals(Roles.USER)) {
            servletResponse.sendRedirect(Path.PAGE_LIST_MENU);
        } else {
            servletResponse.sendRedirect(Path.PAGE_LOGIN);
        }
    }


    @Override
    public void destroy() {

    }
}