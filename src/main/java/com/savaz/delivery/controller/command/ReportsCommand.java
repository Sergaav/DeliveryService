package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.City;
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
        List<OrderBean> beanList = new ArrayList<>();
        if (request.getParameterMap().size() > 1) {
            beanList = getListOrdersDependsParam(request);
        } else {
            beanList = new OrderService().getAllOrders();
        }
        Map<String, String> reportParam = createReportParam(beanList);
        session.setAttribute("reportParam", reportParam);

        return Path.PAGE_REPORTS;
    }

    private Map<String, String> createReportParam(List<OrderBean> beanList) {
        return null;
    }

    private List<OrderBean> getListOrdersDependsParam(HttpServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();
        boolean isCityArrive = false;
        boolean isCityDeparture = false;
        boolean isDate = false;
        try {
            Integer.parseInt(request.getParameter("city_arr"));
            isCityArrive = true;
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        try {
            Integer.parseInt(request.getParameter("city_dep"));
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


        return null;
    }
}
