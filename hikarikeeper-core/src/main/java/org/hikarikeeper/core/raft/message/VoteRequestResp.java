package org.hikarikeeper.core.raft.message;

/**
 * may be a rpc request
 */
public class VoteRequestResp {

    /**
     * currentTerm after votes
     */
    private final long term;

    /**
     * whether voted
     */
    private final boolean voted;

    public VoteRequestResp(long term, boolean voted) {
        this.term = term;
        this.voted = voted;
    }

    public long getTerm() {
        return term;
    }

    public boolean isVoted() {
        return voted;
    }
}
