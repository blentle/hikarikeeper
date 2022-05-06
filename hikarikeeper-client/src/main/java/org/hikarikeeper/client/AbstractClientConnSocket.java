package org.hikarikeeper.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.List;

/**
 * communicate with server by socket
 */
public abstract class AbstractClientConnSocket implements ClientConn {

    abstract boolean isConnected();

    abstract void connect(InetSocketAddress addr) throws IOException;

    abstract SocketAddress getRemoteSocketAddress();

    abstract SocketAddress getLocalSocketAddress();

    abstract void clean();

    abstract void close();

    abstract void wakeupClcn();

    abstract void enableWrite();

    abstract void disableWrite();

    abstract void enableRWOnly();

    abstract void transport(int waitTimeOut, List<Packet> pendingQueue,
                              LinkedList<Packet> outgoingQueue, MultiClientConn mcc)
            throws IOException, InterruptedException;

    abstract void testableClose() throws IOException;

    abstract void send(Packet p) throws IOException;
}
