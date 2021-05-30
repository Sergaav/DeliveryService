package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.exception.ValidationException;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    private static final String REGEX_PASS = ".{5,10}";
    private static final String REGEX_LOGIN = "^(?=[a-zA-Z0-9._]{5,10}$)(?!.*[_.]{2})[^_.].*[^_.]$";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        int role = Integer.parseInt(request.getParameter("role"));
        String locale = request.getLocale().getCountry();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLocale(locale);
        user.setRole(role);
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;
        DaoFactory daoFactory = DaoFactory.getInstance();

        if (login == null || login.isEmpty() ||  !login.matches(REGEX_LOGIN) ) {
            errorMessage = "Login/password must be correct";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        if (password == null || password.isEmpty() ||  !password.matches(REGEX_PASS) ) {
            errorMessage = "Login/password must be correct";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
            forward = Path.COMMAND_LIST_MENU+"&login="+login+"&password="+password;
        } catch (ValidationException exception) {
            errorMessage = "User is already exist!!";
            request.setAttribute("errorMessage", errorMessage);
            forward = Path.PAGE_REGISTRATION;
        }
        return forward;
    }
}
