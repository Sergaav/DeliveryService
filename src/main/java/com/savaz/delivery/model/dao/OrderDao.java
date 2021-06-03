package com.savaz.delivery.model.dao;

import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.bean.DestinationsBean;
import com.savaz.delivery.model.entity.bean.OrderBean;

import java.util.List;

public interface OrderDao extends GenericDao<OrderBean> {

    public Parcel findParcel(int id);
    List<OrderBean> findAllUserOrdersByUserId(int userId);
    List<OrderBean> findAllByPage(int page);
}
