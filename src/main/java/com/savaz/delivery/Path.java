package com.savaz.delivery;

public class Path {

    // pages
    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_REGISTRATION = "registration";
    public static final String PAGE_TOPUP = "topup";
    public static final String PAGE_ERROR_PAGE = "error";
    public static final String PAGE_USER_MENU = "user";
    public static final String PAGE_ADMIN_MENU = "admin";
    public static final String PAGE_DESTINATIONS = "destination";
    public static final String PAGE_CALCULATE = "calculate";
    public static final String PAGE_PRICE = "price";
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String PAGE_CREATE_ORDER_FORM = "create_order";
    public static final String PAGE_CONFIRM_ORDER_FORM = "confirm_order";
    public static final String PAGE_USER_ORDERS = "userOrders";

    // commands
    public static final String COMMAND_LIST_ORDERS = "/controller?command=userOrders";
    public static final String COMMAND_LIST_MENU = "/controller?command=login";
}
