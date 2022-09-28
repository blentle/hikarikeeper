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

    public RnId getSelf() {
        return self;
    }

    public void setSelf(RnId self) {
        this.self = self;
    }

    public RnodeGroup getGroup() {
        return group;
    }

    public void setGroup(RnodeGroup group) {
        this.group = group;
    }

    public Rscheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Rscheduler scheduler) {
        this.scheduler = scheduler;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public Rpc getRpc() {
        return rpc;
    }

    public void setRpc(Rpc rpc) {
        this.rpc = rpc;
    }

    public RnodeRepository getRepository() {
        return repository;
    }

    public void setRepository(RnodeRepository repository) {
        this.repository = repository;
    }
}
