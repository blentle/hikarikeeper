package org.hikarikeeper.core.raft;

public class ComponentConnectorContext {

    /**
     * who am I
     */
    private RnId self;

    private RnodeGroup group;

    private Rscheduler scheduler;

    private TaskExecutor taskExecutor;

    private Rpc rpc;

    private RnodeRepository repository;
}
