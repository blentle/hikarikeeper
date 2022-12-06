package org.hikarikeeper.core.raft.log;

import org.hikarikeeper.core.raft.RnId;
import org.hikarikeeper.core.raft.message.EntryAppendReq;

/**
 * provide a uniform log api for applications
 */
public interface RaftLog {

    NoOpEntry appendEntry(int term);

    GenericEntry appendEntry(int term, CommandOperation operation);

    EntryMetadata getLastEntryMetadata();

    EntryAppendReq createEntryAppendReqRpc(long term, RnId self, long nextIndex, long maxEntries);

    long getNextLogIndex();

    long getCommitIndex();

    void close();

}
