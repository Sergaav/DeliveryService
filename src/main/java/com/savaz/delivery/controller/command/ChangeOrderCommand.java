package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.bean.PriceBean;
import com.savaz.delivery.model.entity.enums.City;
import com.savaz.delivery.service.OrderService;
import com.savaz.delivery.service.PriceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeOrderCommand implements Command {
    static final Logger logger = LogManager.getLogger(ChangeOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter("id"));
        OrderBean bean = new OrderService().getOrderBeanById(orderId);
        HttpSession session = request.getSession();
        session.setAttribute("orderBean", bean);
        session.setAttribute("cities", City.values());
        List<PriceBean> priceBeans = new PriceService().getPriceBeanList();
        session.setAttribute("priceBeans", priceBeans);

        logger.info("Successfully change the order! ");
        return Path.PAGE_CHANGE_ORDER;
    }
}
