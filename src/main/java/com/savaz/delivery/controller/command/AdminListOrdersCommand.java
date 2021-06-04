package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.City;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminListOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        List<OrderBean> orderBeanList = new OrderService().getAllOrdersByPage(pageNumber);
        HttpSession session = request.getSession();
        session.setAttribute("orderBeanList",orderBeanList);
        session.setAttribute("pageNumber",pageNumber);
        session.setAttribute("city", City.values());
        session.setAttribute("statuses", Status.values());
        int size = new OrderService().getAllOrders().size();
        if (size%7 != 0){
            session.setAttribute("avPages",size/7+1);
        }else{
            session.setAttribute("avPages",size/7);
        }

        return Path.PAGE_ADMIN_ORDERS;
    }
}
