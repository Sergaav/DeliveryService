package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.PriceDao;
import com.savaz.delivery.model.entity.bean.PriceBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JDBCPriceDaoTest {

    @Test
    void findAll() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        List<PriceBean> list;
        try (PriceDao dao = daoFactory.createPriceDao()){
            list = dao.findAll();
        }
        Assertions.assertEquals(7,list.size());
    }
}