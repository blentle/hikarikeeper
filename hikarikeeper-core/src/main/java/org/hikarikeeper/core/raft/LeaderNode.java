package org.hikarikeeper.core.raft;

import java.util.concurrent.Callable;

/**
 * for a leader node, it needs to sync log to follower.
 * @author blentle
 * @since 0.1.0
 */
public class LeaderNode extends RnodeRole {

    /**
     * replicate log thread, may return something
     */
    private Callable<?> syncLogTask;


    public LeaderNode(long term, Callable<?> syncLogTask) {
        super(Rrole.leader, term);
        this.syncLogTask = syncLogTask;
    }


    public Callable<?> getSyncLogTask() {
        return syncLogTask;
    }
}
