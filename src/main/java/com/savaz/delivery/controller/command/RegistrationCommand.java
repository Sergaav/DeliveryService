package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.exception.ValidationException;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    private static final String REGEX_PASS = ".{5,10}";
    private static final String REGEX_LOGIN = "^(?=[a-zA-Z0-9._]{5,10}$)(?!.*[_.]{2})[^_.].*[^_.]$";

    static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

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
        String forward = Path.PAGE_REGISTRATION;
        DaoFactory daoFactory = DaoFactory.getInstance();

        if (login == null || login.isEmpty() ||  !login.matches(REGEX_LOGIN) ) {
            errorMessage = "Login/password must be correct";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("Login/password must be correct");
            return forward;
        }
        if (password == null || password.isEmpty() ||  !password.matches(REGEX_PASS) ) {
            errorMessage = "Login/password must be correct";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return forward;
        }
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
            logger.info("User created with login :" + login);
            forward = Path.COMMAND_LIST_MENU+"&login="+login+"&password="+password;
        } catch (ValidationException exception) {
            errorMessage = "User is already exist!!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            forward = Path.PAGE_REGISTRATION;
        }
        return forward;
    }
}
