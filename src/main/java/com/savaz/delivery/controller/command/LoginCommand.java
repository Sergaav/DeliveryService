package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String errorMessage = null;
        String forward = Path.PAGE_ERROR_PAGE;
        DaoFactory daoFactory = DaoFactory.getInstance();

        try (UserDao dao = daoFactory.createUserDao()) {

            if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
                errorMessage = "Login/password cannot be empty";
                request.setAttribute("errorMessage", errorMessage);
                return forward;
            }
            String regexLogin = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
            if (login.matches(regexLogin)) {
                errorMessage = "Login/password must be valid";
                request.setAttribute("errorMessage", errorMessage);
                return forward;
            }

            User user = dao.findUserByLogin(login);

            if (user == null || !password.equals(user.getPassword())) {
                errorMessage = "Cannot find user with such login/password";
                request.setAttribute("errorMessage", errorMessage);

                return forward;
            } else {
                Roles userRole = Roles.values()[user.getRole()];

                if (userRole == Roles.ADMIN)
                    forward = "redirect:"+Path.PAGE_LIST_ORDERS;

                if (userRole == Roles.USER)
                    forward = "redirect:"+Path.COMMAND_LIST_MENU;

                session.setAttribute("login", user.getLogin());
                session.setAttribute("password",user.getPassword());
                session.setAttribute("role", user.getRole());

                // work with i18n
                String userLocaleName = user.getLocale();
                if (userLocaleName != null && !userLocaleName.isEmpty()) {
                    Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
                    session.setAttribute("locale", userLocaleName);
                }
            }

            return forward;
        }
    }
}
