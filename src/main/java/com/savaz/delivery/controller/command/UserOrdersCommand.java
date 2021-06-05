package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.City;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class UserOrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        List<OrderBean> beanList = getListDependsOfParameters(pageNumber,request);
        session.setAttribute("userOrders",beanList);
        session.setAttribute("pageNumber", pageNumber);
        session.setAttribute("city", City.values());
        session.setAttribute("statuses", Status.values());
        return Path.PAGE_USER_ORDERS;
    }


    private List<OrderBean> getListDependsOfParameters(int page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Status status = null;
        LocalDate date;
        List<OrderBean> list;
        int userId = (int) session.getAttribute("userId");

        if (request.getParameterMap().size()>3) {
            boolean isPresent=true;
            try {
                status = Status.values()[Integer.parseInt(request.getParameter("status"))];
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                isPresent=false;
            }
            if (isPresent) {
                if (request.getParameter("dateArrive") != null) {
                    date = Date.valueOf(request.getParameter("dateArrive")).toLocalDate();
                    list = new OrderService().getAllOrdersByPageWithStatusAndDateFilterByUser(userId,status, date);
                } else {
                    list = new OrderService().getAllOrdersByPageWithStatusFilterByUser(userId,status);
                }
            } else {
                if (request.getParameter("dateArrive") != null && !request.getParameter("dateArrive").isEmpty()) {
                    date = Date.valueOf(request.getParameter("dateArrive")).toLocalDate();
                    list = new OrderService().getAllOrdersByPageWithDateFilterByUser(userId,date);
                } else {
                    list = new OrderService().getUserOrdersList(userId);
                }
            }
        }else {
            list = new OrderService().getUserOrdersList(userId);
        }
        int size = list.size();
        int avPages;
        if (size % 7 != 0) {
            avPages=size / 7 + 1;
            session.setAttribute("avPages", avPages);
        } else {
            avPages=size / 7;
            session.setAttribute("avPages", avPages);
        }
        if (page < avPages){
            list = list.subList(7*(page-1),(7*page)+1);
        }else{
            list = list.subList(7*(page-1),size);
        }

        return list;
    }
}
