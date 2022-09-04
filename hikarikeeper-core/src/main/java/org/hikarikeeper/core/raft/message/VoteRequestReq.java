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


}
