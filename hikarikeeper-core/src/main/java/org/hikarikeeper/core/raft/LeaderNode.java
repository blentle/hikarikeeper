package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.scheduler.LogReplicaTask;

/**
 * for a leader node, it needs to sync log to follower.
 * @author blentle
 * @since 0.1.0
 */
public class LeaderNode extends RnodeRole {

    /**
     * replicate log thread, may return something
     */
    private LogReplicaTask logReplicaTask;


    public LeaderNode(long term, LogReplicaTask logReplicaTask) {
        super(Rrole.leader, term);
        this.logReplicaTask = logReplicaTask;
    }


    public LogReplicaTask getLogReplicaTask() {
        return logReplicaTask;
    }

    @Override
    public void cancelJob() {
        logReplicaTask.cancel();
    }
}
