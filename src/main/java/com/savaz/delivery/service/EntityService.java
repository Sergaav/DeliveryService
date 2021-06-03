package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.DestinationDao;
import com.savaz.delivery.model.dao.OrderDao;
import com.savaz.delivery.model.dao.PriceDao;
import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.bean.PriceBean;

public class EntityService {

    public double getDestinationRate(int cityArrId, int cityDepId){
        DaoFactory daoFactory = DaoFactory.getInstance();
        double rate;
        try (DestinationDao dao = daoFactory.createDestinationDao()){
            rate = dao.findRateById(cityArrId,cityDepId);
        }
        return rate;
    }

    public int getWeightRate(int weightRateId){
        DaoFactory daoFactory = DaoFactory.getInstance();
        PriceBean bean;
        try (PriceDao dao = daoFactory.createPriceDao()){
            bean = dao.findById(weightRateId);
        }
        return bean.getRate();
    }


    public Parcel getParcelById (int id){
        Parcel parcel;
        DaoFactory daoFactory =DaoFactory.getInstance();
        try (OrderDao dao = daoFactory.createOrderDao()){
            parcel = dao.findParcel(id);
        }
        return parcel;
    }
}
