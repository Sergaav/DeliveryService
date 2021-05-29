package com.savaz.delivery.model.service;

import com.savaz.delivery.model.service.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract OrderDao createOrderDao();
    public abstract ParcelDao createParcelDao();
    public abstract DestinationDao createDestinationDao();


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
