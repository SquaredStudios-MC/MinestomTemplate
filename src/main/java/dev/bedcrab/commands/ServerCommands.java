package dev.bedcrab.commands;

import net.minestom.server.command.CommandManager;
import net.minestom.server.command.CommandSender;

public class ServerCommands {
    /**
     * Enable commands on the server
     */
    public static void enable(CommandManager manager) throws RuntimeException {
        manager.setUnknownCommandCallback(ServerCommands::fallback);
        manager.register(new StopCommand());
    }

    /**
     * Triggered when an invalid command is executed
     */
    private static void fallback(CommandSender sender, String command) {}
}
