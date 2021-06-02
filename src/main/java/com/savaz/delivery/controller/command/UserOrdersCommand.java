package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserOrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.PAGE_USER_ORDERS;
    }
}
