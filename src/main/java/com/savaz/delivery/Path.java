package com.savaz.delivery;

public class Path {

    // pages
    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";
    public static final String PAGE_LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE_LIST_ORDERS = "admin";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String [] PAGES_NOT_AUTH_USERS = new String[]{"index.jsp","login.jsp","registration.jsp"};

    // commands
    public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";
}
