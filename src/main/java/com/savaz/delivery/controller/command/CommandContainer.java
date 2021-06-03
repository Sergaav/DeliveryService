package com.savaz.delivery.controller.command;

import com.savaz.delivery.controller.command.pages.LoginView;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("exception", new ExceptionCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("price", new PriceCommand());
        commands.put("destination", new DestinationCommand());
        commands.put("changeLocale", new LocaleChangeCommand());
        commands.put("loginView", new LoginView());
        commands.put("calculate", new CalculateCommand());
        commands.put("topup", new TopUpCommand());
       //  client commands
        commands.put("userMenu", new UserMenuCommand());
        commands.put("createOrder", new CreateOrderCommand());
        commands.put("confirmOrder", new ConfirmOrderCommand());
        commands.put("userOrders", new UserOrdersCommand());

       // admin commands
        commands.put("adminMenu", new AdminMenuCommand());
        commands.put("listOrders", new AdminListOrdersCommand());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}
