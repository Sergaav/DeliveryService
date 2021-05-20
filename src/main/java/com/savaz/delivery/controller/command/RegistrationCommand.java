package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        int role = Integer.parseInt(request.getParameter("role"));
        HttpSession session = request.getSession();
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;
        DaoFactory daoFactory = DaoFactory.getInstance();
        String regexLogin = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
        if (login == null || password == null || login.isEmpty() || password.isEmpty() ||
                !login.matches(regexLogin) ) {

            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        if (login.matches(regexLogin)) {
            errorMessage = "Login/password must be valid";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }


        try (UserDao dao = daoFactory.createUserDao()) {

        }
        return null;
    }
}
