package org.hikarikeeper.core.raft.log;


/**
 * log item for replication
 */
public interface LogEntry {

    int LOG_ENTRY_NO_OP = 0;

    int LOG_ENTRY_GENERIC = 1;

    long getIndex();

    long getTerm();


    int getEntryType();

    CommandOperation getOperation();

    byte[] encode();


}
