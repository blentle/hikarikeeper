package org.hikarikeeper.core.raft.message;


/**
 * may be a rpc request
 */
public class EntryAppendResp {

    /**
     * current term
     */
    private final long term;

    /**
     * if append log entry success return true, else false;
     */
    private final boolean success;

    public EntryAppendResp(long term, boolean success) {
        this.term = term;
        this.success = success;
    }


    public long getTerm() {
        return term;
    }

    public boolean isSuccess() {
        return success;
    }
}
