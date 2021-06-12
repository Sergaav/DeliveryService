package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.bean.PriceBean;
import com.savaz.delivery.model.entity.enums.City;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.PriceService;
import com.savaz.delivery.service.UserService;
import com.savaz.delivery.service.EntityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CreateOrderCommand implements Command {
    final static Logger logger = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("cities", City.values());
        PriceService priceService = new PriceService();
        List<PriceBean> priceBeans = priceService.getPriceBeanList();
        session.setAttribute("priceBeans", priceBeans);
        Map<String, String[]> parameters = request.getParameterMap();
        if (parameters.size() == 1 && parameters.get("command")[0].equals("createOrder")) {
            logger.info("First forward to page create order");
            return "redirect:" + Path.PAGE_CREATE_ORDER_FORM;
        }
        if (!validateInput(parameters)) {
            session.setAttribute("errorMessage", "Fill the form correctly");
            logger.error("Not correct filled order form");
            return Path.PAGE_CREATE_ORDER_FORM;
        }
        OrderBean orderBean = mapOrderBean(request);
        session.setAttribute("orderBean", orderBean);
        session.setAttribute("city",City.values());
        logger.info("Successful redirect to confirm order");
        return "redirect:" + Path.PAGE_CONFIRM_ORDER_FORM;
    }


    public boolean validateInput(Map<String, String[]> parameters) {
        for (Map.Entry<String, String[]> m : parameters.entrySet()) {
            if (m.getValue()[0] == null || m.getValue()[0].isEmpty()) {
                return false;
            }
        }
        int length = Integer.parseInt(parameters.get("length")[0]);
        int height = Integer.parseInt(parameters.get("height")[0]);
        int width = Integer.parseInt(parameters.get("width")[0]);
        if (length > 50 || height > 50 || width > 50 || length < 1 || height < 1 || width < 1) {
            return false;
        }
        String[] temp = parameters.get("dateArrive")[0].split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
        return date.compareTo(LocalDate.now()) >= 0;
    }


    private OrderBean mapOrderBean(HttpServletRequest request) {
        OrderBean orderBean = new OrderBean();
        User user = new UserService().getUserById((int) request.getSession().getAttribute("userId"));
        orderBean.setUser(user);
        Parcel parcel = new Parcel();
        parcel.setDescription(request.getParameter("description"));
        parcel.setHeight(Integer.parseInt(request.getParameter("height")));
        parcel.setWidth(Integer.parseInt(request.getParameter("width")));
        parcel.setLength(Integer.parseInt(request.getParameter("length")));
        parcel.setWeight(Integer.parseInt(request.getParameter("weight")));
        orderBean.setParcel(parcel);
        orderBean.setCityArriveId(Integer.parseInt(request.getParameter("city_arr")));
        orderBean.setCityDepartureId(Integer.parseInt(request.getParameter("city_dep")));
        orderBean.setRecipientName(request.getParameter("recipientData"));
        orderBean.setAddress(request.getParameter("recipientAddress"));
        orderBean.setStatus(Status.OPENED);
        String[] temp = request.getParameter("dateArrive").split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
        orderBean.setDateCreation(date);
        orderBean.setDateDeparture(date.plusDays(1));
        orderBean.setBill(calculateCost(orderBean));

        return orderBean;
    }

    private long calculateCost(OrderBean orderBean) {
        PriceService priceService = new PriceService();
        List<PriceBean> priceBeans = priceService.getPriceBeanList();
        Parcel parcel = orderBean.getParcel();
        int volumeWeight = parcel.getHeight() * parcel.getLength() * parcel.getWidth() / 4200;
        int basicWeight = Math.max(volumeWeight, parcel.getWeight());
        long rate = 0;
        for (int i = 0; i < priceBeans.size(); i++) {
            if (basicWeight > priceBeans.get(i).getMaxWeight() && basicWeight <= priceBeans.get(i + 1).getMaxWeight()) {
                rate = priceBeans.get(i + 1).getRate();
                orderBean.getParcel().setWeightRateId(priceBeans.get(i + 1).getId());
            }
        }
        double destinRate = new EntityService().getDestinationRate(orderBean.getCityArriveId(), orderBean.getCityDepartureId());
        return (long) (rate* destinRate);
    }
}
