package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.City;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("cities", City.values());
        List<OrderBean> beanList;
        if (request.getParameterMap().size() > 1) {
            beanList = getListOrdersDependsParam(request);
        } else {
            beanList = new OrderService().getAllOrders();
        }
        Map<String, Number> reportParam = createReportParam(beanList);
        session.setAttribute("reportParam", reportParam);

        return Path.PAGE_REPORTS;
    }

    private Map<String,Number> createReportParam(List<OrderBean> bean) {
        Map<String, Number> map = new HashMap<>();
        map.put("total_orders", bean.size());
        map.put("paid_orders", (int) bean.stream().filter(x -> x.getStatus() == Status.PAID).count());
        map.put("new_orders", (int) bean.stream().filter(x -> x.getStatus() == Status.OPENED).count());
        map.put("closed_orders", (int) bean.stream().filter(x -> x.getStatus() == Status.CLOSED).count());
        map.put("confirmed_orders", (int) bean.stream().filter(x -> x.getStatus() == Status.CONFIRMED).count());
        long billPaid = bean.stream().filter(x -> x.getStatus() == Status.PAID).mapToLong(OrderBean::getBill).sum();
        long billClosed = bean.stream().filter(x -> x.getStatus() == Status.CLOSED).mapToLong(OrderBean::getBill).sum();
        map.put("totalSum", billPaid + billClosed);
        return map;
    }

    private List<OrderBean> getListOrdersDependsParam(HttpServletRequest request) {
        boolean isCityArrive = false;
        boolean isCityDeparture = false;
        boolean isDate = false;
        int cityArriveId = 0;
        int cityDepartureId = 0;
        try {
            cityArriveId = Integer.parseInt(request.getParameter("city_arr"));
            isCityArrive = true;
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        try {
            cityDepartureId = Integer.parseInt(request.getParameter("city_dep"));
            isCityDeparture = true;
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        LocalDate date = null;

        if (request.getParameter("dateArrive") != null && !request.getParameter("dateArrive").isEmpty()) {
            String[] temp = request.getParameter("dateArrive").split("-");
            date = LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            isDate = true;
        }

        if (!isDate && !isCityArrive && !isCityDeparture) {
            return new OrderService().getAllOrders();
        }
        if (isDate && isCityArrive && isCityDeparture) {
            return new OrderService().getOrdersByDateArriveDeparture(date, cityArriveId, cityDepartureId);
        }
        if (isDate && isCityArrive && !isCityDeparture) {
            return new OrderService().getOrdersByDateArrive(date, cityArriveId);
        }
        if (isDate && !isCityArrive && isCityDeparture) {
            return new OrderService().getOrdersByDateDeparture(date, cityDepartureId);
        }
        if (isDate && !isCityArrive && !isCityDeparture) {
            return new OrderService().getOrdersByDate(date);
        }
        if (!isDate && isCityArrive && isCityDeparture) {
            return new OrderService().getOrdersByArriveDeparture(cityArriveId, cityDepartureId);
        }
        if (!isDate && isCityArrive && !isCityDeparture) {
            return new OrderService().getOrdersByArrive(cityArriveId);
        }
        if (!isDate && !isCityArrive && isCityDeparture) {
            return new OrderService().getOrdersByDeparture(cityDepartureId);
        }
        return new OrderService().getAllOrders();
    }
}
