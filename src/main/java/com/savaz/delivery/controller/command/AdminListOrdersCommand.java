package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.City;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdminListOrdersCommand implements Command {
    static final Logger logger = LogManager.getLogger(AdminListOrdersCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        List<OrderBean> orderBeanList;
        orderBeanList = getListDependsOfParameters(pageNumber,request);
        HttpSession session = request.getSession();
        session.setAttribute("orderBeanList", orderBeanList);
        session.setAttribute("pageNumber", pageNumber);
        session.setAttribute("city", City.values());
        session.setAttribute("statuses", Status.values());
        logger.info("Admin forward to orders menu");
        return Path.PAGE_ADMIN_ORDERS;
    }

    private List<OrderBean> getListDependsOfParameters(int page, HttpServletRequest request) {
        Status status = null;
        LocalDate date;
        List<OrderBean> list;

        if (request.getParameterMap().size()>2) {
            boolean isPresent=true;
            try {
                status = Status.values()[Integer.parseInt(request.getParameter("status"))];
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                isPresent=false;
            }
            if (isPresent) {
                if (request.getParameter("dateArrive") != null && !request.getParameter("dateArrive").isEmpty() ) {
                    date = Date.valueOf(request.getParameter("dateArrive")).toLocalDate();
                    list = new OrderService().getAllOrdersByPageWithStatusAndDateFilter(status, date);
                } else {
                    list = new OrderService().getAllOrdersByPageWithStatusFilter(status);
                }
            } else {
                if (request.getParameter("dateArrive") != null && !request.getParameter("dateArrive").isEmpty()) {
                    date = Date.valueOf(request.getParameter("dateArrive")).toLocalDate();
                    list = new OrderService().getAllOrdersByPageWithDateFilter(date);
                } else {
                    list = new OrderService().getAllOrdersByPage();
                }
            }
        }else {
            list = new OrderService().getAllOrdersByPage();
        }
        HttpSession session = request.getSession();
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
            list = list.subList(7*(page-1),(7*page-1));
        }else{
            list = list.subList(7*(page-1),size);
        }

        return list;
    }


}

