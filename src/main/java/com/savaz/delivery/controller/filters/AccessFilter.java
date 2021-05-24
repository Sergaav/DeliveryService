package com.savaz.delivery.controller.filters;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.enums.Roles;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AccessFilter implements Filter {

    // commands access
    private static Map<Roles, List<String>> accessMap = new HashMap<Roles, List<String>>();
    private static List<String> commons = new ArrayList<String>();
    private static List<String> outOfControl = new ArrayList<String>();

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            String errorMessasge = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessasge);
              request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty())
            return false;

        if (outOfControl.contains(commandName))
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        Roles userRole = Roles.values()[(int)session.getAttribute("role")];
        if (userRole == null)
            return false;

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // roles
        accessMap.put(Roles.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Roles.USER, asList(fConfig.getInitParameter("user")));
        // commons
        commons = asList(fConfig.getInitParameter("common"));
                // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}