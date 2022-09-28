package org.hikarikeeper.core.raft.repository;

import org.hikarikeeper.core.raft.RnId;
import org.hikarikeeper.core.raft.RnodeRepository;

/**
 * save data in memory
 */
public class MemRnodeRepo implements RnodeRepository {

    private long term;

    private RnId votedFor;

    public MemRnodeRepo() {
        this(0, null);
    }

    public MemRnodeRepo(long term, RnId votedFor) {
        this.term = term;
        this.votedFor = votedFor;
    }

    @Override
    public long getTerm() {
        return term;
    }

    @Override
    public void setTerm(long term) {
        this.term = term;
    }

    @Override
    public RnId getVotedFor() {
        return votedFor;
    }

    @Override
    public void setVotedFor(RnId votedFor) {
        this.votedFor = votedFor;
    }

    @Override
    public void close() {

    }
}
