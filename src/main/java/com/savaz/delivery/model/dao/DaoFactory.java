package com.savaz.delivery.model.dao;

import com.savaz.delivery.model.dao.impl.JDBCDaoFactory;
import com.savaz.delivery.service.AccountService;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract OrderDao createOrderDao();
    public abstract ParcelDao createParcelDao();
    public abstract DestinationDao createDestinationDao();
    public abstract PriceDao createPriceDao();
    public abstract CalculateDao createCalculateDao();
    public abstract AccountService createAccountService();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
