package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.service.DaoFactory;
import com.savaz.delivery.model.service.DestinationDao;
import com.savaz.delivery.model.entity.bean.DestinationsBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DestinationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DaoFactory daoFactory = DaoFactory.getInstance();
        List<DestinationsBean> destinationsBeans;
        try (DestinationDao dao = daoFactory.createDestinationDao()) {
            int pageNumber;
            if (request.getParameter("pageNumber") != null) {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
                destinationsBeans = dao.findAllByPage(pageNumber, 7);
                session.setAttribute("pageNumber",pageNumber);
            } else {
                destinationsBeans = dao.findAll();
            }
            session.setAttribute("destinationsBeans", destinationsBeans);
        }
        return "redirect:" + Path.PAGE_DESTINATIONS;
    }
}
