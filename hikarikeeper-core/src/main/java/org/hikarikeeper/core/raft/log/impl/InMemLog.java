package org.hikarikeeper.core.raft.log.impl;

import org.hikarikeeper.core.raft.RnId;
import org.hikarikeeper.core.raft.log.CommandOperation;
import org.hikarikeeper.core.raft.log.EntryMetadata;
import org.hikarikeeper.core.raft.log.GenericEntry;
import org.hikarikeeper.core.raft.log.NoOpEntry;
import org.hikarikeeper.core.raft.log.RaftLog;
import org.hikarikeeper.core.raft.message.EntryAppendReq;

/**
 * in general, we don't use in memory log,
 * this log is only used for test, demo or debug
 * todo: InMemLog may be need a encapsulated class called 'LogSequence' to the application invocations
 */
public class InMemLog implements RaftLog {

    public InMemLog() {

    }

    @Override
    public NoOpEntry appendEntry(int term) {
        return null;
    }

    @Override
    public GenericEntry appendEntry(int term, CommandOperation operation) {
        return null;
    }

    @Override
    public EntryMetadata getLastEntryMetadata() {
        return null;
    }

    @Override
    public EntryAppendReq createEntryAppendReqRpc(long term, RnId self, long nextIndex, long maxEntries) {
        return null;
    }

    @Override
    public long getNextLogIndex() {
        return 0;
    }

    @Override
    public long getCommitIndex() {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isNewerThan(long lastLogIndex, long lastLogTerm) {
        return false;
    }
}
