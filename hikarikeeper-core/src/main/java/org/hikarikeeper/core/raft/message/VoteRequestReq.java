package org.hikarikeeper.core.raft.message;

import org.hikarikeeper.core.raft.RnId;

/**
 * may be a rpc request
 */
public class VoteRequestReq {

    /**
     * currentTerm
     */
    private long term;

    /**
     * who are you voting for
     */
    private RnId candidateId;

    /**
     * last log index of candidate
     */
    private long lastLogIndex;

    /**
     * last log term of candidate
     */
    private long lastLogTerm;

    public long getTerm() {
        return term;
    }

    public void setTerm(long term) {
        this.term = term;
    }

    public RnId getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(RnId candidateId) {
        this.candidateId = candidateId;
    }

    public long getLastLogIndex() {
        return lastLogIndex;
    }

    public void setLastLogIndex(long lastLogIndex) {
        this.lastLogIndex = lastLogIndex;
    }

    public long getLastLogTerm() {
        return lastLogTerm;
    }

    public void setLastLogTerm(long lastLogTerm) {
        this.lastLogTerm = lastLogTerm;
    }
}
