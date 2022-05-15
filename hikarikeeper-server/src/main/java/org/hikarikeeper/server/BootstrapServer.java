package org.hikarikeeper.server;

import org.hikarikeeper.core.HikariConstants;
import org.hikarikeeper.core.HikariException;

import java.io.File;
import java.io.IOException;

/**
 * @desc server bootstrap main
 */
public class BootstrapServer {

    private static final File baseFile;

    static {
        // get base path
        String base = System.getProperty(HikariConstants.HIKARI_BASE_PROP);
        File homeFile = null;
        if (base != null) {
            File f = new File(base);
            try {
                homeFile = f.getCanonicalFile();
            } catch (IOException ioe) {
                homeFile = f.getAbsoluteFile();
            }
        }
        baseFile = homeFile;
    }

    public static void main(String[] args) throws HikariException {
        HikariKeeperServer server = new HikariKeeperServer(args);
        server.start();
    }

    public static String getHikariBase() {
        return baseFile.getPath();
    }
}
