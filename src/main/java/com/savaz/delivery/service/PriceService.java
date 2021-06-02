package com.savaz.delivery.service;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.PriceDao;
import com.savaz.delivery.model.entity.bean.PriceBean;

import java.util.ArrayList;
import java.util.List;

public class PriceService {

    public  List<PriceBean> getPriceBeanList (){
        DaoFactory daoFactory = DaoFactory.getInstance();
        List<PriceBean> priceBeans = new ArrayList<>();
        try (PriceDao dao = daoFactory.createPriceDao()){
            priceBeans = dao.findAll();
        }
        return priceBeans;
    }
}
