package com.savaz.delivery.model.dao;

import com.savaz.delivery.model.entity.bean.DestinationsBean;

import java.util.List;

public interface DestinationDao extends GenericDao<DestinationsBean>{

    List<DestinationsBean> findAllByPage(int page,int limit);
    double findRateById(int cityArrive,int cityDeparture);

}
