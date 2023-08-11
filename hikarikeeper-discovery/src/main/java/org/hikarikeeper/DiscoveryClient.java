package org.hikarikeeper;

import java.util.List;

public interface DiscoveryClient {

    String memo();

    List<ServiceInstance> getInstanceList(String serviceId);

    List<String> getServices();

    default void probe() {
        this.getServices();
    }

}
