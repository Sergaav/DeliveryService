package com.savaz.delivery.model.dao.impl;

import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.DestinationDao;
import com.savaz.delivery.model.entity.bean.DestinationsBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JDBCDestinationDaoTest {

    @Test
    void findAll() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        List<DestinationsBean> list;
        try(DestinationDao dao = daoFactory.createDestinationDao()){
            list = dao.findAll();
        }
        Assertions.assertEquals(49,list.size());
    }
}