package com.savaz.delivery.controller.command;

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
//        commands.put("viewSettings", new ViewSettingsCommand());
//        commands.put("updateSettings", new UpdateSettingsCommand());

       //  client commands
        commands.put("userMenu", new UserMenuCommand());

       // admin commands
        commands.put("adminMenu", new AdminMenuCommand());

//        log.debug("Command container was successfully initialized");
//        log.trace("Number of commands --> " + commands.size());
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
