package com.savaz.delivery.controller.command;


import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.DestinationDao;
import com.savaz.delivery.model.dao.PriceDao;
import com.savaz.delivery.model.entity.bean.CalculateBean;
import com.savaz.delivery.model.entity.bean.PriceBean;
import com.savaz.delivery.model.entity.enums.City;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<City> cities = Arrays.asList(City.values());
        List<PriceBean> priceBeans = new ArrayList<>();
        DaoFactory dao = DaoFactory.getInstance();
        try (PriceDao priceDao = dao.createPriceDao()) {
            priceBeans = priceDao.findAll();
        }
        session.setAttribute("priceBeans", priceBeans);
        session.setAttribute("cities", cities);

        CalculateBean calculateBean = new CalculateBean();
        String errorMessage = "";
        try {
            if (request.getParameter("city_arr") != null && !request.getParameter("city_arr").isEmpty() &&
                    request.getParameter("city_dep") != null && !request.getParameter("city_dep").isEmpty() &&
                    request.getParameter("weight") != null && !request.getParameter("weight").isEmpty()) {
                int cityArrive = Integer.parseInt(request.getParameter("city_arr"));
                int cityDeparture = Integer.parseInt(request.getParameter("city_dep"));
                calculateBean.setCityArriveId(cityArrive);
                calculateBean.setCityDepartureId(cityDeparture);
                int weight = Integer.parseInt(request.getParameter("weight"));
                calculateBean.setWeight(weight);

                int basicRate = priceBeans.stream().filter((bean -> bean.getMaxWeight() == weight)).
                        collect(Collectors.toList()).get(0).getRate();
                calculateBean.setBasicRate(basicRate);
                try (DestinationDao destinationDao = dao.createDestinationDao()) {
                    double rateDepArr = destinationDao.findRateById(cityArrive, cityDeparture);
                    calculateBean.setRateDepArr(rateDepArr);
                }
            }
        } catch (NumberFormatException e) {
            errorMessage = "Choose please cities and weight";
            request.setAttribute("errorMessage", errorMessage);
            return Path.PAGE_CALCULATE;
        }
        if (request.getParameter("length") != null && !request.getParameter("length").isEmpty() &&
                request.getParameter("width") != null && !request.getParameter("width").isEmpty() &&
                request.getParameter("height") != null && !request.getParameter("height").isEmpty()) {
            try {
                int length = Integer.parseInt(request.getParameter("length"));
                int width = Integer.parseInt(request.getParameter("width"));
                int height = Integer.parseInt(request.getParameter("height"));
                calculateBean.setLength(length);
                calculateBean.setWidth(width);
                calculateBean.setHeight(height);
            } catch (NumberFormatException e) {
                errorMessage = "Wrong parameter of parcel, it must be number from 1 to 50 cm!!";
                request.setAttribute("errorMessage", errorMessage);
                return Path.PAGE_CALCULATE;
            }
        }
        int cost = calculateShippingCost(calculateBean);
        session.setAttribute("cost", cost);

        return "redirect:" + Path.PAGE_CALCULATE;
    }

    private int calculateShippingCost(CalculateBean bean) {
        int volumeWeight = bean.getHeight() * bean.getLength() * bean.getWidth() / 4200;
        int basicWeight = Math.max(volumeWeight, bean.getWeight());
        return (int) (bean.getRateDepArr() * bean.getBasicRate());
    }
}
