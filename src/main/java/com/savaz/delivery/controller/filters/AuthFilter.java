package com.savaz.delivery.controller.filters;

import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.enums.Roles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserDao dao = new UserDao();
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");

        if (session != null && session.getAttribute("login") != null && session.getAttribute("password") != null) {
            final Roles role = (Roles) session.getAttribute("role");
            moveToMenu(req, res, role);
        } else if (dao.userIsExist(login, password)) {
            User user = dao.findUserByLoginAndPass(login, password);
            final Roles role = Roles.values()[user.getRole()];
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("role", role);
            System.out.println(user);
            moveToMenu(req, res, role);
        } else {
            moveToMenu(req, res, Roles.UNKNOWN);
        }
        filterChain.doFilter(req,res);
    }

    private void moveToMenu(ServletRequest servletRequest, ServletResponse servletResponse, Roles role) throws ServletException, IOException {
        if (role.equals(Roles.ADMIN)) {
            servletRequest.getRequestDispatcher("/WEB-INF/jsp/admin/adminMenu.jsp").forward(servletRequest, servletResponse);
        }else if (role.equals(Roles.USER)){
            servletRequest.getRequestDispatcher("/WEB-INF/jsp/user/userMenu.jsp").forward(servletRequest, servletResponse);
        }else {
            servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
