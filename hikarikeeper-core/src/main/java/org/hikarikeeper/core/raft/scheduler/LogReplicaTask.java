package org.hikarikeeper.core.raft.scheduler;

import java.util.concurrent.ScheduledFuture;

/**
 * log replication task
 */
public class LogReplicaTask {

    private final ScheduledFuture<?> scheduledFuture;

    public LogReplicaTask(ScheduledFuture<?> scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }


    public void cancel() {
        scheduledFuture.cancel(false);
    }

}
