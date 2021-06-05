package com.savaz.delivery.model.dao;

import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.bean.DestinationsBean;
import com.savaz.delivery.model.entity.bean.OrderBean;
import com.savaz.delivery.model.entity.enums.Status;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends GenericDao<OrderBean> {

    public Parcel findParcel(int id);

    List<OrderBean> findAllUserOrdersByUserId(int userId);

    List<OrderBean> findAllByWithStatus(Status status);

    List<OrderBean> findAllByPageWithDate(LocalDate date);

    List<OrderBean> findAllByPageWithStatusAndDate(Status status, LocalDate date);

    List<OrderBean> findAllByPageWithStatusAndDateByUser(int userId, Status status, LocalDate date);

    List<OrderBean> findAllByPageWithStatusByUser(int userId, Status status);

    List<OrderBean> findAllByPageWithDateByUser(int userId, LocalDate date);

    void deleteParcel(int parcelId);

    void updateStatus(int orderId,Status status);
}
