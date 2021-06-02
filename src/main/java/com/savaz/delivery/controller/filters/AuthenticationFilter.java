package com.savaz.delivery.controller.filters;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.enums.Roles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("login") != null) {
            Roles role = Roles.values()[(int) session.getAttribute("role")];
            if (role.equals(Roles.ADMIN) && !request.getServletPath().contains("/admin")) {
                response.sendRedirect(Path.PAGE_ADMIN_MENU);
            }
            if (role.equals(Roles.USER) && !request.getServletPath().contains("/user")) {
                response.sendRedirect(Path.PAGE_USER_MENU);
            }
            filterChain.doFilter(request, response);
        } else {
            if (!request.getServletPath().contains("/user") && !request.getServletPath().contains("/admin")) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(Path.PAGE_LOGIN);
            }
        }


    }

    @Override
    public void destroy() {

    }
}
