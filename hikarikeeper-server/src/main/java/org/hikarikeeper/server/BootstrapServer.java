package org.hikarikeeper.server;

import org.hikarikeeper.core.HikariException;

/**
 * @desc server bootstrap main
 */
public class BootstrapServer {

    public static void main(String[] args) throws HikariException {
        HikariKeeperServer server = new HikariKeeperServer(args);
        server.start();
    }
}
