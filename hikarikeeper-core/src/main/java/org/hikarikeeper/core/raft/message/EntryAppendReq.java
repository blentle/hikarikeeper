package org.hikarikeeper.core.raft.message;

import org.hikarikeeper.core.raft.RnId;
import org.hikarikeeper.core.raft.log.LogEntry;

import java.util.Collections;
import java.util.List;

/**
 * may be a rpc request
 */
public class EntryAppendReq {

    /**
     * current term
     */
    private long term;

    /**
     * leader node
     */
    private RnId leaderId;

    /**
     * previous log index
     */
    private long preLogIndex;

    /**
     * previous log term
     */
    private long preLogTerm;


    private List<LogEntry> entries = Collections.emptyList();

    /**
     * commit index of leader
     */
    private long commitIndex;

}
