package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.DestinationDao;
import com.savaz.delivery.model.entity.bean.DestinationsBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DestinationCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DaoFactory daoFactory = DaoFactory.getInstance();
        List<DestinationsBean> destinationsBeans;
        try (DestinationDao dao = daoFactory.createDestinationDao()){
            destinationsBeans = dao.findAll();
            session.setAttribute("destinationsBeans",destinationsBeans);
        }
        return "redirect:"+Path.PAGE_DESTINATIONS;
    }
}
