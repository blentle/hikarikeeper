package org.hikarikeeper.core.raft.log;

/**
 * generic log entry
 */
public class GenericEntry implements LogEntry {

    private long index;

    private long term;

    private final CommandOperation operation;

    public GenericEntry(long index, long term, CommandOperation operation) {
        this.index = index;
        this.term = term;
        this.operation = operation;
    }

    @Override
    public long getIndex() {
        return index;
    }

    @Override
    public long getTerm() {
        return term;
    }

    @Override
    public int getEntryType() {
        return LOG_ENTRY_GENERIC;
    }

    @Override
    public CommandOperation getOperation() {
        return operation;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
