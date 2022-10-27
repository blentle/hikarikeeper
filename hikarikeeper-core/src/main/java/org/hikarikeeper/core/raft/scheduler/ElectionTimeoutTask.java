package org.hikarikeeper.core.raft.scheduler;

import java.util.concurrent.ScheduledFuture;

/**
 * log replication task
 */
public class ElectionTimeoutTask {


    private final ScheduledFuture<?> scheduledFuture;

    public static final ElectionTimeoutTask NON = new ElectionTimeoutTask(new NoneScheduledFuture()) ;

    public ElectionTimeoutTask(ScheduledFuture<?> scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }


    public void cancel() {
        scheduledFuture.cancel(false);
    }
}
