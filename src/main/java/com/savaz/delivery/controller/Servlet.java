package com.savaz.delivery.controller;


import com.savaz.delivery.controller.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;


public class Servlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(Servlet.class);

    @Override
    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter("command");
        logger.info("Processing command "+commandName);
        Command command = CommandContainer.get(commandName);
        String forward = command.execute(request, response);
        if (forward != null && !forward.contains("redirect:")) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            logger.info("End of processing command "+commandName+"  and request forward");
            disp.forward(request, response);

        } else if (forward != null && forward.contains("redirect:")) {
            String temp = forward.replace("redirect:", "");
            logger.info("End of processing command "+commandName+" and redirect");
            response.sendRedirect(temp);
        }
    }
}
