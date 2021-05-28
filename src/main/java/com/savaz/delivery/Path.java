package com.savaz.delivery;

import com.savaz.delivery.model.entity.enums.Roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Path {

    // pages
    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";
    public static final String PAGE_LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE_LIST_ORDERS = "admin";
    public static final String PAGE_DESTINATIONS = "destination";
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String [] PAGES_NOT_AUTH_USERS = new String[]{"index.jsp","login.jsp","registration.jsp"};
    public static final Map<Roles, List<String>> accessPath = new HashMap<>();

    static {
        List<String> adminPages = new ArrayList<>();
        adminPages.add("admin");
        adminPages.add("/index.jsp");
        adminPages.add("controller");
        accessPath.put(Roles.ADMIN,adminPages);
    }
    // commands
    public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";
}
