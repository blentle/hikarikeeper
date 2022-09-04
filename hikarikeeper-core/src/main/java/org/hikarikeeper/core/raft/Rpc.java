package org.hikarikeeper.core.raft;

public interface Rpc {

    void init();

    void sendRequestVote();

    void replyRequestVote();

    void sendAppendEntries();

    void replyAppendEntries();

    void close();
}
