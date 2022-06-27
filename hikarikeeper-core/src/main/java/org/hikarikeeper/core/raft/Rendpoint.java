package org.hikarikeeper.core.raft;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * an endpoint for the access of client, may be ip:port
 *
 * @author blentle
 * @since 0.1.0
 *
 */
public class Rendpoint {

    private final RnId nodeId;

    private final SocketAddress addr;

    public Rendpoint(String id, String host, int port) {
        this(RnId.of(id), new InetSocketAddress(host, port));
    }

    public Rendpoint(RnId nodeId, SocketAddress addr) {
        //todo: sanity check ,may encapsulates a util class
        this.nodeId = nodeId;
        this.addr = addr;
    }
}
