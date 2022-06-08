package org.hikarikeeper.client;

/**
 * server config builder
 */
public class ServerConfigBuilder {

    private HikariServerConfig serverConfig = new HikariServerConfig();


    public HikariServerConfig serverList(String servers) {
        this.serverConfig.setServers(servers);
        return this.serverConfig;
    }

    public HikariServerConfig build() {
        return this.serverConfig;
    }


}
