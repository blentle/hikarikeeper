package org.hikarikeeper.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import static org.hikarikeeper.core.HikariConstants.*;

public class HikariProperties {

    private static final Logger logger = LoggerFactory.getLogger(HikariProperties.class);

    private static Properties properties = null;


    static {
        loadProperties();
    }

    /**
     * Load properties.
     */
    private static void loadProperties() {

        InputStream is = null;
        String fileName = MAIN_PROPS_FILE_NAME;
        //System -D parameter override
        try {
            String configUrl = System.getProperty(PARAMS_MAIN_CONFIG);
            if (configUrl != null) {
                if (configUrl.indexOf('/') == -1)
                    // No '/'. Must be a file name rather than a URL
                    fileName = configUrl;
                else
                    is = (new URL(configUrl)).openStream();
            }
        } catch (Throwable t) {
            handleThrowable(t);
        }

        if (is == null) {
            try {
                File home = new File(BootstrapServer.getHikariBase());
                File conf = new File(home, "conf");
                File propsFile = new File(conf, fileName);
                is = new FileInputStream(propsFile);
            } catch (Throwable t) {
                handleThrowable(t);
            }
        }

        if (is == null) {
            try {
                //if no config files found ,use default .
                is = HikariProperties.class.getResourceAsStream("/org/hikarikeeper/server/startup/hikari.properties");
            } catch (Throwable t) {
                handleThrowable(t);
            }
        }

        if (is != null) {
            try {
                properties = new Properties();
                properties.load(is);
            } catch (Throwable t) {
                handleThrowable(t);
                logger.warn(t.getMessage());
            } finally {
                try {
                    is.close();
                } catch (IOException ioe) {
                    logger.warn("Could not close hikari properties file", ioe);
                }
            }
        }

        if ((is == null)) {
            // Do something
            logger.warn("Failed to load hikari properties file");
            // That's fine - we have reasonable defaults.
            properties = new Properties();
        }

        // Register the properties as system properties
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String value = properties.getProperty(name);
            if (value != null)
                System.setProperty(name, value);
        }
    }

    private static void handleThrowable(Throwable t) {
        if (t instanceof ThreadDeath)
            throw (ThreadDeath) t;
        if (t instanceof VirtualMachineError)
            throw (VirtualMachineError) t;
        // All other instances of Throwable will be silently swallowed
    }
}
