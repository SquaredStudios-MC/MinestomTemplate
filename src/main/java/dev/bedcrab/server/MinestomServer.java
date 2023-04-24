package dev.bedcrab.server;

import dev.bedcrab.commands.ServerCommands;
import dev.bedcrab.events.ServerEvents;
import net.minestom.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MinestomServer {
    private static MinestomServer instance = null;
    private static final Logger logger = LoggerFactory.getLogger(MinestomServer.class);
    public static final Properties configuration = new Properties();

    /**
     * Pre-startup tasks: executed before the server starts. Throw {@link RuntimeException}s if necessary.
     */
    private void preStartup() throws RuntimeException {
        // add database code here or something
    }

    /**
     * Server startup logic
     */
    private void onStartup() {
        logger.info("Done (yeepee)! For help, type \"help\" or \"?\"");
    }

    /**
     * Server shutdown logic
     */
    private void onShutdown() {
        logger.info("Server shutting down...");
    }

    /// INTERNAL CODE

    private MinestomServer() {
        MinecraftServer server = MinecraftServer.init();

        try {
            ConfigLoader.run();
            preStartup();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.exit(1);
            return;
        }

        String ip = (String) configuration.get("server-ip");
        int port = Integer.parseInt((String) configuration.get("server-port"));

        ServerCommands.enable(MinecraftServer.getCommandManager());
        ServerEvents.enable(MinecraftServer.getGlobalEventHandler());

        logger.info("Starting server on " + ip + ":" + port);
        server.start(ip, port);

        MinecraftServer.getSchedulerManager().buildShutdownTask(this::onShutdown);

        onStartup();
    }

    /**
     * Gets the current MinestomServer, or starts a new one.
     */
    public static MinestomServer getInstance() {
        if (instance == null) instance = new MinestomServer();
        return instance;
    }
}
