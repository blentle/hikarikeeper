package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.repository.RnodeRepoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * a real raft node contains an endpoint for client (may be ip:port) and a state for replication
 * @author blentle
 * @since 0.1.0
 *
 */
public class Rnode {

    private static final Logger logger = LoggerFactory.getLogger(Rnode.class);

    private final Rendpoint rendpoint;

    private final ReplicationState replicationState;

    private final ComponentConnectorContext connectorContext;

    private RnodeRole role;

    private volatile boolean started;



    public Rnode(Rendpoint rendpoint) {
        this(rendpoint, null, null);
    }

    public Rnode(Rendpoint rendpoint, ReplicationState replicationState, ComponentConnectorContext connectorContext) {
        this.rendpoint = rendpoint;
        this.replicationState = replicationState;
        this.connectorContext = connectorContext;
    }

    public synchronized void start() throws RnodeRepoException {
        if (started)
            return;
        //register event
        //init connector
        //change role, initialize current node follower
        RnodeRepository repo = connectorContext.getRepository();
        changeRole(new FollowerNode(repo.getTerm(), null, repo.getVotedFor(), connectorContext.getScheduler().scheduleLogElecTimeoutTask(this::elecTimeout)));
        this.started = true;


    }

    private void changeRole(RnodeRole newRole) throws RnodeRepoException {
        if (logger.isDebugEnabled())
            logger.debug("node:{} role state changed to :{}", connectorContext.getSelf(), newRole);
        RnodeRepository repo = connectorContext.getRepository();
        repo.setTerm(newRole.getTerm());
        if (newRole.getRole().equals(Rrole.follower))
            repo.setVotedFor(((FollowerNode)newRole).getVotedFor());
        this.role = newRole;
    }

    private void elecTimeout() {

    }
}
