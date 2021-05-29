package com.savaz.delivery.model.service;

import com.savaz.delivery.model.entity.bean.DestinationsBean;

import java.util.List;

public interface DestinationDao extends GenericDao<DestinationsBean>{

    List<DestinationsBean> findAllByPage(int page,int limit);

}
