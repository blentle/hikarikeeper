package org.hikarikeeper.core.raft;


/**
 * cluster node member
 *
 * nextIndex, matchIndex ?
 *
 */
public class RnodeGroupMember {

    /**
     * current node endpoint
     */
    private final RnodeEndpoint endpoint;

    /**
     * replication state
     */
    private ReplicationState replicationState;


    public RnodeGroupMember(RnodeEndpoint endpoint) {
        this(endpoint, null);
    }

    public RnodeGroupMember(RnodeEndpoint endpoint, ReplicationState replicationState) {
        this.endpoint = endpoint;
        this.replicationState = replicationState;
    }

    public RnodeEndpoint getEndpoint() {
        return endpoint;
    }

    private ReplicationState getReplicationState() {
        return replicationState;
    }
}
