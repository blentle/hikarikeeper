package org.hikarikeeper.core.raft;

import org.hikarikeeper.core.raft.repository.RnodeRepoException;

/**
 * data like currentTerm, voteFor may be saved after server restart
 */
public interface RnodeRepository {

    long getTerm();

    void setTerm(long term) throws RnodeRepoException;

    RnId getVotedFor();

    void setVotedFor(RnId candidate) throws RnodeRepoException;

    void close() throws RnodeRepoException;
}
