package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.scheduler.ElectionTimeoutTask;
import org.hikarikeeper.core.raft.scheduler.LogReplicaTask;

import java.util.concurrent.Callable;

/**
 * generic schedule for raft task such as election timeout, log replication sync timeout
 */
public interface Rscheduler {

    /**
     * create a log replication task
     * @param task
     * @return
     */
    LogReplicaTask scheduleLogReplicaTask(Runnable task);

    /**
     * create a election timeout task
     * @param task
     * @return
     */
    ElectionTimeoutTask scheduleLogElecTimeoutTask(Runnable task);

    /**
     * stop a scheduler
     * @return
     * @throws InterruptedException
     */
    boolean abort() throws InterruptedException;
}
