package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.entity.enums.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderCommandTest {
    HttpServletRequest req;
    HttpServletResponse resp;
    HttpSession session;

    @BeforeEach
    void createSession() {
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        req.setAttribute("userId",1);
        session.setAttribute("userId",1);
        Mockito.when(req.getParameter("description")).thenReturn("description");
        Mockito.when(req.getParameter("height")).thenReturn("25");
        Mockito.when(req.getParameter("width")).thenReturn("25");
        Mockito.when(req.getParameter("length")).thenReturn("25");
        Mockito.when(req.getParameter("weight")).thenReturn("10");
        Mockito.when(req.getParameter("city_arr")).thenReturn("0");
        Mockito.when(req.getParameter("city_dep")).thenReturn("1");
        Mockito.when(req.getParameter("recipientData")).thenReturn("Test Test");
        Mockito.when(req.getParameter("recipientAddress")).thenReturn("Test address");
        Mockito.when(req.getParameter("dateArrive")).thenReturn("2021-06-15");
        Mockito.when(req.getParameter("command")).thenReturn("createOrder");
        Mockito.when(req.getSession()).thenReturn(session);
        Map<String, String[]> parameters =new HashMap<>();
        parameters.put("command",new String[] {"createOrder"});
        parameters.put("height",new String[] {"25"});
        parameters.put("width",new String[] {"25"});
        parameters.put("length",new String[] {"25"});
        parameters.put("dateArrive",new String[] {"2021-06-15"});
       Mockito.when(req.getParameterMap()).thenReturn(parameters);
       Mockito.when(req.getSession().getAttribute("userId")).thenReturn(1);
    }

    @Test
    void execute() {
        CreateOrderCommand command = new CreateOrderCommand();
        String path = command.execute(req, resp);
        Assertions.assertEquals("redirect:" + Path.PAGE_CONFIRM_ORDER_FORM,path);
    }
}