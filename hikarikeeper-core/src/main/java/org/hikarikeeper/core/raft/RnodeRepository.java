package org.hikarikeeper.core.raft;

/**
 * data like currentTerm, voteFor may be saved after server restart
 */
public interface RnodeRepository {

    long getTerm();

    void setTerm(long term);

    RnId getVotedFor();

    void setVotedFor(RnId candidate);

    void close();
}
