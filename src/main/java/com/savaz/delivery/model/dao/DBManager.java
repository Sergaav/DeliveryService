package com.savaz.delivery.model.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static DBManager instance;

    public static DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }


    public Connection getConnection() {
        InitialContext ctx;
        Connection c = null;
        DataSource dataSource = null;
        try {
            ctx = new InitialContext();
            Context context = (Context) ctx.lookup("java:/comp/env");
            dataSource = (DataSource) context.lookup("jdbc/deliveryPool");
            c = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    private DBManager() {
    }


    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Commits and close the given connection.
     *
     * @param con Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con Connection to be rollback and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
