package org.hikarikeeper;

import java.net.URI;
import java.util.Map;

public interface ServiceInstance {

    String getInstanceId();

    String getServiceId();

    String getHost();

    int getPort();

    boolean isSecure();

    URI getUri();

    Map<String, String> getMetadata();
}
