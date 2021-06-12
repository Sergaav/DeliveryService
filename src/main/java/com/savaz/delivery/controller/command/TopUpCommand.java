package com.savaz.delivery.controller.command;

import com.savaz.delivery.Path;
import com.savaz.delivery.exception.ValidationException;
import com.savaz.delivery.model.dao.DaoFactory;
import com.savaz.delivery.model.dao.UserDao;
import com.savaz.delivery.model.dao.Service;
import com.savaz.delivery.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TopUpCommand implements Command {
    static final Logger logger = LogManager.getLogger(TopUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String errorMessage ;
        try {
            if (request.getParameter("amount") != null || !request.getParameter("amount").isEmpty()) {
                long amount = Long.parseLong(request.getParameter("amount"));
                if (amount < 0) {
                    errorMessage = "Enter valid number!!";
                    request.setAttribute("errorMessage", errorMessage);
                    logger.error(errorMessage);
                    return Path.PAGE_TOPUP;
                }
                int userId = (int) session.getAttribute("userId");
                DaoFactory daoFactory = DaoFactory.getInstance();


                try (UserDao dao = daoFactory.createUserDao();
                     Service service = daoFactory.createAccountServiceDao();) {
                    service.topUpAccount(userId, amount);
                    User user = dao.findById(userId);
                    session.setAttribute("balance",user.getBalance());

                } catch (ValidationException e) {
                    session.setAttribute("errorMessage", e);
                    logger.error(e);
                    return Path.PAGE_TOPUP;
                } catch (Exception exception) {
                    logger.error(exception);
                }
            }
        } catch (
                NumberFormatException e) {
            errorMessage = "Enter valid number!!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error(errorMessage);
            return Path.PAGE_TOPUP;
        }

        return "redirect:"+Path.PAGE_USER_MENU;
    }
}
