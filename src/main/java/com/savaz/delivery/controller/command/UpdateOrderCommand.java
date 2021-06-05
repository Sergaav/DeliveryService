package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.bean.PriceBean;
import com.savaz.delivery.model.entity.enums.Status;
import com.savaz.delivery.service.EntityService;
import com.savaz.delivery.service.OrderService;
import com.savaz.delivery.service.PriceService;
import com.savaz.delivery.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class UpdateOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map<String, String[]> parameters = request.getParameterMap();
        if (!new CreateOrderCommand().validateInput(parameters)) {
            session.setAttribute("errorMessage", "Fill the form correctly");
            return Path.UPDATE_ORDER;
        }
        OrderBean orderBean = mapOrderBean(request);
        new OrderService().updateOrder(orderBean);
        String path = "";
        if ((int) session.getAttribute("role") == 0){
            path = Path.COMMAND_ADMIN_ORDERS;
        }
        if ((int) session.getAttribute("role") == 1){
            path = Path.COMMAND_LIST_ORDERS;
        }
        return path;
    }


    private OrderBean mapOrderBean(HttpServletRequest request) {
        OrderBean orderBean = new OrderBean();
        orderBean.setId(Integer.parseInt(request.getParameter("id")));
        User user = new UserService().getUserById(Integer.parseInt(request.getParameter("userId")));
        orderBean.setUser(user);
        Parcel parcel = new Parcel();
        parcel.setId(Integer.parseInt(request.getParameter("parcelId")));
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

    public long calculateCost(OrderBean orderBean) {
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
