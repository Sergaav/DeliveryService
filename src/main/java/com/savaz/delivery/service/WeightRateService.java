package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.DestinationDao;

public class WeightRateService {

    public static double getWeightRate (int cityArrId,int cityDepId){
        DaoFactory daoFactory = DaoFactory.getInstance();
        double rate;
        try (DestinationDao dao = daoFactory.createDestinationDao()){
            rate = dao.findRateById(cityArrId,cityDepId);
        }
        return rate;
    }
}
