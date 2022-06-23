package org.hikarikeeper.core.raft;

/**
 * a real raft node contains an endpoint for client (may be ip:port) and a state for replication
 * @author blentle
 * @since 0.1.o
 *
 */
public class Rnode {

    private final Rendpoint rendpoint;

    private final ReplicationState replicationState;

    public Rnode(Rendpoint rendpoint) {
        this(rendpoint, null);
    }

    public Rnode(Rendpoint rendpoint, ReplicationState replicationState) {
        this.rendpoint = rendpoint;
        this.replicationState = replicationState;
    }
}
