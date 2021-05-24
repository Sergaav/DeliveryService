package com.savaz.delivery.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent event) {

    }

    public void contextInitialized(ServletContextEvent event) {
        initCommandContainer();
    }


    private void initCommandContainer() {
        // initialize commands container
        // just load class to JVM
        try {
            Class.forName("com.savaz.delivery.controller.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}