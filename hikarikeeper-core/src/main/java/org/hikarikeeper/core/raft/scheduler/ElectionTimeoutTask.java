package org.hikarikeeper.core.raft.scheduler;

import java.util.concurrent.ScheduledFuture;

/**
 * log replication task
 */
public class ElectionTimeoutTask {

    private final ScheduledFuture<?> scheduledFuture;

    public ElectionTimeoutTask(ScheduledFuture<?> scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }


    public void cancel() {
        scheduledFuture.cancel(false);
    }
}
