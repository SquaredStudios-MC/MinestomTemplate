package dev.bedcrab.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Files;

public class ConfigLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);
    public static final String PROPERTIES_FILE = "server.properties";

    public static void run() throws RuntimeException {
        File propertiesFile = new File(PROPERTIES_FILE);
        logger.info("Loading configuration");
        if (propertiesFile.exists() && !propertiesFile.isDirectory()) {
            logger.info("Opening configuration from server directory");
            openConfig(propertiesFile);
        } else {
            logger.info("Copying local configuration to server directory");
            URL resourcesFile = ConfigLoader.class.getClassLoader().getResource(PROPERTIES_FILE);
            if (resourcesFile == null) {
                throw new RuntimeException("The " + PROPERTIES_FILE + " cannot be found in the jar resources folder.");
            }
            File localFile = new File(resourcesFile.getPath());
            try (FileInputStream stream = new FileInputStream(localFile)) {
                Files.copy(stream, propertiesFile.toPath());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            openConfig(localFile);
        }
    }

    private static void openConfig(File file) throws RuntimeException {
        try (FileInputStream stream = new FileInputStream(file)) {
            MinestomServer.configuration.load(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
