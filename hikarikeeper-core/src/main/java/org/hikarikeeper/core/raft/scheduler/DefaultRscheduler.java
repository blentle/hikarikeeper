package org.hikarikeeper.core.raft.scheduler;

import org.hikarikeeper.core.RandGenerator;
import org.hikarikeeper.core.raft.Rscheduler;

import java.util.concurrent.*;

public class DefaultRscheduler implements Rscheduler {

    private final long maxElecTimeout;

    private final long minElecTinout;

    private final long logReplicaDelay;

    private final long logReplicaInterval;

    /**
     * random number generator
     */
    private final RandGenerator randGenerator;

    private final ScheduledExecutorService executorService;

    public DefaultRscheduler(long maxElecTimeout, long minElecTinout, long logReplicaDelay, long logReplicaInterval, RandGenerator randGenerator, ScheduledExecutorService executorService) {
        this.maxElecTimeout = maxElecTimeout;
        this.minElecTinout = minElecTinout;
        this.logReplicaDelay = logReplicaDelay;
        this.logReplicaInterval = logReplicaInterval;
        this.randGenerator = randGenerator;
        this.executorService = executorService;
    }

    @Override
    public LogReplicaTask scheduleLogReplicaTask(Runnable task) {
        ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(task, logReplicaDelay, logReplicaInterval, TimeUnit.MILLISECONDS);
        return new LogReplicaTask(scheduledFuture);
    }

    @Override
    public ElectionTimeoutTask scheduleLogElecTimeoutTask(Runnable task) {
        long timeout = randGenerator.nextLong(maxElecTimeout - minElecTinout) + minElecTinout;
        ScheduledFuture<?> scheduledFuture = executorService.schedule(task, timeout, TimeUnit.MILLISECONDS);
        return new ElectionTimeoutTask(scheduledFuture);
    }

    @Override
    public boolean abort() throws InterruptedException {
        //todo:
        return false;
    }
}
