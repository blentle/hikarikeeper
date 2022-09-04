package org.hikarikeeper.core.raft.repository;

import org.hikarikeeper.core.raft.RnId;
import org.hikarikeeper.core.raft.RnodeRepository;

/**
 * save data to memory
 * todo:
 */
public class MemRnodeRepo implements RnodeRepository {

    @Override
    public long getTerm() {
        return 0;
    }

    @Override
    public void setTerm(long term) {

    }

    @Override
    public RnId getVotedFor() {
        return null;
    }

    @Override
    public void setVotedFor(RnId candidate) {

    }

    @Override
    public void close() {

    }
}
