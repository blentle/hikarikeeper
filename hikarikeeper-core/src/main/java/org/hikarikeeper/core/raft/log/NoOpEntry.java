package org.hikarikeeper.core.raft.log;

/**
 * no operation log entry
 * only occurred when the candidate be the leader
 */
public class NoOpEntry implements LogEntry {

    private long index;

    private long term;

    private final CommandOperation operation;

    public NoOpEntry(long index, long term, CommandOperation operation) {
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
        return LOG_ENTRY_NO_OP;
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
