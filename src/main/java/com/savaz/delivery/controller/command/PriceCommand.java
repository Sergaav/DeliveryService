package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.PriceDao;
import com.savaz.delivery.model.entity.bean.PriceBean;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.DestinationDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PriceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DaoFactory daoFactory = DaoFactory.getInstance();
        List<PriceBean> priceBeans;
        try (PriceDao dao = daoFactory.createPriceDao()) {
            priceBeans = dao.findAll();
            session.setAttribute("priceBeans", priceBeans);
        }
        return "redirect:" + Path.PAGE_PRICE;
    }
}
