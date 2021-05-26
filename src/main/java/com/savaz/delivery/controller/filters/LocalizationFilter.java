package com.savaz.delivery.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {
    LocalizationContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Locale sessionLocale = (Locale) request.getSession().getAttribute("locale");
        if (sessionLocale != null){
            servletResponse.setLocale(sessionLocale);

        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
