package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.exception.ValidationException;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.dao.service.Service;
import com.savaz.delivery.model.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TopUpCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String errorMessage ;
        try {
            if (request.getParameter("amount") != null || !request.getParameter("amount").isEmpty()) {
                double amount = Double.parseDouble(request.getParameter("amount"));
                if (amount < 0) {
                    throw new NumberFormatException();
                }
                int userId = (int) session.getAttribute("userId");
                DaoFactory daoFactory = DaoFactory.getInstance();
                Service service = daoFactory.createAccountService();

                try (UserDao dao = daoFactory.createUserDao()) {
                    service.popUpAccount(userId, amount);
                    User user = dao.findById(userId);
                    session.setAttribute("balance",user.getBalance());

                } catch (ValidationException e) {
                    session.setAttribute("errorMessage", e);
                    return Path.PAGE_POPUP;
                }
            }
        } catch (
                NumberFormatException e) {
            errorMessage = "Enter valid number!!";
            request.setAttribute("errorMessage", errorMessage);
            return Path.PAGE_POPUP;
        }

        return Path.PAGE_USER_MENU;
    }
}
