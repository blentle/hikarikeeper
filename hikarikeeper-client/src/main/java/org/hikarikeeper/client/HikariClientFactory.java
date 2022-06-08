package org.hikarikeeper.client;

import org.hikarikeeper.client.impl.DefaultHikariClient;

/**
 * Factory methods for creating a HikariClient
 */
public class HikariClientFactory {

    public static HikariClient createClient(HikariServerConfig serverConfig) {
        return new DefaultHikariClient(serverConfig);
    }
}
