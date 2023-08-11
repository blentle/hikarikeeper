package org.hikarikeeper.discovery;

import org.hikarikeeper.DiscoveryClient;
import org.hikarikeeper.ServiceInstance;

import java.util.List;

/**
 * default implementation for discovery client
 */
public class HikarikeeperDiscoveryClient implements DiscoveryClient {
    @Override
    public String memo() {
        //todo:
        return null;
    }

    @Override
    public List<ServiceInstance> getInstanceList(String serviceId) {
        //todo:
        return null;
    }

    @Override
    public List<String> getServices() {
        //todo:
        return null;
    }
}
