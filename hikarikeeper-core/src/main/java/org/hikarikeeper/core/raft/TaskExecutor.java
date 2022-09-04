package org.hikarikeeper.core.raft;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public interface TaskExecutor {

    CompletableFuture<?> submit(Runnable task);

    <V> CompletableFuture<V> submit(Callable<?> task);

    void shutdown() throws InterruptedException;
}
