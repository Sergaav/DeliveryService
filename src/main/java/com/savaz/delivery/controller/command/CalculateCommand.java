package com.savaz.delivery.controller.command;


import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.enums.City;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class CalculateCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<City> cities = Arrays.asList(City.values());
        session.setAttribute("cities",cities);
      return "redirect:" + Path.PAGE_CALCULATE;
    }
}
